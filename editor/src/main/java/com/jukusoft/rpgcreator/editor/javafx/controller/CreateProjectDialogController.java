package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.05.2017.
 */
public class CreateProjectDialogController implements FXMLController, Initializable {

    @FXML
    protected TextField projectTitleTextField;

    @FXML
    protected TextField projectPathTextField;

    @FXML
    protected TextField serverIPTextField;

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        projectPathTextField.setText(getBasePath());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    protected String getBasePath () {
        return new File("./games/").getAbsolutePath().replace(".\\", "").replace("\\", "/");
    }

}
