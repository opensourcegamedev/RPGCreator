package com.jukusoft.rpgcreator.editor.network.impl;

import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.editor.network.NetworkReceiveEvents;
import com.jukusoft.rpgcreator.editor.network.message.custom.LoginResultMessage;
import com.jukusoft.rpgcreator.editor.network.message.factory.LoginMessageFactory;
import com.jukusoft.rpgcreator.editor.network.message.handler.DistributedMessageHandler;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;
import com.jukusoft.rpgcreator.engine.network.impl.WritableAsyncResult;

/**
 * Created by Justin on 25.06.2017.
 */
public class DefaultManCenterClient extends VertxClient implements ManCenterClient {

    protected DistributedMessageHandler distributedMessageHandler = null;

    public DefaultManCenterClient () {
        super();

        //create new message distributor
        this.distributedMessageHandler = new DistributedMessageHandler(this);

        //set message receiver
        this.setMessageReceiver(this.distributedMessageHandler);
    }

    @Override
    public void login(String user, String password, Handler<AsyncResult<LoginResultMessage>> handler) {
        if (!isConnected()) {
            throw new IllegalStateException("client isnt connected to ManCenter server, so client cannot login.");
        }

        if (distributedMessageHandler.existsHandler(NetworkReceiveEvents.LOGIN)) {
            distributedMessageHandler.removeHandler(NetworkReceiveEvents.LOGIN);
        }

        //register ack message handler
        distributedMessageHandler.addHandler(NetworkReceiveEvents.LOGIN, (client, message) -> {
            //parse message
            LoginResultMessage resMsg = LoginResultMessage.create(message);

            if (!resMsg.succeeded()) {
                handler.handle(new WritableAsyncResult<LoginResultMessage>(resMsg.cause()));
            } else {
                handler.handle(new WritableAsyncResult<LoginResultMessage>(resMsg, true));
            }
        });

        //send login message
        this.write(LoginMessageFactory.createLoginMessage(user, password));
    }

    @Override
    public DistributedMessageHandler getDistributedMessageHandler() {
        return this.distributedMessageHandler;
    }

}
