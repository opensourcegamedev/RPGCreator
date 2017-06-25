package com.jukusoft.rpgcreator.editor.network.impl;

import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.editor.network.message.handler.DistributedMessageHandler;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;

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
    public void login(String user, String password, Handler<AsyncResult<String>> handler) {
        if (!isConnected()) {
            throw new IllegalStateException("client isnt connected to ManCenter server, so client cannot login.");
        }

        //send login message
    }

    @Override
    public DistributedMessageHandler getDistributedMessageHandler() {
        return this.distributedMessageHandler;
    }

}
