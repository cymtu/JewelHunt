package org.jewelhunt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jewelhunt.controllers.Controller;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Controller controller = new Controller(stage);

        var scene = new Scene(controller.getWindowApp());

        scene.getStylesheets().add(controller.getStyleCSS());

        stage.setTitle(controller.getMessage("AppName"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}