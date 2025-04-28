package org.jewelhunt.gametypes;

import javafx.scene.input.MouseButton;
import org.jewelhunt.ai.IAi;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.Game;
import org.jewelhunt.ui.WindowApp;

public class ServiceSingle implements ServiceGame {

    private final Controller controller;
    private Human human;

    public ServiceSingle(Controller controller) {
        this.controller = controller;
        this.human = new Human();
    }

    @Override
    public void mouseClicked(int line, int column, MouseButton button) {
        Game game = controller.getGame();
        WindowApp windowApp = controller.getWindowApp();

        if(game.isGameOver()) {
            if(button == MouseButton.SECONDARY) {
                controller.newGame();
            }
            return;
        }

        if(game.isCellOpen(line, column)) {
            return;
        }

        if(button == MouseButton.PRIMARY) {
            movePlayer(line, column);
            windowApp.update();
            if(game.isGameOver()) {
                controller.saveResultGame();
                return;
            }
        }

        if(button == MouseButton.SECONDARY) {
            game.mark(line, column);
            windowApp.update();
        }
    }

    @Override
    public int getScorePlayer() {
        return human.getScore();
    }

    @Override
    public void resetScore() {
        human.resetScore();
    }

    @Override
    public int getNumberAiGames() {
        return 0;
    }

    @Override
    public void setNumberAiGames(int numberAiGames) {

    }

    @Override
    public int getScoreAi() {
        return 0;
    }

    @Override
    public int getScoreAiSecond() {
        return 0;
    }

    @Override
    public IAi getAiOpponent() {
        return null;
    }

    @Override
    public IAi getAiSecondOpponent() {
        return null;
    }

    @Override
    public void setAiOpponent(IAi aiOpponent) {

    }

    @Override
    public void setAiSecondOpponent(IAi aiSecondOpponent) {

    }

    public void movePlayer(int line, int column) {
        Game game = controller.getGame();
        game.move(line, column, human);
    }


}
