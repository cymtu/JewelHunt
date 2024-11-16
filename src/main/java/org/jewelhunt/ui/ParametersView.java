package org.jewelhunt.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.BoardTypesConverter;
import org.jewelhunt.model.GameTypes;
import org.jewelhunt.model.GameTypesConverter;

public class ParametersView {
    private Stage stage;
    private static final double DEFAULT_SPACING = 20;
    private static final double DEFAULT_WIDTH = 300;
    private ComboBox<GameTypes> cbxGameTypes;
    private ComboBox<BoardTypes> cbxBoardTypes;
    private Controller controller;

    public void show(Controller controller) {
        this.controller = controller;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(controller.getMessage("ParametersView.Title"));
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox(DEFAULT_SPACING);
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(paneBoardTypes());
        pane.getChildren().addAll(paneGameTypes());
        pane.getChildren().addAll(paneBtn());

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private HBox paneBoardTypes() {
        Label labBoardTypes = new Label(controller.getMessage("ParametersView.labBoardTypes"));
        this.cbxBoardTypes = new ComboBox<>();
        cbxBoardTypes.setItems(FXCollections.observableArrayList( BoardTypes.values()));
        cbxBoardTypes.setConverter(new BoardTypesConverter(controller));
        cbxBoardTypes.setValue(controller.getBoardTypes());
        HBox paneBoardTypes = new HBox(DEFAULT_SPACING);
        paneBoardTypes.getChildren().addAll(labBoardTypes, cbxBoardTypes);
        return paneBoardTypes;
    }

    private HBox paneGameTypes() {
        Label labGameTypes = new Label(controller.getMessage("ParametersView.labGameTypes"));
        this.cbxGameTypes = new ComboBox<>();
        cbxGameTypes.setItems( FXCollections.observableArrayList( GameTypes.values()));
        cbxGameTypes.setConverter(new GameTypesConverter(controller));
        cbxGameTypes.setValue(controller.getGameTypes());
        HBox paneGameTypes = new HBox(DEFAULT_SPACING);
        paneGameTypes.getChildren().addAll(labGameTypes, cbxGameTypes);
        return paneGameTypes;
    }

    private HBox paneBtn() {
        Button btnOk = new Button();
        btnOk.setText(controller.getMessage("ParametersView.btnOk"));
        btnOk.setOnAction(e -> btnOk_Clicked() );
        Button btnCancel = new Button();
        btnCancel.setText(controller.getMessage("ParametersView.btnCancel"));
        btnCancel.setOnAction(e -> btnCancel_Clicked() );
        HBox paneBtn = new HBox(DEFAULT_SPACING);
        paneBtn.getChildren().addAll(btnOk, btnCancel);
        return paneBtn;
    }

    private void btnOk_Clicked() {
        stage.close();
        controller.newGame(cbxGameTypes.getValue(), cbxBoardTypes.getValue());
    }

    private void btnCancel_Clicked() {
        stage.close();
    }


}
