package com.jukusoft.rpgcreator.editor.network.message.handler;

import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.editor.network.message.ManCenterMessage;
import com.jukusoft.rpgcreator.editor.network.message.MessageReceiver;
import com.jukusoft.rpgcreator.editor.network.message.NetworkReceiveEvents;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 25.06.2017.
 */
public class DistributedMessageHandler implements MessageReceiver<ManCenterMessage> {

    /**
    * map with all registered handler for an events
    */
    protected Map<String,EventHandler<ManCenterMessage>> handlerMap = new ConcurrentHashMap<>();

    /**
    * array with all events, which could be handled without user is logged in
    */
    protected static final String[] ALLOWED_EVENTS_WITHOUT_LOGIN = {
            NetworkReceiveEvents.LOGIN
    };

    protected AtomicBoolean isLoggedIn = new AtomicBoolean(false);

    protected ManCenterClient client = null;

    public DistributedMessageHandler (ManCenterClient client) {
        this.client = client;
    }

    @Override
    public void messageReceived(ManCenterMessage message) {
        if (message == null) {
            throw new NullPointerException("message is null.");
        }

        if (message.getEvent() == null || message.getEvent().isEmpty()) {
            throw new IllegalStateException("event cannot be empty.");
        }

        //check, if permissions for message
        if (!isLoggedIn.get() && !isEventAllowedWithoutLogin(message.getEvent())) {
            //drop message
            System.err.println("drop message with event '" + message.getEvent() + "', because user isnt logged in.");

            return;
        }

        //search for an event handler
        EventHandler<ManCenterMessage> eventHandler = this.handlerMap.get(message.getEvent());

        if (eventHandler == null) {
            //drop message
            System.err.println("drop message with event '" + message.getEvent() + "', because no event handler was found.");

            return;
        }

        //handle message
        eventHandler.messageReceived(this.client, message);
    }

    public void addHandler (String event, EventHandler<ManCenterMessage> handler) {
        //check, if their is already an handler for this event registered
        EventHandler<ManCenterMessage> eventHandler = this.handlerMap.get(event);

        if (eventHandler != null) {
            throw new IllegalStateException("event handler for event '" + event + "' was already registered!");
        }

        //add event handler
        this.handlerMap.put(event, handler);
    }

    public void removeHandler (String event) {
        this.handlerMap.remove(event);
    }

    public boolean existsHandler (String event) {
        return this.handlerMap.get(event) != null;
    }

    /**
    * check, if an event message can be handled without logged in user
     *
     * @return true, if message can handled, if user isnt logged in
    */
    public static final boolean isEventAllowedWithoutLogin (String event) {
        for (String str : ALLOWED_EVENTS_WITHOUT_LOGIN) {
            if (str.equals(event)) {
                return true;
            }
        }

        return false;
    }

}
