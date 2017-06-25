package com.jukusoft.rpgcreator.editor.javafx;

import com.jukusoft.rpgcreator.editor.javafx.controller.LoginController;
import com.jukusoft.rpgcreator.editor.network.ManCenterClient;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import com.jukusoft.rpgcreator.engine.javafx.FXMLWindow;
import com.jukusoft.rpgcreator.engine.network.AsyncResult;
import com.jukusoft.rpgcreator.engine.network.Handler;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by Justin on 25.06.2017.
 */
public class LoginDialog extends FXMLWindow {

    public LoginDialog(Stage ownerStage, ManCenterClient client, Handler<AsyncResult<ManCenterClient>> handler) {
        super("RPG Creater - by JuKuSoft.com", 550, 300, "./data/editor/JavaFX/Login.fxml", new LoginController(client, handler));

        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        //block parent window while login dialog is opened
        this.stage.initModality(Modality.WINDOW_MODAL);
        this.stage.initOwner(ownerStage);

        this.stage.show();
    }

}
