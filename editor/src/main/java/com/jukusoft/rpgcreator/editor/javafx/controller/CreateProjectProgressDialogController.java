package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.05.2017.
 */
public class CreateProjectProgressDialogController implements FXMLController, Initializable {

    public CreateProjectProgressDialogController (String title, String path, String ip, int port, boolean includeAssets) {
        //
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize.");
    }

}
