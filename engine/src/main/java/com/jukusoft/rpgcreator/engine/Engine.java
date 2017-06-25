package com.jukusoft.rpgcreator.engine;

/**
 * Created by Justin on 29.05.2017.
 */
public class Engine {

    public static final int MAJOR_VERSION = 1;
    public static final int MINOR_VERSION = 0;
    public static final int PATCH_LEVEL = 0;
    public static final int BUILD_NUMBER = 1;

    public static String getVersionString () {
        return MAJOR_VERSION + "." + MINOR_VERSION + "." + PATCH_LEVEL + "-" + BUILD_NUMBER;
    }

}
