package com.jukusoft.rpgcreator.engine.network;

/**
 * Created by Justin on 25.06.2017.
 */
public interface Handler<T> {

    public void handle (T event);

}
