package org.jewelhunt.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jewelhunt.ai.*;
import org.jewelhunt.dao.JewelHuntDao;
import org.jewelhunt.gametypes.ServiceGame;
import org.jewelhunt.gametypes.ServicePlayWithAI;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.Game;
import org.jewelhunt.gametypes.GameTypes;
import org.jewelhunt.ui.AboutView;
import org.jewelhunt.ui.ParametersView;
import org.jewelhunt.ui.RecordsView;
import org.jewelhunt.ui.WindowApp;
import org.jewelhunt.utils.LoadImages;
import org.jewelhunt.utils.ResourceMessage;

import java.util.Map;
import java.util.Objects;

public class Controller {
    private Game game;
    private final WindowApp windowApp;
    private final Stage stage;
    private final ResourceMessage resourceMessage;
    private final String styleCSS;
    private GameTypes gameTypes;
    private ServiceGame serviceGame;
    private IAi aiAssistant;
    private boolean showBestMoves;

    public Controller(Stage stage) {
        this.stage = stage;
        this.gameTypes = GameTypes.PlayWithAI;
        resourceMessage = new ResourceMessage();
        this.game = new Game();
        this.serviceGame = new ServicePlayWithAI(this);
        this.windowApp = new WindowApp(this);
        this.styleCSS = Objects.requireNonNull(getClass().getResource("/css/jewelhunt.css")).toExternalForm();
        this.showBestMoves = true;
        this.aiAssistant = new AiMin(game.getBoardTypes());
        LoadImages.load();
        JewelHuntDao.createRecords();
        loadParameters();
        newGame();
    }

    public ServiceGame getServiceGame() {
        return serviceGame;
    }

    public GameTypes getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(GameTypes gameTypes) {
        this.gameTypes = gameTypes;
    }

    public void loadParameters() {
        // TODO загружаем последние параметры игры
    }

    public WindowApp getWindowApp() {
        return windowApp;
    }

    public String getMessage(String key) {
        return resourceMessage.getMessage(key);
    }

    public void setOnMouseClicked(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int line = (int) (y / WindowApp.SIZE_IMAGE);
        int column = (int) (x / WindowApp.SIZE_IMAGE);

        serviceGame.mouseClicked(line, column, mouseEvent.getButton());
    }

    public void saveResultGame() {
        JewelHuntDao.saveRecord(this);
    }

    public void exit(){
        saveParameters();
        Platform.exit();
    }

    public void saveParameters() {
        // TODO сохраняем параметры игры
    }

    public void newGame(){
        game.newGame();
        windowApp.newGame();
        stage.sizeToScene();
        stage.centerOnScreen();
        windowApp.update();
        serviceGame.resetScore();
    }

    public void showAbout() {
        AboutView.show(this);
    }

    public void showParameters() {
        ParametersView view = new ParametersView();
        view.show(this);
    }

    public void setParameters(GameTypes gameTypes, BoardTypes boardTypes, boolean showBestMoves, int numberAiGames, AiTypes aiAssistant, AiTypes aiOpponent, AiTypes aiSecondOpponent) {
        game = new Game(boardTypes);
        setGameTypes(gameTypes);
        serviceGame = GameTypes.getService(this);
        setShowBestMoves(showBestMoves);
        serviceGame.setNumberAiGames(numberAiGames);

        setAiAssistant(AiTypes.ai(aiAssistant, boardTypes));
        serviceGame.setAiOpponent(AiTypes.ai(aiOpponent, boardTypes));
        serviceGame.setAiSecondOpponent(AiTypes.ai(aiSecondOpponent, boardTypes));
        newGame();
    }

    public Game getGame() {
        return game;
    }

    public Image getImage(String imgName) {
        return LoadImages.getImage(imgName);
    }

    public String getStyleCSS() {
        return styleCSS;
    }

    public void showStatistics() {
        RecordsView view = new RecordsView();
        view.show(this);
    }

    public ObservableList<Map> getRecords(GameTypes gameTypes, BoardTypes boardTypes) {
        return JewelHuntDao.getRecords(gameTypes, boardTypes);
    }

    public void clearRecords() {
        JewelHuntDao.clearRecords();
    }

    public boolean isShowBestMoves() {
        return showBestMoves;
    }

    public void setShowBestMoves(boolean showBestMoves) {
        this.showBestMoves = showBestMoves;
    }

    public AiData getBestMoves() {
        aiAssistant.init(game.getBoard().getCellState(), game.getBoard().getCellValues());
        aiAssistant.calculation();
        return aiAssistant.getData();
    }

    public IAi getAiAssistant() {
        return aiAssistant;
    }

    public void setAiAssistant(IAi aiAssistant) {
        this.aiAssistant = aiAssistant;
    }
}
