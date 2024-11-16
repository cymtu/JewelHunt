package org.jewelhunt.controllers;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.jewelhunt.App;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.Game;
import org.jewelhunt.model.GameTypes;
import org.jewelhunt.model.Jewels;
import org.jewelhunt.ui.AboutView;
import org.jewelhunt.ui.ParametersView;
import org.jewelhunt.ui.ViewApp;
import org.jewelhunt.utils.LoadImages;
import org.jewelhunt.utils.ResourceMessage;

public class Controller {
    private final Game game;

    private final App app;

    private final ResourceMessage resourceMessage;

    private boolean sellIsAlreadyOpen = false;

    public Controller(App app, Game game) {
        this.app = app;
        this.game = game;
        resourceMessage = new ResourceMessage();
        LoadImages.load();
    }

    public String getMessage(String key) {
        return resourceMessage.getMessage(key);
    }

    public void setOnMouseClicked(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int line = (int) (y / LoadImages.SIZE_IMAGE);
        int column = (int) (x / LoadImages.SIZE_IMAGE);

        mouseClicked(line, column, mouseEvent.getButton());
        outputGameState();
    }

    private void mouseClicked(int line, int column, MouseButton button){
        if(game.isGameOver()) {
            if(button == MouseButton.SECONDARY) {
                newGame();
            }
            return;
        }

        if(game.getBoard().isCellOpen(line, column)) {
            sellIsAlreadyOpen = true;
            return;
        }

        game.mouseClicked(line, column, button);
        render();

        if(button == MouseButton.SECONDARY) {
            return;
        }

        if(game.getGameTypes() == GameTypes.PlayWithAI) {
            game.aiMove();
            render();
        }
    }

    public void exit(){
        Platform.exit();
    }

    public void newGame(){
        game.newGame();
        app.newGame();
        outputGameState();
        render();
    }

    private void outputGameState() {
        String s = "";

        if(game.getNumberMoves() == 0) {
            s = getMessage("Controller.NewGame");
        }

        if(game.isGameOver()) {
            s = getMessage("Controller.GameOver") ;
        }

        if(sellIsAlreadyOpen && !game.isGameOver()) {
            sellIsAlreadyOpen = false;
            s = getMessage("Controller.CellIsAlreadyOpen");
        }

        s += getMessage("Controller.Move") + game.getNumberMoves() + " " + getMessage("Controller.Score") + game.getScorePlayer() + "/" + game.getScoreAi();

        app.setBottomText(s);
    }

    public void render(){
        for(int i = 0; i < game.getLines(); i++){
            for(int j = 0; j < game.getColumns(); j++){
                drawCell(i, j);
            }
        }

        ViewApp viewApp = app.getViewApp();
        int[] minMax = game.getMinMax();
        for(int i = 0; i < game.getLines(); i++){
            for(int j = 0; j < game.getColumns(); j++){
                if(game.isCellOpen(i, j)) {
                    if(game.getJewel(i, j) == Jewels.Empty) {
                        int number = game.getNumber(i, j);
                        viewApp.setStroke(Color.YELLOW);
                        if(number == minMax[0]) {
                            viewApp.setStroke(Color.GREEN);
                        }
                        if(number == minMax[1]) {
                            viewApp.setStroke(Color.RED);
                        }
                        if(number != 0) {
                            viewApp.strokeText(String.valueOf(number), LoadImages.SIZE_IMAGE, i, j);
                        }
                    }
                }
            }
        }

        viewApp.setStroke(Color.BLACK);

        if (game.isShowBestMoves()) {
            double[][] calculation = game.getBestMoves(game.getBoard());
            for(int i = 0; i < game.getLines(); i++){
                for(int j = 0; j < game.getColumns(); j++){
                    double v = LoadImages.SIZE_IMAGE * j;
                    double v1 = LoadImages.SIZE_IMAGE * (i + 1) - 4;
                    viewApp.strokeText(String.format("%3.0f", 100*calculation[i][j]), v, v1);
                }
            }
        }
    }

    private void drawCell(int line, int column) {
        if(game.isCellOpen(line, column)) {
            drawCellOpen(line, column);
        } else {
            drawCellClosed(line, column);
        }
    }

    private void drawCellOpen(int line, int column) {
        ViewApp viewApp = app.getViewApp();
        viewApp.drawImage(LoadImages.OPEN, LoadImages.SIZE_IMAGE, line, column);

        if(game.getJewel(line, column) != Jewels.Empty) {
            Image img = LoadImages.getImage(game.getBoard().getJewel(line, column));
            viewApp.drawImage(img, LoadImages.SIZE_IMAGE, line, column);
        }
    }

    private void drawCellClosed(int line, int column) {
        ViewApp viewApp = app.getViewApp();
        viewApp.drawImage(LoadImages.CLOSED, LoadImages.SIZE_IMAGE, line, column);

        if(game.isMark(line, column)) {
            viewApp.drawImage(LoadImages.COAL, LoadImages.SIZE_IMAGE, line, column);
        }
    }

    public void showAbout() {
        AboutView.show(this);
    }

    public void showParameters() {
        ParametersView view = new ParametersView();
        view.show(this);
    }

    public GameTypes getGameTypes() {
        return game.getGameTypes();
    }

    public BoardTypes getBoardTypes () {
        return game.getBoardTypes();
    }

    public void newGame(GameTypes gameTypes, BoardTypes boardTypes) {
        game.setGameTypes(gameTypes);
        game.setBoardTypes(boardTypes);
        game.init(gameTypes, boardTypes);
        newGame();
    }
}
