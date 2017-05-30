package com.jukusoft.rpgcreator.engine.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Justin on 30.05.2017.
 */
public abstract class JavaFXApplication extends Application {

    public JavaFXApplication (String... args) {
        Application.launch(args);
    }

}
