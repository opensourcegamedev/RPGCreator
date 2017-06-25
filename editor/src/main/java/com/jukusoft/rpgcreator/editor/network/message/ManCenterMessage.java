package com.jukusoft.rpgcreator.editor.network.message;

import com.jukusoft.rpgcreator.engine.Engine;
import com.jukusoft.rpgcreator.engine.json.JSONLoadable;
import com.jukusoft.rpgcreator.engine.json.JSONSerializable;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Justin on 25.06.2017.
 */
public class ManCenterMessage implements JSONSerializable, JSONLoadable {

    /**
    * constants for json string keys
    */
    protected static final String KEY_EVENT = "event";
    protected static final String KEY_DATA = "data";
    protected static final String KEY_ENGINE_VERSION = "engine_version";
    protected static final String KEY_ACK = "ack_message";
    protected static final String KEY_UUID = "msg_uuid";

    /**
    * network data to send
    */
    protected String event = "";
    protected String data = "";

    protected boolean ackMessage = false;
    protected UUID uuid = null;

    /**
    * remote version of engine,
     * for example if client sends this message, this is client engine version,
     * if server sends this message, this is server engine version
    */
    protected String remoteVersion = "";

    /**
    * default constructor
     *
     * @param event event name
    */
    public ManCenterMessage (String event) {
        //only lowercase events are allowed
        this.event = event.trim().toLowerCase();

        //generate new unique id for message
        this.uuid = UUID.randomUUID();
    }

    /**
    * internal constructor for create() method
    */
    private ManCenterMessage () {
        //
    }

    @Override
    public final void loadFromJSON(JSONObject json) {
        //check, if json is valide
        if (json.isNull(KEY_EVENT)) {
            throw new IllegalArgumentException("invalide json string, event is null.");
        }

        if (json.isNull(KEY_DATA)) {
            throw new IllegalArgumentException("invalide json string, data is null.");
        }

        if (json.isNull(KEY_ENGINE_VERSION)) {
            throw new IllegalArgumentException("invalide json string, engine_version is null.");
        }

        if (!json.isNull(KEY_ACK) && !json.isNull(KEY_UUID)) {
            this.ackMessage = true;
            this.uuid = UUID.fromString(json.getString(KEY_UUID));
        }

        //get data
        this.event = json.getString(KEY_EVENT);
        this.data = json.getString(KEY_DATA);

        //get engine version
        this.remoteVersion = json.getString(KEY_ENGINE_VERSION);

        this.afterLoad(this.data);
    }

    /**
    * this method can be overriden to add logik to load complex objects
    */
    protected void afterLoad (String data) {
        //convert plain data to correct data object
    }

    /**
    * get event name, for example "login"
     *
     * @return event name
    */
    public String getEvent () {
        return this.event;
    }

    /**
    * get data as json string
    */
    public String getData () {
        return this.data;
    }

    /**
    * get plain json data
     *
     * @return plain data as string
    */
    public final String getPlainData () {
        return this.data;
    }

    /**
    * set plain data as string
     *
     * @param data plain data as string
    */
    public void setPlainData (String data) {
        this.data = data;
    }

    public boolean isAckMessage () {
        return this.ackMessage;
    }

    public void setAckMessage (boolean ackMessage) {
        this.ackMessage = ackMessage;
    }

    public void setAckMessage (UUID uuid) {
        this.ackMessage = true;
        this.uuid = uuid;
    }

    public UUID getUUID () {
        if (!isAckMessage()) {
            throw new IllegalStateException("this message isnt an acknowledged message.");
        }

        return this.uuid;
    }

    @Override
    public JSONObject toJSON() {
        //create new json object
        JSONObject json = new JSONObject();

        //put data
        json.put(KEY_EVENT, this.getEvent());
        json.put(KEY_DATA, this.getData());

        if (this.ackMessage) {
            json.put(KEY_ACK, this.ackMessage);
            json.put(KEY_UUID, this.uuid.toString());
        }

        //put version of RPG Creator Engine
        json.put(KEY_ENGINE_VERSION, Engine.getVersionString());

        return json;
    }

    @Override
    public String toString () {
        return toJSON().toString();
    }

    public static ManCenterMessage create (JSONObject json) {
        //create new message
        ManCenterMessage message = new ManCenterMessage();

        //load message from json
        message.loadFromJSON(json);

        return message;
    }

}
