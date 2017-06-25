package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 25.06.2017.
 */
public class LoginController implements FXMLController, Initializable {

    protected ManCenterClient client = null;
    protected Handler<AsyncResult<ManCenterClient>> handler = null;

    @FXML
    protected TextField usernameTextField;

    @FXML
    protected PasswordField passwordTextField;

    @FXML
    protected Button loginButton;

    @FXML
    protected Button abortButton;

    @FXML
    protected Label serverIPLabel;

    @FXML
    protected Label serverPortLabel;

    protected final String ip;
    protected final int port;

    public LoginController (ManCenterClient client, Handler<AsyncResult<ManCenterClient>> handler, String ip, int port) {
        this.client = client;
        this.handler = handler;
        this.ip = ip;
        this.port = port;

        if (this.client == null) {
            throw new NullPointerException("client cannot be null.");
        }

        if (!this.client.isConnected()) {
            throw new IllegalStateException("client isnt connected.");
        }
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //set server address
        this.serverIPLabel.setText("Server IP: " + this.ip);
        this.serverPortLabel.setText("Port: " + this.port);
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
