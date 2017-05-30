package com.jukusoft.rpgcreator.editor.javafx;

import com.jukusoft.rpgcreator.editor.javafx.controller.CreateProjectDialogController;
import com.jukusoft.rpgcreator.editor.javafx.controller.StartDialogController;
import com.jukusoft.rpgcreator.engine.javafx.FXMLController;
import com.jukusoft.rpgcreator.engine.javafx.FXMLWindow;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Created by Justin on 30.05.2017.
 */
public class CreateProjectDialog extends FXMLWindow {

    public CreateProjectDialog() {
        super("RPG Creater - by JuKuSoft.com", 680, 450, "./data/editor/JavaFX/CreateProjectDialog.fxml", new CreateProjectDialogController());

        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

}
