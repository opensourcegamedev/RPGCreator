package com.jukusoft.rpgcreator.editor.network;

/**
 * Created by Justin on 31.05.2017.
 */
public interface ManCenterClient {

    /**
    * connect to management server
     *
     * @param ip ip of management server
     * @param port port of server
     * @param user username to authentificate
     * @param password password
    */
    public boolean connect (String ip, int port, String user, String password);

    /**
    * disconnect from management server
    */
    public void disconnect ();

}
