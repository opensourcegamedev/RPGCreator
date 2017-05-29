package com.jukusoft.rpgcreator.engine.camera;

import com.jukusoft.rpgcreator.engine.time.GameTime;

/**
 * Created by Justin on 11.02.2017.
 */
public interface CameraModification {

    public void onUpdate(GameTime time, TempCameraParams position, ModificationFinishedListener listener);

    public void dispose();

}
