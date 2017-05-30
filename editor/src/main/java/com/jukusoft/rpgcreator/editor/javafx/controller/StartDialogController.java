package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.editor.javafx.CreateProjectDialog;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.05.2017.
 */
public class StartDialogController implements FXMLController, Initializable {

    @FXML
    protected Stage stage = null;

    @FXML
    protected Button createProjectButton;

    @FXML
    protected Button loadProjectButton;

    public StartDialogController () {
        //
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;

        if (this.createProjectButton == null) {
            throw new NullPointerException("button is null.");
        }

        this.createProjectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("create new project.");
                createNewProject();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    protected void createNewProject () {
        //hide stage
        stage.hide();

        //open create project dialog
        CreateProjectDialog dialog = new CreateProjectDialog();
    }

    protected void loadProject () {
        //
    }

}
