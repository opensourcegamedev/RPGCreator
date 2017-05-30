package com.jukusoft.rpgcreator.editor.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Justin on 30.05.2017.
 */
public class JavaFXApplication extends Application {

    public JavaFXApplication () {
        //
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartDialog startDialog = new StartDialog();
    }

}
