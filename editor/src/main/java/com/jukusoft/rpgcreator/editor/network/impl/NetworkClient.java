package com.jukusoft.rpgcreator.editor.network.impl;

import com.jukusoft.rpgcreator.editor.network.message.MessageReceiver;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;

/**
 * Created by Justin on 25.06.2017.
 */
public interface NetworkClient<T> {

    /**
     * connect to server
     *
     * @param ip remote ip address of server
     * @param port remote port of server
     * @param handler handler which will be executed, if operation was successful or failed
     */
    public void connect (String ip, int port, Handler<AsyncResult<String>> handler) throws Exception;

    /**
     * set message receiver
     *
     * @param messageReceiver instance of message receiver
     */
    public void setMessageReceiver (MessageReceiver messageReceiver);

    /**
    * write message
    */
    public void write (T msg);

    /**
    * execute an blocking task asynchronous
     *
     * @param runnable runnable to execute
    */
    public void executeBlocking(Runnable runnable);

    /**
    * check, if client is connected
     *
     * @return true, if client is connected to server
    */
    public boolean isConnected ();

    /**
    * disconnect client from remote server
    */
    public void disconnect ();

}
