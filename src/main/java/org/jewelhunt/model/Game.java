package org.jewelhunt.model;

import org.jewelhunt.ai.AiData;
import org.jewelhunt.ai.AiMin;
import org.jewelhunt.ai.Solution;

public class Game {
    private Board board;
    private GameTypes gameTypes;
    private GameStatus status;
    private BoardTypes boardTypes;
    private final AiMin aiAssistant;
    private final AiMin aiOpponent;
    private final AiMin aiSecondOpponent;
    private final AiData data;
    private int numberMoves;
    private int scorePlayer;
    private int scoreAi;
    private int scoreAiSecond;
    private boolean showBestMoves;

    public Game() {
        data = new AiData();
        aiAssistant = new AiMin(data);
        aiOpponent = new AiMin(data);
        aiSecondOpponent = new AiMin(data);
        init();
    }

    public Board getBoard() {
        return board;
    }

    public void init() {
        boardTypes = BoardTypes.Small;
        gameTypes = GameTypes.PlayWithAI;
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
        data.init(board.getCellState(), board.getCellValues(), boardTypes);
        Solution solution = aiOpponent.findSolution();
        board.openCell(solution.getLine(), solution.getColumns());
        numberMoves++;
        scoreAi += board.getJewel(solution.getLine(), solution.getColumns()).getValue();
    }

    public void aiMoveSecond() {
        data.init(board.getCellState(), board.getCellValues(), boardTypes);
        Solution solution = aiSecondOpponent.findSolution();
        board.openCell(solution.getLine(), solution.getColumns());
        numberMoves++;
        scoreAiSecond += board.getJewel(solution.getLine(), solution.getColumns()).getValue();
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

    public AiData getBestMoves() {
        data.init(board.getCellState(), board.getCellValues(), boardTypes);
        aiAssistant.calculation();
        return data;
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

    public AiMin getAiAssistant() {
        return aiAssistant;
    }

    public AiMin getAiOpponent() {
        return aiOpponent;
    }

    public AiMin getAiSecondOpponent() {
        return aiSecondOpponent;
    }
}
