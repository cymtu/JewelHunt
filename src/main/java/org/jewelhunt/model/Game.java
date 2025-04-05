package org.jewelhunt.model;

import org.jewelhunt.gametypes.Score;

public class Game {
    private final Board board;
    private GameStatus status;
    private int numberMoves;

    public Game() {
        this(BoardTypes.Small);
    }

    public Game(BoardTypes boardTypes) {
        this.board = new Board(boardTypes);
        newGame();
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
        return board.getBoardTypes().getColumns();
    }

    public int getLines() {
        return board.getBoardTypes().getLines();
    }

    public void newGame() {
        status = GameStatus.Playing;
        numberMoves = 0;
        board.init();
    }

    public void move(int line, int column, Score score) {
        board.openCell(line, column);
        numberMoves++;
        score.addScore(board.getJewel(line, column).getValue());
    }

    public void mark(int line, int column) {
        if(board.isMark(line, column)) {
            board.resetMark(line, column);
        } else {
            board.setMark(line, column);
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

    public Jewels getJewel(int line, int column) {
        return board.getJewel(line, column);
    }

    public boolean isEmpty(int line, int column) {
        return board.isEmpty(line, column);
    }

    public int getNumberMoves() {
        return numberMoves;
    }

    public BoardTypes getBoardTypes() {
        return board.getBoardTypes();
    }

    public int[] getMinMax() {
        return board.getMinMax();
    }

    public Board getBoard() {
        return board;
    }
}
