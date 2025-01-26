package org.jewelhunt.controllers;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jewelhunt.ai.*;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.Game;
import org.jewelhunt.model.GameTypes;
import org.jewelhunt.ui.AboutView;
import org.jewelhunt.ui.ParametersView;
import org.jewelhunt.ui.WindowApp;
import org.jewelhunt.utils.LoadImages;
import org.jewelhunt.utils.ResourceMessage;

import java.util.Objects;

public class Controller {
    private Game game;
    private final WindowApp windowApp;
    private final Stage stage;
    private final ResourceMessage resourceMessage;
    private final String styleCSS;

    public Controller(Stage stage) {
        this.stage = stage;
        resourceMessage = new ResourceMessage();
        this.game = new Game();
        this.windowApp = new WindowApp(this);
        this.styleCSS = Objects.requireNonNull(getClass().getResource("/css/jewelhunt.css")).toExternalForm();
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

        if(game.isGameOver()) {
            if(button == MouseButton.PRIMARY) {
                if(game.getGameTypes() == GameTypes.GameOfArtificialOpponents) {
                    GameOfArtificialOpponents(game.getNumberAiGames());
                }
            }

            if(button == MouseButton.SECONDARY) {
                newGame();
            }
            return;
        }

        if(game.isCellOpen(line, column)) {
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

    private void GameOfArtificialOpponents(int numberGames) {
        int scoreAi = 0;
        int scoreAiSecond = 0;
        int deadHeat = 0;
        for(int i = 0; i < numberGames; i++) {
            game.newGame();
            while (!game.isGameOver()) {
                game.aiMove();
                if(game.isGameOver()) {
                    continue;
                }
                game.aiMoveSecond();
            }
            
            if(game.getScoreAi() > game.getScoreAiSecond()) {
                scoreAi++;
            }

            if(game.getScoreAi() < game.getScoreAiSecond()) {
                scoreAiSecond++;
            }

            if(game.getScoreAi() == game.getScoreAiSecond()) {
                deadHeat++;
            }
        }
        windowApp.update();
        String s = getMessage(game.getAiOpponent().getType().toString()) + " / " + getMessage(game.getAiSecondOpponent().getType().toString());
        s = s + " : " + scoreAi + " / " + scoreAiSecond + " / " + deadHeat;
        windowApp.setBottomText(s);
    }

    public void showAbout() {
        AboutView.show(this);
    }

    public void showParameters() {
        ParametersView view = new ParametersView();
        view.show(this);
    }

    public void setParameters(GameTypes gameTypes, BoardTypes boardTypes, boolean showBestMoves, int numberAiGames, AiTypes aiAssistant, AiTypes aiOpponent, AiTypes aiSecondOpponent) {
        game = new Game(gameTypes, boardTypes);
        game.setShowBestMoves(showBestMoves);
        game.setNumberAiGames(numberAiGames);

        AiData data = new AiData(boardTypes);
        game.setData(data);
        game.setAiAssistant(AiTypes.ai(aiAssistant, data));
        game.setAiOpponent(AiTypes.ai(aiOpponent, data));
        game.setAiSecondOpponent(AiTypes.ai(aiSecondOpponent, data));
        newGame();
    }

    public Game getGame() {
        return game;
    }

    public Image getImage(String imgName) {
        return LoadImages.getImage(imgName);
    }

    public String getStyleCSS() {
        return styleCSS;
    }
}
