package com.jukusoft.rpgcreator.editor.network.message.factory;

import com.jukusoft.rpgcreator.editor.network.NetworkEvents;
import com.jukusoft.rpgcreator.editor.network.message.ManCenterMessage;
import org.json.JSONObject;

/**
 * Created by Justin on 25.06.2017.
 */
public class LoginMessageFactory {

    public static ManCenterMessage createLoginMessage (String username, String password) {
        ManCenterMessage message = new ManCenterMessage(NetworkEvents.LOGIN);

        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);

        //set json data
        message.setPlainData(json.toString());

        return message;
    }

}
