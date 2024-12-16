package org.jewelhunt.model;

import org.jewelhunt.ai.AIMinMax;

public class Game {
    private Board board;
    private GameTypes gameTypes;
    private GameStatus status;
    private BoardTypes boardTypes;
    private final AIMinMax aiAssistant;
    private final AIMinMax aiOpponent;
    private final AIMinMax aiSecondOpponent;
    private int numberMoves;
    private int scorePlayer;
    private int scoreAi;
    private int scoreAiSecond;
    private boolean showBestMoves;

    public Game() {
        aiAssistant = new AIMinMax();
        aiOpponent = new AIMinMax();
        aiSecondOpponent = new AIMinMax();
        init();
    }

    public Board getBoard() {
        return board;
    }

    public void init() {
        boardTypes = BoardTypes.Small;
        gameTypes = GameTypes.GameOfArtificialOpponents;
        board = new Board(boardTypes);
        showBestMoves = true;
        newGame();
    }

    public void init(GameTypes gameTypes, BoardTypes boardTypes) {
        this.boardTypes = boardTypes;
        this.gameTypes = gameTypes;
        board = new Board(boardTypes);
        showBestMoves = true;
        newGame();
    }

    public GameTypes getGameTypes() {
        return gameTypes;
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
        scoreAiSecond = 0;
        board.init();
    }

    public void aiMove() {
        int[] cell = aiOpponent.move(board.getVisiblePartOfBoard(), board.sumNotOpenJewels(), board.closedCells());
        board.openCell(cell[0], cell[1]);
        numberMoves++;
        scoreAi += board.getJewel(cell[0], cell[1]).getValue();
    }

    public void aiMoveSecond() {
        int[] cell = aiSecondOpponent.move(board.getVisiblePartOfBoard(), board.sumNotOpenJewels(), board.closedCells());
        board.openCell(cell[0], cell[1]);
        numberMoves++;
        scoreAiSecond += board.getJewel(cell[0], cell[1]).getValue();
    }

    public void movePlayer(int line, int column) {
        board.openCell(line, column);
        numberMoves++;
        scorePlayer += board.getJewel(line, column).getValue();
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

    public int getScorePlayer() {
        return scorePlayer;
    }

    public int getScoreAi() {
        return scoreAi;
    }

    public int getScoreAiSecond() {
        return scoreAiSecond;
    }

    public boolean isShowBestMoves() {
        return showBestMoves;
    }

    public void setShowBestMoves(boolean showBestMoves) {
        this.showBestMoves = showBestMoves;
    }

    public double[][] getBestMoves() {
        int[][] visible = board.getVisiblePartOfBoard();
        double averageValue = ((double) board.sumNotOpenJewels() / board.closedCells());
        return aiAssistant.getCalculation(visible, averageValue);
    }

    public BoardTypes getBoardTypes() {
        return boardTypes;
    }

    public void setGameTypes(GameTypes gameTypes) {
        this.gameTypes = gameTypes;
    }

    public void setBoardTypes(BoardTypes boardTypes) {
        this.boardTypes = boardTypes;
    }

    public int[] getMinMax() {
        return board.getMinMax();
    }

    public AIMinMax getAiAssistant() {
        return aiAssistant;
    }

    public AIMinMax getAiOpponent() {
        return aiOpponent;
    }

    public AIMinMax getAiSecondOpponent() {
        return aiSecondOpponent;
    }
}
