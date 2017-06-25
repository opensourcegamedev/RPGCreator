package com.jukusoft.rpgcreator.editor.network.message;

/**
 * Created by Justin on 25.06.2017.
 */
public abstract class CustomManCenterMessage extends ManCenterMessage {

    /**
     * default constructor
     *
     * @param event event name
     */
    public CustomManCenterMessage(String event) {
        super(event);
    }

    /**
     * get data as json string
     */
    @Override
    public abstract String getData ();

    /**
     * this method can be overriden to add logik to load complex objects
     */
    @Override
    protected abstract void afterLoad (String data);

}
