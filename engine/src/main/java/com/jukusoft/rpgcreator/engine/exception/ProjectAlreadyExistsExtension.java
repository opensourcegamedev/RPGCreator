package com.jukusoft.rpgcreator.engine.exception;

import java.io.IOException;

/**
 * Exception which is thrown, if game project file already exists
 *
 * Created by Justin on 23.06.2017.
 */
public class ProjectAlreadyExistsExtension extends IOException {

    public ProjectAlreadyExistsExtension (String message) {
        super(message);
    }

}
