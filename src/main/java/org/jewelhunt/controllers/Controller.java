package org.jewelhunt.controllers;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.Game;
import org.jewelhunt.model.GameTypes;
import org.jewelhunt.ui.AboutView;
import org.jewelhunt.ui.ParametersView;
import org.jewelhunt.ui.WindowApp;
import org.jewelhunt.utils.LoadImages;
import org.jewelhunt.utils.ResourceMessage;

public class Controller {
    private final Game game;
    private final WindowApp windowApp;
    private final Stage stage;
    private final ResourceMessage resourceMessage;
    private String styleCSS;

    public Controller(Stage stage) {
        this.stage = stage;
        resourceMessage = new ResourceMessage();
        this.game = new Game();
        this.windowApp = new WindowApp(this);
        this.styleCSS = getClass().getResource("/css/jewelhunt.css").toExternalForm();
        LoadImages.load();
        newGame();
    }

    public WindowApp getWindowApp() {
        return windowApp;
    }

    public String getMessage(String key) {
        return resourceMessage.getMessage(key);
    }

    public void setOnMouseClicked(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int line = (int) (y / WindowApp.SIZE_IMAGE);
        int column = (int) (x / WindowApp.SIZE_IMAGE);

        mouseClicked(line, column, mouseEvent.getButton());
    }

    private void mouseClicked(int line, int column, MouseButton button){

        if(game.getGameTypes() == GameTypes.GameOfArtificialOpponents) {
            return;
        }

        if(game.isGameOver()) {
            if(button == MouseButton.SECONDARY) {
                newGame();
            }
            return;
        }

        if(game.getBoard().isCellOpen(line, column)) {
            return;
        }

        if(button == MouseButton.PRIMARY) {
            game.movePlayer(line, column);
        }

        if(button == MouseButton.SECONDARY) {
            game.mark(line, column);
        }

        windowApp.update();

        if(button == MouseButton.SECONDARY) {
            return;
        }

        if(game.getGameTypes() == GameTypes.PlayWithAI) {
            game.aiMove();
            windowApp.update();
        }
    }

    public void exit(){
        Platform.exit();
    }

    public void newGame(){
        game.newGame();
        windowApp.newGame();
        stage.sizeToScene();
        stage.centerOnScreen();
        windowApp.update();
        if(game.getGameTypes() == GameTypes.GameOfArtificialOpponents) {
            GameOfArtificialOpponents();
        }
    }

    private void GameOfArtificialOpponents() {

        while (!game.isGameOver()) {
            game.aiMove();
            windowApp.update();
            if(game.isGameOver()) {
                continue;
            }
            game.aiMoveSecond();
            windowApp.update();
        }

    }

    public void showAbout() {
        AboutView.show(this);
    }

    public void showParameters() {
        ParametersView view = new ParametersView();
        view.show(this);
    }

    public void newGame(GameTypes gameTypes, BoardTypes boardTypes, boolean showBestMoves) {
        game.setGameTypes(gameTypes);
        game.setBoardTypes(boardTypes);
        game.init(gameTypes, boardTypes);
        game.setShowBestMoves(showBestMoves);
        newGame();
    }

    public Game getGame() {
        return game;
    }

    public Image getImage(String imgName) {
        Image img;

        switch (imgName) {
            case "Nugget":
                img = LoadImages.NUGGET;
                break;
            case "Amethyst":
                img = LoadImages.AMETHYST;
                break;
            case "Chrysolite":
                img = LoadImages.CHRYSOLITE;
                break;
            case "Pearl":
                img = LoadImages.PEARL;
                break;
            case "Sapphire":
                img = LoadImages.SAPPHIRE;
                break;
            case "Ruby":
                img = LoadImages.RUBY;
                break;
            case "Closed":
                img = LoadImages.CLOSED;
                break;
            case "Coal":
                img = LoadImages.COAL;
                break;
            default:
                img = LoadImages.OPEN;
        }

        return img;
    }

    public String getStyleCSS() {
        return styleCSS;
    }
}
