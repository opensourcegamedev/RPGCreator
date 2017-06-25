package com.jukusoft.rpgcreator.editor.network.message;

import com.jukusoft.rpgcreator.engine.Engine;
import com.jukusoft.rpgcreator.engine.json.JSONLoadable;
import com.jukusoft.rpgcreator.engine.json.JSONSerializable;
import org.json.JSONObject;

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

    /**
    * network data to send
    */
    protected String event = "";
    protected String data = "";

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

        //get data
        this.event = json.getString(KEY_EVENT);
        this.data = json.getString(KEY_DATA);

        //get engine version
        this.remoteVersion = json.getString(KEY_ENGINE_VERSION);

        this.afterLoad();
    }

    /**
    * this method can be overriden to add logik to load complex objects
    */
    protected void afterLoad () {
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

    @Override
    public JSONObject toJSON() {
        //create new json object
        JSONObject json = new JSONObject();

        //put data
        json.put(KEY_EVENT, this.getEvent());
        json.put(KEY_DATA, this.getData());

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
