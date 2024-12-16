package org.jewelhunt.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jewelhunt.ai.AiTypes;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.*;

public class ParametersView {
    private Stage stage;
    private static final double DEFAULT_SPACING = 20;
    private static final double DEFAULT_WIDTH = 300;
    private ComboBox<GameTypes> cbxGameTypes;
    private ComboBox<BoardTypes> cbxBoardTypes;
    private ComboBox<AiTypes> cbxAiAssistantTypes;
    private ComboBox<AiTypes> cbxAiOpponentTypes;
    private ComboBox<AiTypes> cbxAiSecondOpponentTypes;
    private CheckBox checkBox;
    private Controller controller;
    private Game game;

    public void show(Controller controller) {
        this.controller = controller;
        this.game = controller.getGame();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(controller.getMessage("ParametersView.Title"));
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox(DEFAULT_SPACING);
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
        Label labBoardTypes = new Label(controller.getMessage("ParametersView.labBoardTypes"));
        this.cbxBoardTypes = new ComboBox<>();
        cbxBoardTypes.setItems(FXCollections.observableArrayList( BoardTypes.values()));
        cbxBoardTypes.setConverter(new BoardTypesConverter(controller));
        cbxBoardTypes.setValue(game.getBoardTypes());
        HBox paneBoardTypes = new HBox(DEFAULT_SPACING);
        paneBoardTypes.setPadding(new Insets(10, 10, 10, 10));
        paneBoardTypes.getChildren().addAll(labBoardTypes, cbxBoardTypes);
        return paneBoardTypes;
    }

    private HBox paneGameTypes() {
        Label labGameTypes = new Label(controller.getMessage("ParametersView.labGameTypes"));

        this.cbxGameTypes = new ComboBox<>();
        cbxGameTypes.setItems(FXCollections.observableArrayList(GameTypes.values()));
        cbxGameTypes.setConverter(new GameTypesConverter(controller));
        cbxGameTypes.setValue(game.getGameTypes());

        this.cbxAiOpponentTypes = new ComboBox<>();
        cbxAiOpponentTypes.setItems(FXCollections.observableArrayList(AiTypes.values()));
        cbxAiOpponentTypes.setConverter(new AiTypesConverter(controller));
        cbxAiOpponentTypes.setValue(game.getAiOpponent().getType());

        this.cbxAiSecondOpponentTypes = new ComboBox<>();
        cbxAiSecondOpponentTypes.setItems(FXCollections.observableArrayList(AiTypes.values()));
        cbxAiSecondOpponentTypes.setConverter(new AiTypesConverter(controller));
        cbxAiSecondOpponentTypes.setValue(game.getAiSecondOpponent().getType());

        HBox paneGameTypes = new HBox(DEFAULT_SPACING);
        paneGameTypes.setPadding(new Insets(10, 10, 10, 10));
        paneGameTypes.getChildren().addAll(labGameTypes, cbxGameTypes, cbxAiOpponentTypes, cbxAiSecondOpponentTypes);
        return paneGameTypes;
    }

    private HBox paneShowBestMoves() {
        Label label = new Label(controller.getMessage("ParametersView.showBestMoves"));
        this.checkBox = new CheckBox();
        this.checkBox.setSelected(game.isShowBestMoves());

        this.cbxAiAssistantTypes = new ComboBox<>();
        cbxAiAssistantTypes.setItems(FXCollections.observableArrayList(AiTypes.values()));
        cbxAiAssistantTypes.setConverter(new AiTypesConverter(controller));
        cbxAiAssistantTypes.setValue(game.getAiAssistant().getType());

        HBox hBox = new HBox(DEFAULT_SPACING);
        hBox.setPadding(new Insets(10, 10, 10, 10));
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
        HBox paneBtn = new HBox(DEFAULT_SPACING);
        paneBtn.setPadding(new Insets(10, 10, 10, 10));
        paneBtn.getChildren().addAll(btnOk, btnCancel);
        return paneBtn;
    }

    private void btnOk_Clicked() {
        stage.close();
        controller.newGame(cbxGameTypes.getValue(), cbxBoardTypes.getValue(), checkBox.isSelected());
    }

    private void btnCancel_Clicked() {
        stage.close();
    }


}
