package org.jewelhunt.gametypes;

import javafx.scene.input.MouseButton;
import org.jewelhunt.ai.AiMin;
import org.jewelhunt.ai.IAi;
import org.jewelhunt.ai.Solution;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.Board;
import org.jewelhunt.model.Game;
import org.jewelhunt.ui.WindowApp;

public class ServiceGameOfArtificialOpponents implements ServiceGame {

    private static final int NUMBER_AI_GAMES = 1000;
    private final Controller controller;
    private int numberAiGames;

    private IAi aiOpponent;
    private IAi aiSecondOpponent;

    public ServiceGameOfArtificialOpponents(Controller controller) {
        this.controller = controller;
        this.numberAiGames = NUMBER_AI_GAMES;
        this.aiOpponent = new AiMin(controller.getGame().getBoardTypes());
        this.aiSecondOpponent = new AiMin(controller.getGame().getBoardTypes());
    }

    @Override
    public void mouseClicked(int line, int column, MouseButton button) {
        if(button == MouseButton.PRIMARY) {
            GameOfArtificialOpponents(numberAiGames);
        }

        if(button == MouseButton.SECONDARY) {
            controller.newGame();
            GameOfArtificialOpponents();
        }
    }

    @Override
    public int getScorePlayer() {
        return 0;
    }

    @Override
    public void resetScore() {
        aiOpponent.resetScore();
        aiSecondOpponent.resetScore();
    }

    public void GameOfArtificialOpponents() {
        Game game = controller.getGame();
        WindowApp windowApp = controller.getWindowApp();

        while (!game.isGameOver()) {
            aiMove();
            windowApp.update();
            if(game.isGameOver()) {
                continue;
            }
            aiMoveSecond();
            windowApp.update();
        }

    }

    public void GameOfArtificialOpponents(int numberGames) {
        Game game = controller.getGame();
        WindowApp windowApp = controller.getWindowApp();

        int scoreAi = 0;
        int scoreAiSecond = 0;
        int deadHeat = 0;
        for(int i = 0; i < numberGames; i++) {
            game.newGame();
            while (!game.isGameOver()) {
                aiMove();
                if(game.isGameOver()) {
                    continue;
                }
                aiMoveSecond();
            }

            if(getScoreAi() > getScoreAiSecond()) {
                scoreAi++;
            }

            if(getScoreAi() < getScoreAiSecond()) {
                scoreAiSecond++;
            }

            if(getScoreAi() == getScoreAiSecond()) {
                deadHeat++;
            }
        }
        windowApp.update();
        String s = controller.getMessage(aiOpponent.getType().toString()) + " / " + controller.getMessage(aiSecondOpponent.getType().toString());
        s = s + " : " + scoreAi + " / " + scoreAiSecond + " / " + deadHeat;
        windowApp.setBottomText(s);
    }

    public int getScoreAi() {
        return aiOpponent.getScore();
    }

    public int getScoreAiSecond() {
        return aiSecondOpponent.getScore();
    }

    @Override
    public IAi getAiOpponent() {
        return aiOpponent;
    }

    @Override
    public IAi getAiSecondOpponent() {
        return aiSecondOpponent;
    }

    @Override
    public void setAiOpponent(IAi aiOpponent) {
        this.aiOpponent = aiOpponent;
    }

    @Override
    public void setAiSecondOpponent(IAi aiSecondOpponent) {
        this.aiSecondOpponent = aiSecondOpponent;
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

    public void aiMoveSecond() {
        Game game = controller.getGame();
        Board board = game.getBoard();

        if(game.isGameOver()) {
            return;
        }
        aiSecondOpponent.init(board.getCellState(), board.getCellValues());
        Solution solution = aiSecondOpponent.findSolution();
        game.move(solution.getLine(), solution.getColumns(), aiSecondOpponent);
    }

    public int getNumberAiGames() {
        return numberAiGames;
    }

    public void setNumberAiGames(int numberAiGames) {
        this.numberAiGames = numberAiGames;
    }
}
