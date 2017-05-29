package com.jukusoft.rpgcreator.editor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jukusoft.rpgcreator.editor.Editor;
import com.jukusoft.rpgcreator.engine.utils.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 29.05.2017.
 */
public class DesktopLauncher {

    /**
     * The start-method for the whole application which is creating a new configuration for the stage and a new game so one could play.
     *
     * @param args Unused argument so far.
     */
    public static void main (String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "RPG Creator - Editor";
        config.height = 720;
        config.width = 1280;
        config.resizable = true;
        //config.addIcon("./data/icons/icon.png", Files.FileType.Absolute);

        try {
            //start game
            new LwjglApplication(new Editor(), config);
        } catch (Exception e) {
            e.printStackTrace();

            try {
                //write crash dump
                FileUtils.writeFile("./crash.log", e.getLocalizedMessage(), StandardCharsets.UTF_8);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            System.exit(-1);
        }
    }

}
