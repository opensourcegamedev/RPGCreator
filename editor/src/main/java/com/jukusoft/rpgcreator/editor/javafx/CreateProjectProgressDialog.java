package com.jukusoft.rpgcreator.editor.javafx;

import com.jukusoft.rpgcreator.editor.javafx.controller.CreateProjectProgressDialogController;
import com.jukusoft.rpgcreator.engine.javafx.FXMLWindow;
import javafx.event.EventHandler;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Created by Justin on 30.05.2017.
 */
public class CreateProjectProgressDialog extends FXMLWindow {

    public CreateProjectProgressDialog (String gameTitle, String gamePath, String ip, int port, boolean includeAssets) {
        super("RPG Creater - by JuKuSoft.com", 400, 200, "./data/editor/JavaFX/CreateProjectProgressDialog.fxml", new CreateProjectProgressDialogController(gameTitle, gamePath, ip, port, includeAssets));

        //dont draw fullscreen oder minimize buttons on top of window
        this.stage.initStyle(StageStyle.UNDECORATED);

        //set window position to center of screen
        this.stage.centerOnScreen();

        //set window focus
        this.stage.requestFocus();

        //show window, this means window is visible
        this.stage.show();

        //set handler, which will be executed, if user closes window
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

}
