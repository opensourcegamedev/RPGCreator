package com.jukusoft.rpgcreator.engine.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jukusoft.rpgcreator.engine.data.DefaultSharedData;
import com.jukusoft.rpgcreator.engine.data.SharedData;
import com.jukusoft.rpgcreator.engine.screen.IScreen;
import com.jukusoft.rpgcreator.engine.screen.ScreenManager;
import com.jukusoft.rpgcreator.engine.screen.impl.DefaultScreenManager;
import com.jukusoft.rpgcreator.engine.time.GameTime;

/**
 * Created by Justin on 06.02.2017.
 */
public abstract class ScreenBasedGame extends BaseGame {

    protected ScreenManager<IScreen> screenManager = null;

    /**
    *
    */
    protected SharedData sharedData = null;

    public ScreenBasedGame() {
        super();

        this.screenManager = new DefaultScreenManager(this);
        this.sharedData = new DefaultSharedData();
    }

    @Override
    protected final void update (GameTime time) {
        //update all screens
        for (IScreen screen : this.screenManager.listActiveScreens()) {
            //update screen
            screen.update(this, time);
        }
    }

    @Override
    protected final void draw (GameTime time, SpriteBatch batch) {
        //draw all screens
        for (IScreen screen : this.screenManager.listActiveScreens()) {
            //draw screen
            screen.draw(time, batch);
        }
    }

    @Override
    protected final void initGame () {
        this.onCreateScreens(this.screenManager);
    }

    @Override
    protected final void destroyGame () {
        //pause all active screens
        this.screenManager.listActiveScreens().forEach(screen -> {
            screen.onPause();
        });

        this.onDestroyGame();
    }

    protected abstract void onCreateScreens (ScreenManager<IScreen> screenManager);

    public ScreenManager<IScreen> getScreenManager () {
        if (this.screenManager == null) {
            throw new IllegalStateException("screen manager isnt initialized yet. Call constructor of ScreenBasedGame first!");
        }

        return this.screenManager;
    }

    public SharedData getSharedData () {
        if (this.sharedData == null) {
            throw new IllegalStateException("shared data isnt inialized yet. Call constructor of ScreenBasedGame first!");
        }

        return this.sharedData;
    }

    protected void onDestroyGame () {
        //
    }

}
