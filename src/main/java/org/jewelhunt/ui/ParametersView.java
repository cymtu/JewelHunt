package org.jewelhunt.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jewelhunt.ai.AiTypes;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.gametypes.ServiceGame;
import org.jewelhunt.model.*;
import org.jewelhunt.gametypes.GameTypes;

public class ParametersView {
    private Stage stage;
    private static final double DEFAULT_WIDTH = 300;
    private ComboBox<GameTypes> cbxGameTypes;
    private ComboBox<BoardTypes> cbxBoardTypes;
    private ComboBox<AiTypes> cbxAiAssistantTypes;
    private ComboBox<AiTypes> cbxAiOpponentTypes;
    private ComboBox<AiTypes> cbxAiSecondOpponentTypes;
    private CheckBox checkBox;
    private TextField numberAiGames;
    private Controller controller;

    public void show(Controller controller) {
        this.controller = controller;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(controller.getMessage("ParametersView.Title"));
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox();
        pane.getStyleClass().add("vbox");
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(paneBoardTypes());
        pane.getChildren().addAll(paneGameTypes());
        pane.getChildren().addAll(paneShowBestMoves());
        pane.getChildren().addAll(paneBtn());

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(controller.getStyleCSS());
        stage.setScene(scene);
        stage.showAndWait();
    }

    private HBox paneBoardTypes() {
        Game game = controller.getGame();
        Label labBoardTypes = new Label(controller.getMessage("ParametersView.labBoardTypes"));
        this.cbxBoardTypes = new ComboBox<>();
        cbxBoardTypes.setItems(FXCollections.observableArrayList( BoardTypes.values()));
        cbxBoardTypes.setConverter(new BoardTypesConverter(controller));
        cbxBoardTypes.setValue(game.getBoardTypes());
        HBox paneBoardTypes = new HBox();
        paneBoardTypes.getStyleClass().add("hbox");
        paneBoardTypes.getChildren().addAll(labBoardTypes, cbxBoardTypes);
        return paneBoardTypes;
    }

    private HBox paneGameTypes() {
        Game game = controller.getGame();
        ServiceGame service = controller.getServiceGame();
        Label labGameTypes = new Label(controller.getMessage("ParametersView.labGameTypes"));

        this.cbxGameTypes = new ComboBox<>();
        cbxGameTypes.setItems(FXCollections.observableArrayList(GameTypes.values()));
        cbxGameTypes.setConverter(new GameTypesConverter(controller));
        cbxGameTypes.setValue(controller.getGameTypes());
        cbxGameTypes.setOnAction(e -> getActionGameTypes());

        this.cbxAiOpponentTypes = new ComboBox<>();
        cbxAiOpponentTypes.setItems(FXCollections.observableArrayList(AiTypes.values()));
        cbxAiOpponentTypes.setConverter(new AiTypesConverter(controller));
        if (controller.getGameTypes() == GameTypes.PlayWithAI || controller.getGameTypes() == GameTypes.GameOfArtificialOpponents) {
            cbxAiOpponentTypes.setValue(service.getAiOpponent().getType());
        } else {
            cbxAiOpponentTypes.setValue(AiTypes.Min);
            cbxAiOpponentTypes.setVisible(false);
        }

        this.cbxAiSecondOpponentTypes = new ComboBox<>();
        cbxAiSecondOpponentTypes.setItems(FXCollections.observableArrayList(AiTypes.values()));
        cbxAiSecondOpponentTypes.setConverter(new AiTypesConverter(controller));

        numberAiGames = new TextField();

        if (controller.getGameTypes() == GameTypes.GameOfArtificialOpponents) {
            cbxAiSecondOpponentTypes.setValue(service.getAiSecondOpponent().getType());
            numberAiGames.setText(String.valueOf(service.getNumberAiGames()));
        } else {
            cbxAiSecondOpponentTypes.setVisible(false);
            cbxAiSecondOpponentTypes.setValue(AiTypes.Min);
            numberAiGames.setVisible(false);
        }

        HBox paneGameTypes = new HBox();
        paneGameTypes.getStyleClass().add("hbox");
        paneGameTypes.getChildren().addAll(labGameTypes, cbxGameTypes, cbxAiOpponentTypes, cbxAiSecondOpponentTypes, numberAiGames);
        return paneGameTypes;
    }

    private void getActionGameTypes() {
        if(cbxGameTypes.getValue() == GameTypes.Single) {
            cbxAiOpponentTypes.setVisible(false);
            cbxAiSecondOpponentTypes.setVisible(false);
            numberAiGames.setVisible(false);
        }

        if(cbxGameTypes.getValue() == GameTypes.PlayWithAI) {
            cbxAiOpponentTypes.setVisible(true);
            cbxAiSecondOpponentTypes.setVisible(false);
            numberAiGames.setVisible(false);
        }

        if(cbxGameTypes.getValue() == GameTypes.GameOfArtificialOpponents) {
            cbxAiOpponentTypes.setVisible(true);
            cbxAiSecondOpponentTypes.setVisible(true);
            numberAiGames.setVisible(true);
        }
    }

    private HBox paneShowBestMoves() {
        Game game = controller.getGame();
        Label label = new Label(controller.getMessage("ParametersView.showBestMoves"));
        this.checkBox = new CheckBox();
        this.checkBox.setSelected(controller.isShowBestMoves());

        this.cbxAiAssistantTypes = new ComboBox<>();
        cbxAiAssistantTypes.setItems(FXCollections.observableArrayList(AiTypes.values()));
        cbxAiAssistantTypes.setConverter(new AiTypesConverter(controller));
        cbxAiAssistantTypes.setValue(controller.getAiAssistant().getType());

        HBox hBox = new HBox();
        hBox.getStyleClass().add("hbox");
        hBox.getChildren().addAll(label, checkBox, cbxAiAssistantTypes);
        return hBox;
    }

    private HBox paneBtn() {
        Button btnOk = new Button();
        btnOk.setText(controller.getMessage("ParametersView.btnOk"));
        btnOk.setOnAction(e -> btnOk_Clicked() );
        Button btnCancel = new Button();
        btnCancel.setText(controller.getMessage("ParametersView.btnCancel"));
        btnCancel.setOnAction(e -> btnCancel_Clicked() );
        HBox paneBtn = new HBox();
        paneBtn.getStyleClass().add("hbox");
        paneBtn.getChildren().addAll(btnOk, btnCancel);
        return paneBtn;
    }

    private void btnOk_Clicked() {
        stage.close();
        controller.setParameters(cbxGameTypes.getValue(),
                cbxBoardTypes.getValue(),
                checkBox.isSelected(),
                Integer.parseInt(numberAiGames.getText()),
                cbxAiAssistantTypes.getValue(),
                cbxAiOpponentTypes.getValue(),
                cbxAiSecondOpponentTypes.getValue());
    }

    private void btnCancel_Clicked() {
        stage.close();
    }


}
