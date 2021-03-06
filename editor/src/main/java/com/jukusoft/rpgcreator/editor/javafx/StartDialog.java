package com.jukusoft.rpgcreator.editor.javafx;

import com.jukusoft.rpgcreator.editor.javafx.controller.StartDialogController;
import com.jukusoft.rpgcreator.engine.javafx.FXMLWindow;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Created by Justin on 30.05.2017.
 */
public class StartDialog extends FXMLWindow {

    public StartDialog() {
        super("RPG Creater - by JuKuSoft.com", 680, 450, "./data/editor/JavaFX/StartDialog.fxml", new StartDialogController());

        //add handler which will be executed, if user closes window
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        //set window position to center and focus window
        this.stage.centerOnScreen();
        this.stage.requestFocus();

        //set window visible
        this.stage.show();
    }

}
