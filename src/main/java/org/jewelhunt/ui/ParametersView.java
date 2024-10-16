package org.jewelhunt.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParametersView {
    static Stage stage;
    static boolean btnOkClicked;
    static double DEFAULT_SPACING = 20;
    static double DEFAULT_WIDTH = 300;

    public static boolean show() {
        btnOkClicked = false;
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Параметры");
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox(DEFAULT_SPACING);
        pane.setAlignment(Pos.CENTER);

        Label labBoardTypes = new Label("Размеры игры:");
        HBox paneBoardTypes = new HBox(DEFAULT_SPACING);
        paneBoardTypes.getChildren().addAll(labBoardTypes);
        pane.getChildren().addAll(paneBoardTypes);

        Label labGameTypes = new Label("Тип игры:");
        HBox paneGameTypes = new HBox(DEFAULT_SPACING);
        paneGameTypes.getChildren().addAll(labGameTypes);
        pane.getChildren().addAll(paneGameTypes);

        Button btnOk = new Button();
        btnOk.setText("ОК");
        btnOk.setOnAction(e -> btnOk_Clicked() );
        Button btnCancel = new Button();
        btnCancel.setText("Отмена");
        btnCancel.setOnAction(e -> btnCancel_Clicked() );
        HBox paneBtn = new HBox(DEFAULT_SPACING);
        paneBtn.getChildren().addAll(btnOk, btnCancel);
        pane.getChildren().addAll(paneBtn);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();

        return btnOkClicked;
    }

    private static void btnOk_Clicked() {
        stage.close();
        btnOkClicked = true;
    }

    private static void btnCancel_Clicked() {
        stage.close();
        btnOkClicked = false;
    }
}
