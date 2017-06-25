package com.jukusoft.rpgcreator.editor.network.message;

import com.jukusoft.rpgcreator.engine.json.JSONLoadable;
import com.jukusoft.rpgcreator.engine.json.JSONSerializable;
import org.json.JSONObject;

/**
 * Created by Justin on 25.06.2017.
 */
public class ManCenterMessage implements JSONSerializable, JSONLoadable {

    /**
    * internal constructor for create() method
    */
    private ManCenterMessage () {
        //
    }

    @Override
    public void loadFromJSON(JSONObject json) {
        //
    }

    @Override
    public JSONObject toJSON() {
        return null;
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
