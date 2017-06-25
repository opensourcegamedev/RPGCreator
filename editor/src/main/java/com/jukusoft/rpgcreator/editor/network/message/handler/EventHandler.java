package com.jukusoft.rpgcreator.editor.network.message.handler;

import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.editor.network.message.ManCenterMessage;
import com.jukusoft.rpgcreator.editor.network.message.MessageReceiver;

/**
 * Created by Justin on 25.06.2017.
 */
public interface EventHandler<T> {

    public void messageReceived(ManCenterClient client, T message);

}
