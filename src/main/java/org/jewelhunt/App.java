package org.jewelhunt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.Game;
import org.jewelhunt.ui.BottomApp;
import org.jewelhunt.ui.MenuApp;
import org.jewelhunt.ui.ViewApp;
import org.jewelhunt.utils.LoadImages;

/**
 * JavaFX App
 */
public class App extends Application {

    private ViewApp viewApp;
    private BottomApp bottomApp;
    private Game game;

    private Stage stage;

    @Override
    public void start(Stage stage) {

        this.stage = stage;

        this.game = new Game();

        Controller controller = new Controller(this, game);

        BorderPane borderPane = new BorderPane();

        MenuApp menuApp = new MenuApp(controller);
        borderPane.setTop(menuApp);

        viewApp = new ViewApp(controller);
        borderPane.setCenter(viewApp);

        bottomApp = new BottomApp();
        borderPane.setBottom(bottomApp);

        newGame();
        controller.newGame();
        controller.render();

        var scene = new Scene(borderPane);

        stage.setTitle(controller.getMessage("AppName"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void newGame(){
        viewApp.setHeightCanvas(LoadImages.SIZE_IMAGE * game.getLines());
        viewApp.setWidthCanvas(LoadImages.SIZE_IMAGE * game.getColumns());
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    public ViewApp getViewApp() {
        return viewApp;
    }

    public void setBottomText(String s) {
        bottomApp.setText(s);
    }

    public static void main(String[] args) {
        launch();
    }

}