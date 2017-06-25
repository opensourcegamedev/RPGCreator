package com.jukusoft.rpgcreator.editor.network.message.custom;

import com.jukusoft.rpgcreator.editor.network.message.ManCenterMessage;
import com.jukusoft.rpgcreator.editor.network.NetworkReceiveEvents;
import org.json.JSONObject;

/**
 * Login result message
 *
 * Proxy Design Pattern
 *
 * Created by Justin on 25.06.2017.
 */
public class LoginResultMessage {

    protected ManCenterMessage originMsg = null;

    protected boolean succeeded = false;

    protected String username = "";
    protected Throwable cause = null;

    private LoginResultMessage (ManCenterMessage originMsg) {
        this.originMsg = originMsg;
    }

    protected void load (JSONObject json) {
        this.succeeded = json.getBoolean("succeeded");

        if (this.succeeded) {
            this.username = json.getString("username");
        }

        if (!json.isNull("cause")) {
            this.cause = new Throwable(json.getString("cause"));
        }
    }

    public boolean succeeded () {
        return this.succeeded;
    }

    public boolean failed () {
        return !this.succeeded;
    }

    public Throwable cause () {
        return this.cause;
    }

    public static LoginResultMessage create (ManCenterMessage msg) {
        if (msg.getEvent() != NetworkReceiveEvents.LOGIN) {
            throw new IllegalArgumentException("this message cannot be proxied by LoginResultMessage, because message has another event name.");
        }

        LoginResultMessage message = new LoginResultMessage(msg);
        message.load(new JSONObject(msg.getPlainData()));

        return message;
    }

}
