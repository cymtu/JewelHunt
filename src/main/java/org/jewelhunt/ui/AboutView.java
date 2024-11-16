package org.jewelhunt.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jewelhunt.controllers.Controller;

public class AboutView {
    private static Stage stage;
    private static final double DEFAULT_SPACING = 20;
    private static final double DEFAULT_WIDTH = 300;

    public static void show(Controller controller) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(controller.getMessage("AboutView.Title"));
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox(DEFAULT_SPACING);
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(createLabel(controller.getMessage("AboutView.labProgrammer")));
        pane.getChildren().addAll(createLabel(controller.getMessage("AboutView.labEmail")));
        pane.getChildren().addAll(createLabel(controller.getMessage("AboutView.labGitHub")));
        pane.getChildren().addAll(createLabel(controller.getMessage("AboutView.labYesAiText")));
        pane.getChildren().addAll(createLabel(controller.getMessage("AboutView.labYesAi")));

        pane.getChildren().addAll(createButtonOk(controller.getMessage("AboutView.btnOk")));

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private static HBox createLabel(String s) {
        Label lab = new Label(s);
        HBox hBox = new HBox(DEFAULT_SPACING);
        hBox.getChildren().addAll(lab);
        return hBox;
    }

    private static HBox createButtonOk(String s) {
        Button btn = new Button();
        btn.setText(s);
        btn.setOnAction(e -> close() );
        HBox hBox = new HBox(DEFAULT_SPACING);
        hBox.getChildren().addAll(btn);
        return hBox;
    }

    private static void close() {
        stage.close();
    }

}
