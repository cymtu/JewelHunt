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
    private static double DEFAULT_SPACING = 20;
    private static double DEFAULT_WIDTH = 300;

    public static void show(Controller controller) {

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(controller.getMessage("AboutView.Title"));
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox(DEFAULT_SPACING);
        pane.setAlignment(Pos.CENTER);

        Label labProgrammer = new Label(controller.getMessage("AboutView.labProgrammer"));
        HBox paneProgrammer = new HBox(DEFAULT_SPACING);
        paneProgrammer.getChildren().addAll(labProgrammer);
        pane.getChildren().addAll(paneProgrammer);

        Label labEmail = new Label(controller.getMessage("AboutView.labEmail"));
        HBox paneEmail = new HBox(DEFAULT_SPACING);
        paneEmail.getChildren().addAll(labEmail);
        pane.getChildren().addAll(paneEmail);

        Label labGitHub = new Label(controller.getMessage("AboutView.labGitHub"));
        HBox paneGitHub = new HBox(DEFAULT_SPACING);
        paneGitHub.getChildren().addAll(labGitHub);
        pane.getChildren().addAll(paneGitHub);

        Label labYesAiText = new Label(controller.getMessage("AboutView.labYesAiText"));
        HBox paneYesAiText = new HBox(DEFAULT_SPACING);
        paneYesAiText.getChildren().addAll(labYesAiText);
        pane.getChildren().addAll(paneYesAiText);

        Label labYesAi = new Label(controller.getMessage("AboutView.labYesAi"));
        HBox paneYesAi = new HBox(DEFAULT_SPACING);
        paneYesAi.getChildren().addAll(labYesAi);
        pane.getChildren().addAll(paneYesAi);

        Button btnOK = new Button();
        btnOK.setText(controller.getMessage("AboutView.btnOk"));
        btnOK.setOnAction(e -> close() );
        HBox paneBtn = new HBox(DEFAULT_SPACING);
        paneBtn.getChildren().addAll(btnOK);
        pane.getChildren().addAll(paneBtn);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private static void close() {
        stage.close();
    }

}
