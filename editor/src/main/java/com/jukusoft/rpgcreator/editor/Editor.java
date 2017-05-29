package com.jukusoft.rpgcreator.editor;

import com.jukusoft.rpgcreator.editor.screen.IntroScreen;
import com.jukusoft.rpgcreator.engine.game.ScreenBasedGame;
import com.jukusoft.rpgcreator.engine.screen.IScreen;
import com.jukusoft.rpgcreator.engine.screen.ScreenManager;

/**
 * Created by Justin on 29.05.2017.
 */
public class Editor extends ScreenBasedGame {

    public Editor () {
        super();
    }

    @Override
    protected void onCreateScreens(ScreenManager<IScreen> screenManager) {
        //create and add screens
        screenManager.addScreen("intro", new IntroScreen());

        //push screens
        screenManager.push("intro");
    }

}
