package org.jewelhunt.model;

import javafx.scene.input.MouseButton;

public class Game {
    private Board board;
    private GameTypes type;
    private GameStatus status;
    private BoardTypes boardTypes;
    private AI ai;
    private int numberMoves;
    private int scorePlayer;
    private int scoreAi;

    public Game() {
        init();
    }

    public Board getBoard() {
        return board;
    }

    private void init() {
        boardTypes = BoardTypes.Small;
        type = GameTypes.PlayWithAI;
        board = new Board(boardTypes);
        ai = new AI();
        newGame();
    }

    public GameTypes getType() {
        return type;
    }

    public boolean isGameOver() {
        if(status == GameStatus.Playing) {
            if(board.getTotalNumberOfMissingJewels() == 0) {
                status = GameStatus.GameOver;
            }
        }
        return status == GameStatus.GameOver;
    }

    public int getColumns() {
        return boardTypes.getColumns();
    }

    public int getLines() {
        return boardTypes.getLines();
    }

    public void newGame() {
        status = GameStatus.Playing;
        numberMoves = 0;
        scorePlayer = 0;
        scoreAi = 0;
        board.init();
    }

    public void aiMove() {
        int[] cell = ai.move(board);
        board.openCell(cell[0], cell[1]);
        numberMoves++;
        scoreAi += board.getJewel(cell[0], cell[1]).getValue();
    }

    public void mouseClicked(int line, int column, MouseButton button) {
        if(button == MouseButton.PRIMARY) {
            board.openCell(line, column);
            numberMoves++;
            scorePlayer += board.getJewel(line, column).getValue();
        }

        if(button == MouseButton.SECONDARY) {
            if(board.isMark(line, column)) {
                board.resetMark(line, column);
            } else {
                board.setMark(line, column);
            }
        }
    }

    public boolean isCellOpen(int line, int column) {
        return board.isCellOpen(line, column);
    }

    public boolean isMark(int line, int column){
        return board.isMark(line, column);
    }

    public int getNumber(int line, int column){
        return board.getNumber(line, column);
    }

    public Jewels getJewel(int line, int column){
        return board.getJewel(line, column);
    }

    public int getNumberMoves() {
        return numberMoves;
    }

    public int getScorePlayer() {
        return scorePlayer;
    }

    public int getScoreAi() {
        return scoreAi;
    }
}
