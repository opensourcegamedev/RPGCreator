package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.editor.javafx.CreateProjectDialog;
import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.editor.network.impl.DefaultManCenterClient;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;
import com.jukusoft.rpgcreator.engine.utils.SocketUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.05.2017.
 */
public class CreateProjectProgressDialogController implements FXMLController, Initializable {

    protected final String title;
    protected String path = "";
    protected final String ip;
    protected final int port;
    protected final boolean includeAssets;

    protected static final String[] STATES_TEXT = {
            "Initialize...",
            "Check, if server is available...",
            "Create project directory...",
            "Set directory file permissions...",
            "Connect to server...",
            "Try to login...",
            "Check server version..."
    };

    //socket timeout which will be used to check, if remote server is available
    protected static final int SOCKET_TIMEOUT = 500;

    protected int currentState = -1;

    //RPGCreator management center client
    protected ManCenterClient client = null;

    @FXML
    protected Label stateLabel;

    @FXML
    protected ProgressBar progressBar;

    @FXML
    protected Button backButton;

    @FXML
    protected Stage stage;

    public CreateProjectProgressDialogController (String title, String path, String ip, int port, boolean includeAssets) {
        this.title = title;
        this.path = path;
        this.ip = ip;
        this.port = port;
        this.includeAssets = includeAssets;

        //add slash at end of path, if neccessary
        if (!this.path.endsWith("/")) {
            this.path = this.path + "/";
        }
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        stateLabel.setText("1% - Connect to server...");

        //hide back button
        backButton.setVisible(false);

        backButton.setOnAction((ActionEvent event) -> {
            //hide and close current window
            stage.hide();
            stage.close();

            //open create project dialog
            CreateProjectDialog dialog = new CreateProjectDialog();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize.");
    }

    @Override
    public void run() {
        //increment state
        incState(1);

        //increment state
        incState(5);

        //check if server port is open
        boolean available = SocketUtils.checkIfRemotePortAvailable(this.ip, this.port, SOCKET_TIMEOUT);

        if (!available) {
            //set progressbar color to red, show back button and set state text
            setFailedState("Failed! Remote RPGCreator Server isnt available!\nwait 3 seconds...");

            return;
        } else {
            System.out.println("server " + ip + ":" + port + " is available!");
        }

        incState(20);

        //create project directory
        System.out.println("Create project directory: " + path);

        File projectRootPath = new File(this.path);

        if (projectRootPath.exists()) {
            //set progressbar color to red, show back button and set state text
            setFailedState("Failed! Project directory already exists!\nwait 3 seconds...");

            //log message
            System.err.println("Project directory already exists! Path: " + path);

            return;
        }

        //create project directory
        projectRootPath.mkdirs();

        incState(25);

        //set correct directory permissions
        projectRootPath.setWritable(true);
        projectRootPath.setReadable(true);

        //connect to server
        incState(30);

        //create new ManCenter client
        this.client = new DefaultManCenterClient();

        //try to connect
        this.client.connect(ip, port, res -> {
            if (!res.succeeded()) {
                //remove project directory
                projectRootPath.delete();

                //set progressbar color to red, show back button and set state text
                setFailedState("Failed! Couldnt connect to server!");

                return;
            }

            //execute next operations in new thread to avoid blocking vertx.io queue
            new Thread(this::runAfterConnect).start();
        });
    }

    private void runAfterConnect () {
        //try to login
        incState(35);
    }

    protected void updateState (int percent, int state) {
        if (state > STATES_TEXT.length) {
            throw new ArrayIndexOutOfBoundsException("state text with index " + state + " isnt defined.");
        }

        //update progressbar and state label
        updateState(percent, STATES_TEXT[state]);

        this.currentState = state;
    }

    protected void updateState (int percent, String text) {
        //log message
        System.out.println("new state text: " + percent + "% - " + text);

        //execute in UI thread
        Platform.runLater(() -> {
            //update progressbar first
            progressBar.setProgress(((double) percent / 100));

            //update state label
            stateLabel.setText("" + percent + "% - " + text);
        });
    }

    protected void setFailedState () {
        //execute in UI thread
        Platform.runLater(() -> {
            progressBar.setStyle("-fx-accent: red; ");

            //show back button
            backButton.setVisible(true);
        });
    }

    protected void setFailedState (String text) {
        updateState(100, text);

        //set progressbar color to red and show back button
        setFailedState();
    }

    protected void incState (int percent) {
        updateState(percent, (this.currentState + 1));
    }

}
