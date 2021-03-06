package com.jukusoft.rpgcreator.engine.screen.impl;

import com.badlogic.gdx.assets.AssetManager;
import com.jukusoft.rpgcreator.engine.game.ScreenBasedGame;
import com.jukusoft.rpgcreator.engine.screen.IScreen;

/**
 * Created by Justin on 06.02.2017.
 */
public abstract class BaseScreen implements IScreen {

    protected ScreenBasedGame game;
    protected AssetManager assetManager;

    public final void init (ScreenBasedGame game, AssetManager assetManager) {
        this.game = game;
        this.assetManager = assetManager;

        this.onInit(game, assetManager);
    }

    protected abstract void onInit (ScreenBasedGame game, AssetManager assetManager);

    @Override public void onPause() {

    }

    @Override public void onResume() {

    }

}
