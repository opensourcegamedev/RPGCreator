package com.jukusoft.rpgcreator.editor.javafx.controller;

import com.jukusoft.rpgcreator.editor.javafx.CreateProjectDialog;
import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;
import com.jukusoft.rpgcreator.engine.network.impl.WritableAsyncResult;
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

    protected final String CSS_FAILED_STYLE = "-fx-border-color:#ff0000; -fx-background-color:#c4cfed; ";
    protected final String CSS_SUCCESS_STYLE = "-fx-border-color:#1b52f1; -fx-background-color:#c4cfed; ";

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

    @FXML
    protected Label errorLabel;

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

        //hide error label
        errorLabel.setVisible(false);

        this.abortButton.setOnAction(event -> {
            //hide and close current window
            stage.hide();
            stage.close();

            handler.handle(new WritableAsyncResult<ManCenterClient>(new Throwable("abort button")));
        });

        this.loginButton.setOnAction(event -> {
            //reset textfield styles first
            usernameTextField.setStyle(CSS_SUCCESS_STYLE);
            passwordTextField.setStyle(CSS_SUCCESS_STYLE);

            //hide error label
            errorLabel.setVisible(false);

            //check, if username and password are filled
            if (usernameTextField.getText().isEmpty()) {
                usernameTextField.setStyle(CSS_FAILED_STYLE);

                return;
            }

            if (passwordTextField.getText().isEmpty()) {
                passwordTextField.setStyle(CSS_FAILED_STYLE);

                return;
            }

            //get username and password
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();

            //disable login button, so user cannot request more than one logins at the same time
            loginButton.setDisable(true);

            //try to login
            client.login(username, password, res -> {
                if (!res.succeeded()) {
                    //enable login button again
                    loginButton.setDisable(false);

                    //show error label and set text
                    errorLabel.setVisible(true);
                    errorLabel.setText(res.cause().getLocalizedMessage());

                    return;
                }

                //login successful
                this.handler.handle(new WritableAsyncResult<ManCenterClient>(this.client, true));

                //hide and close stage
                stage.hide();
                stage.close();
            });
        });
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
