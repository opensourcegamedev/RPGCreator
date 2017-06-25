package com.jukusoft.rpgcreator.editor.network;

import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;

/**
 * Created by Justin on 31.05.2017.
 */
public interface ManCenterClient {

    /**
    * connect to management server
     *
     * @param ip ip of management server
     * @param port port of server
     * @param handler handler which will be executed, if operation was executed
    */
    public void connect (String ip, int port, Handler<AsyncResult<String>> handler);

    /**
    * try to login user
     *
     * @param user username to authentificate
     * @param password password
     * @param handler handler which will be executed, if operation was executed
    */
    public void login (String user, String password, Handler<AsyncResult<String>> handler);

    /**
    * disconnect from management server
    */
    public void disconnect ();

}
