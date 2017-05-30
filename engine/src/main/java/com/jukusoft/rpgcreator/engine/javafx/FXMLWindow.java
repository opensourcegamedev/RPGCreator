package com.jukusoft.rpgcreator.engine.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Justin on 30.05.2017.
 */
public class FXMLWindow {

    //JavaFX stage means an window
    protected Stage stage = null;

    //root AnchorPane
    protected Pane rootPane = null;

    public FXMLWindow (String title, int width, int height, String fxmlPath) {
        //create new stage
        this.stage = new Stage();

        //set title
        stage.setTitle(title);

        //set width & height
        stage.setWidth(width);
        stage.setHeight(height);

        // load fxml
        Pane pane = null;
        try {
            pane = FXMLLoader.load(new File(fxmlPath).toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //add scene
        Scene scene = new Scene(pane);

        //set scene
        stage.setScene(scene);

        //show window
        stage.show();
    }

    public void setVisible (boolean visible) {
        if (!visible) {
            this.stage.hide();
        } else {
            this.stage.show();
        }
    }

}
