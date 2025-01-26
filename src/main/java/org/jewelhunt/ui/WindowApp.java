package org.jewelhunt.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jewelhunt.ai.AiData;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.Game;
import org.jewelhunt.model.GameTypes;

public class WindowApp extends BorderPane {
    public static int SIZE_IMAGE = 64;
    private final ViewApp viewApp;
    private final BottomApp bottomApp;
    private final Controller controller;

    public WindowApp(Controller controller) {
        this.controller = controller;

        MenuApp menuApp = new MenuApp(controller);
        setTop(menuApp);

        viewApp = new ViewApp(controller);
        setCenter(viewApp);

        bottomApp = new BottomApp();
        setBottom(bottomApp);

    }

    private void setBottomText() {
        Game game = controller.getGame();
        String s = "";

        if(game.getNumberMoves() == 0) {
            s = controller.getMessage("WindowApp.NewGame");
        }

        if(game.isGameOver()) {
            s = controller.getMessage("WindowApp.GameOver") ;
        }

        if(game.getGameTypes() == GameTypes.Single) {
            s += controller.getMessage("WindowApp.Move") + game.getNumberMoves() + " " + controller.getMessage("WindowApp.Score") + game.getScorePlayer();
        }

        if(game.getGameTypes() == GameTypes.PlayWithAI) {
            s += controller.getMessage("WindowApp.Move") + game.getNumberMoves() + " " + controller.getMessage("WindowApp.Score") + game.getScorePlayer() + "/" + game.getScoreAi();
        }

        if(game.getGameTypes() == GameTypes.GameOfArtificialOpponents) {
            s += controller.getMessage("WindowApp.Move") + game.getNumberMoves() + " " + controller.getMessage("WindowApp.Score") + game.getScoreAi() + "/" + game.getScoreAiSecond();
        }

        bottomApp.setText(s);
    }

    public void setBottomText(String s) {
        bottomApp.setText(s);
    }

    public void newGame() {
        Game game = controller.getGame();
        viewApp.setHeightCanvas(SIZE_IMAGE * game.getLines());
        viewApp.setWidthCanvas(SIZE_IMAGE * game.getColumns());
    }

    public void update() {
        Game game = controller.getGame();
        for(int i = 0; i < game.getLines(); i++){
            for(int j = 0; j < game.getColumns(); j++){
                drawCell(i, j);
            }
        }

        double sizeFont = 16;
        viewApp.setFont(new Font(sizeFont));

        int[] minMax = game.getMinMax();
        for(int i = 0; i < game.getLines(); i++){
            for(int j = 0; j < game.getColumns(); j++){
                if(game.isCellOpen(i, j)) {
                    if(game.isEmpty(i, j)) {
                        int number = game.getNumber(i, j);
                        viewApp.setStroke(Color.YELLOW);
                        if(number == minMax[0]) {
                            viewApp.setStroke(Color.GREEN);
                        }
                        if(number == minMax[1]) {
                            viewApp.setStroke(Color.RED);
                        }
                        if(number != 0) {
                            double v = SIZE_IMAGE * j + (double)SIZE_IMAGE / 2;
                            double v1 = SIZE_IMAGE * i + (double)SIZE_IMAGE / 2;
                            viewApp.strokeText(String.valueOf(number), v, v1);
                        }
                    }
                }
            }
        }

        viewApp.setStroke(Color.BLACK);

        if (game.isShowBestMoves()) {
            AiData data = game.getBestMoves();
            for(int i = 0; i < game.getLines(); i++){
                for(int j = 0; j < game.getColumns(); j++){
                    if(!game.isCellOpen(i, j) & !game.isMark(i, j)) {
                        double v = SIZE_IMAGE * j + sizeFont;
                        double v1 = SIZE_IMAGE * (i + 1) - sizeFont;
                        viewApp.strokeText(String.format("%3.0f", 100 * data.get(i, j)), v, v1);
                    }
                }
            }
        }

        setBottomText();
    }

    private void drawCell(int line, int column) {
        Game game = controller.getGame();
        if(game.isCellOpen(line, column)) {
            drawCellOpen(line, column);
        } else {
            drawCellClosed(line, column);
        }
    }

    private void drawCellOpen(int line, int column) {
        Game game = controller.getGame();
        viewApp.drawImage(controller.getImage("Open"), SIZE_IMAGE, line, column);

        if(!game.isEmpty(line, column)) {
            Image img = controller.getImage(game.getJewel(line, column).getName());
            viewApp.drawImage(img, SIZE_IMAGE, line, column);
        }
    }

    private void drawCellClosed(int line, int column) {
        Game game = controller.getGame();
        viewApp.drawImage(controller.getImage("Closed"), SIZE_IMAGE, line, column);

        if(game.isMark(line, column)) {
            viewApp.drawImage(controller.getImage("Stone"), SIZE_IMAGE, line, column);
        }
    }
}
