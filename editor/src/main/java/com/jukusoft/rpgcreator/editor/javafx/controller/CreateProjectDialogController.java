package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.editor.javafx.CreateProjectProgressDialog;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import com.jukusoft.rpgcreator.engine.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.05.2017.
 */
public class CreateProjectDialogController implements FXMLController, Initializable {

    protected int DEFAULT_PORT = 1234;

    protected final String CSS_FAILED_STYLE = "-fx-border-color:#ff0000; -fx-background-color:#c4cfed; ";
    protected final String CSS_SUCCESS_STYLE = "-fx-border-color:#1b52f1; -fx-background-color:#c4cfed; ";

    @FXML
    protected TextField projectTitleTextField;

    @FXML
    protected TextField projectPathTextField;

    @FXML
    protected TextField serverIPTextField;

    @FXML
    protected TextField serverPortTextField;

    @FXML
    protected CheckBox includeAssetsCheckBox;

    @FXML
    protected Button createGameButton;

    @FXML
    protected Button choosePathButton;

    protected Stage stage = null;

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;

        projectPathTextField.setText(getBasePath());

        //disable resize window
        stage.setResizable(false);

        createGameButton.setOnAction((ActionEvent event) -> {
            //check, if form is valide
            if (validate()) {
                //create project
                try {
                    create();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        });

        projectTitleTextField.setOnKeyTyped((KeyEvent event) -> {
            generateAndSetProjectPath();

            validate();
        });

        projectPathTextField.setOnMouseMoved((MouseEvent event) -> {
            generateAndSetProjectPath();
        });

        serverPortTextField.setText(DEFAULT_PORT + "");

        this.choosePathButton.setOnAction((ActionEvent event) -> {
            System.out.println("not supported yet.");
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    protected String getBasePath () {
        return new File("./games/").getAbsolutePath().replace(".\\", "").replace("\\", "/");
    }

    /**
    * check, if all form fields are valide
     *
     * @return true, if all fields are valide
    */
    protected boolean validate () {
        //TODO: add code here

        //get project title
        String title = projectTitleTextField.getText();

        if (!title.equals(title.replace(" ", ""))) {
            projectTitleTextField.setStyle(CSS_FAILED_STYLE);
            return false;
        } else {
            projectTitleTextField.setStyle(CSS_SUCCESS_STYLE);
        }

        return true;
    }

    /**
    * create new project
    */
    protected void create () throws Exception {
        //get title
        String title = projectTitleTextField.getText();

        //get path
        String path = projectPathTextField.getText();

        String ip = serverIPTextField.getText();
        int port = Integer.parseInt(serverPortTextField.getText());

        boolean includeAssets = this.includeAssetsCheckBox.isSelected();

        //check, if directory already exists
        if (new File(path).exists()) {
            //check, if directory is empty
            if (FileUtils.isDirEmpty(new File(path))) {
                //delete directory
                new File(path).delete();
            } else {
                //path already exists
                this.projectPathTextField.setStyle(CSS_FAILED_STYLE);

                return;
            }
        }

        stage.hide();

        //close window
        stage.close();

        System.out.println("open create project progress window.");
        CreateProjectProgressDialog dialog = new CreateProjectProgressDialog(title, path, ip, port, includeAssets);
    }

    protected void generateAndSetProjectPath() {
        String text = getBasePath() + "/" + projectTitleTextField.getText();

        if (!projectPathTextField.getText().equals(text)) {
            projectPathTextField.setText(getBasePath() + "/" + projectTitleTextField.getText());
        }
    }

    @Override
    public void run() {
        //
    }

}
