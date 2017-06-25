package com.jukusoft.rpgcreator.editor.network.impl;

import com.jukusoft.rpgcreator.editor.network.message.ManCenterMessage;
import com.jukusoft.rpgcreator.editor.network.message.MessageReceiver;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 25.06.2017.
 */
public class VertxClient implements NetworkClient<ManCenterMessage> {

    /**
     * vertx.io options
     */
    protected VertxOptions vertxOptions = new VertxOptions();

    /**
     * vertx.io instance
     */
    protected Vertx vertx = null;

    /**
     * vert.x network client options
     */
    protected NetClientOptions netClientOptions = new NetClientOptions();

    /**
     * network client
     */
    protected NetClient netClient = null;

    /**
     * network socket
     */
    protected NetSocket socket = null;

    /**
     * message receiver
     */
    protected MessageReceiver messageReceiver = null;

    /**
     * flag, if client is connected
     */
    protected AtomicBoolean connected = new AtomicBoolean(false);

    public VertxClient () {
        //set connection timeout of 1 second
        this.netClientOptions.setConnectTimeout(1000);

        //if connection fails, try 3 times every 500ms
        this.netClientOptions.setReconnectAttempts(3)
                .setReconnectInterval(500);
    }

    @Override
    public void connect(String ip, int port) throws Exception {
        if (this.messageReceiver == null) {
            throw new IllegalStateException("You have to set an message receiver first.");
        }

        //create new vertx.io instance
        this.vertx = Vertx.vertx(this.vertxOptions);

        //create new network client
        this.netClient = this.vertx.createNetClient(this.netClientOptions);

        //connect to server
        this.netClient.connect(port, ip, res -> {
            if (res.succeeded()) {
                System.out.println("Connected!");
                this.socket = res.result();

                //initialize socket
                initSocket(socket);

                //set flag
                connected.set(true);
            } else {
                System.out.println("Failed to connect: " + res.cause().getMessage());
            }
        });
    }

    /**
     * initialize socket
     *
     * @param socket network socket
     */
    protected void initSocket (NetSocket socket) {
        //set connection close handler
        socket.closeHandler(res -> {
            System.err.println("Server connection was closed by server.");

            System.exit(0);
        });

        //add message handler
        socket.handler(buffer -> {
            //convert to string and json object
            String str = buffer.toString(StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(str);

            //System.out.println("message received: " + str);

            //convert to chat message
            ManCenterMessage msg = ManCenterMessage.create(json);

            //call message receiver
            messageReceiver.messageReceived(msg);
        });
    }

    @Override
    public void setMessageReceiver(MessageReceiver messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    @Override
    public void write(ManCenterMessage msg) {
        if (!isConnected()) {
            throw new IllegalStateException("Client isnt connected, so VertxClient cannot send message.");
        }

        if (this.socket == null) {
            throw new IllegalStateException("Socket is null, so vertx client cannot send message to server.");
        }

        //write json string of message to server
        this.socket.write(msg.toJSON().toString());
    }

    @Override
    public void executeBlocking(Runnable runnable) {
        this.vertx.executeBlocking(future -> {
            //execute blocking code
            runnable.run();

            //task was executed
            future.complete();
        }, res -> {
            //
        });
    }

    @Override
    public boolean isConnected() {
        return this.connected.get();
    }

    @Override
    public void disconnect() {
        //check, if client is connected
        if (isConnected()) {
            //close socket
            this.socket.close();
        }

        //close vert.x client
        this.netClient.close();
        this.vertx.close();
    }

}
