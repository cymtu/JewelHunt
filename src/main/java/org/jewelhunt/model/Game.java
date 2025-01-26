package org.jewelhunt.model;

import org.jewelhunt.ai.*;

public class Game {
    private static final int NUMBER_AI_GAMES = 1000;
    private final Board board;
    private final GameTypes gameTypes;
    private GameStatus status;
    private IAi aiAssistant;
    private IAi aiOpponent;
    private IAi aiSecondOpponent;
    private AiData data;
    private int numberMoves;
    private int scorePlayer;
    private int scoreAi;
    private int scoreAiSecond;
    private boolean showBestMoves;
    private int numberAiGames;

    public Game() {
        this(GameTypes.PlayWithAI, BoardTypes.Small);
    }

    public Game(GameTypes gameTypes, BoardTypes boardTypes) {
        this.gameTypes = gameTypes;
        this.board = new Board(boardTypes);
        this.data = new AiData(boardTypes);
        this.showBestMoves = true;
        this.numberAiGames = NUMBER_AI_GAMES;
        this.aiAssistant = new AiAverage(data);
        this.aiOpponent = new AiAverage(data);
        this.aiSecondOpponent = new AiMin(data);
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
        return board.getBoardTypes().getColumns();
    }

    public int getLines() {
        return board.getBoardTypes().getLines();
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
        if(isGameOver()) {
            return;
        }
        data.init(board.getCellState(), board.getCellValues());
        Solution solution = aiOpponent.findSolution();
        board.openCell(solution.getLine(), solution.getColumns());
        numberMoves++;
        scoreAi += board.getJewel(solution.getLine(), solution.getColumns()).getValue();
    }

    public void aiMoveSecond() {
        if(isGameOver()) {
            return;
        }
        data.init(board.getCellState(), board.getCellValues());
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
        data.init(board.getCellState(), board.getCellValues());
        aiAssistant.calculation();
        return data;
    }

    public BoardTypes getBoardTypes() {
        return board.getBoardTypes();
    }

    public int[] getMinMax() {
        return board.getMinMax();
    }

    public IAi getAiAssistant() {
        return aiAssistant;
    }

    public IAi getAiOpponent() {
        return aiOpponent;
    }

    public IAi getAiSecondOpponent() {
        return aiSecondOpponent;
    }

    public void setAiAssistant(IAi aiAssistant) {
        this.aiAssistant = aiAssistant;
    }

    public void setAiOpponent(IAi aiOpponent) {
        this.aiOpponent = aiOpponent;
    }

    public void setAiSecondOpponent(IAi aiSecondOpponent) {
        this.aiSecondOpponent = aiSecondOpponent;
    }

    public int getNumberAiGames() {
        return numberAiGames;
    }

    public void setNumberAiGames(int numberAiGames) {
        this.numberAiGames = numberAiGames;
    }

    public void setData(AiData data) {
        this.data = data;
    }
}
