package org.jewelhunt.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutView {
    private static Stage stage;
    private static double DEFAULT_SPACING = 20;
    private static double DEFAULT_WIDTH = 300;

    public static void show() {

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("О программе...");
        stage.setMinWidth(DEFAULT_WIDTH);

        VBox pane = new VBox(DEFAULT_SPACING);
        pane.setAlignment(Pos.CENTER);

        Label labProgrammer = new Label("Программист : Барабанов Александр Владимирович");
        HBox paneProgrammer = new HBox(DEFAULT_SPACING);
        paneProgrammer.getChildren().addAll(labProgrammer);
        pane.getChildren().addAll(paneProgrammer);

        Label labEmail = new Label("Email : cymtu@yandex.ru");
        HBox paneEmail = new HBox(DEFAULT_SPACING);
        paneEmail.getChildren().addAll(labEmail);
        pane.getChildren().addAll(paneEmail);

        Label labGitHub = new Label("GitHub: https://github.com/cymtu/JewelHunt");
        HBox paneGitHub = new HBox(DEFAULT_SPACING);
        paneGitHub.getChildren().addAll(labGitHub);
        pane.getChildren().addAll(paneGitHub);

        Label labYesAiText = new Label("Все картинки сгенерированы нейронной сетью :");
        HBox paneYesAiText = new HBox(DEFAULT_SPACING);
        paneYesAiText.getChildren().addAll(labYesAiText);
        pane.getChildren().addAll(paneYesAiText);

        Label labYesAi = new Label("YES Ai: https://t.me/yes_ai_official");
        HBox paneYesAi = new HBox(DEFAULT_SPACING);
        paneYesAi.getChildren().addAll(labYesAi);
        pane.getChildren().addAll(paneYesAi);

        Button btnOK = new Button();
        btnOK.setText("ОК");
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
