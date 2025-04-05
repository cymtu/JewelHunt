package org.jewelhunt.gametypes;

import javafx.scene.input.MouseButton;
import org.jewelhunt.ai.AiMin;
import org.jewelhunt.ai.IAi;
import org.jewelhunt.ai.Solution;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.Board;
import org.jewelhunt.model.Game;
import org.jewelhunt.ui.WindowApp;

public class ServicePlayWithAI implements ServiceGame {

    private final Controller controller;
    private Player player;

    private IAi aiOpponent;

    public ServicePlayWithAI(Controller controller) {
        this.controller = controller;
        this.player = new Player();
        this.aiOpponent = new AiMin(controller.getGame().getBoardTypes());
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
            return;
        }

        if(controller.getGameTypes() == GameTypes.PlayWithAI) {
            aiMove();
            windowApp.update();
            if(game.isGameOver()) {
                controller.saveResultGame();
            }
        }
    }

    public void aiMove() {
        Game game = controller.getGame();
        Board board = game.getBoard();

        if(game.isGameOver()) {
            return;
        }
        aiOpponent.init(board.getCellState(), board.getCellValues());
        Solution solution = aiOpponent.findSolution();
        game.move(solution.getLine(), solution.getColumns(), aiOpponent);
    }

    public void movePlayer(int line, int column) {
        Game game = controller.getGame();
        game.move(line, column, player);
    }

    public int getScorePlayer() {
        return player.getScore();
    }

    public void resetScore() {
        player.resetScore();
        aiOpponent.resetScore();
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
        return aiOpponent.getScore();
    }

    @Override
    public int getScoreAiSecond() {
        return 0;
    }

    @Override
    public IAi getAiOpponent() {
        return aiOpponent;
    }

    @Override
    public IAi getAiSecondOpponent() {
        return null;
    }

    @Override
    public void setAiOpponent(IAi aiOpponent) {
        this.aiOpponent = aiOpponent;
    }

    @Override
    public void setAiSecondOpponent(IAi aiSecondOpponent) {

    }
}
