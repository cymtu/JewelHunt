package org.jewelhunt.ui;

import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.Game;
import org.jewelhunt.gametypes.GameTypes;

public class RecordsView {
    private Stage stage;
    private static final double DEFAULT_WIDTH = 700;
    private Controller controller;
    private ComboBox<GameTypes> cbxGameTypes;
    private ComboBox<BoardTypes> cbxBoardTypes;
    private TableView table;

    public void show(Controller controller) {
        this.controller = controller;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(controller.getMessage("RecordsView.Title"));
        stage.setMinWidth(DEFAULT_WIDTH);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(paneGameTypes(), paneBoardTypes());

        table = createTableView();

        HBox buttonClear = getButtonClear(controller);
        hBox.getChildren().addAll(buttonClear);

        BorderPane borderPane = new BorderPane();
        borderPane.autosize();
        borderPane.setTop(hBox);
        borderPane.setCenter(table);

        Group root = new Group();
        root.getChildren().addAll(borderPane);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(controller.getStyleCSS());
        stage.setScene(scene);
        stage.showAndWait();
    }

    private HBox getButtonClear(Controller controller) {
        Button buttonClear = new Button(controller.getMessage("RecordsView.Clear"));
        buttonClear.setOnAction(e -> {
            controller.clearRecords(); table.setItems(controller.getRecords(cbxGameTypes.getValue(), cbxBoardTypes.getValue()));});

        HBox hBox = new HBox();
        hBox.getStyleClass().add("hbox");
        hBox.getChildren().addAll(buttonClear);
        return hBox;
    }

    private HBox paneBoardTypes() {
        Game game = controller.getGame();
        Label labBoardTypes = new Label(controller.getMessage("RecordsView.labBoardTypes"));
        this.cbxBoardTypes = new ComboBox<>();
        cbxBoardTypes.setItems(FXCollections.observableArrayList( BoardTypes.values()));
        cbxBoardTypes.setConverter(new BoardTypesConverter(controller));
        cbxBoardTypes.setValue(game.getBoardTypes());
        cbxBoardTypes.setOnAction(e -> {
            table.setItems(controller.getRecords(cbxGameTypes.getValue(), cbxBoardTypes.getValue()));
        });

        HBox hBox = new HBox();
        hBox.getStyleClass().add("hbox");
        hBox.getChildren().addAll(labBoardTypes, cbxBoardTypes);
        return hBox;
    }

    private HBox paneGameTypes() {
        Game game = controller.getGame();
        Label labGameTypes = new Label(controller.getMessage("RecordsView.labGameTypes"));

        this.cbxGameTypes = new ComboBox<>();
        cbxGameTypes.setItems(FXCollections.observableArrayList(GameTypes.values()));
        cbxGameTypes.setConverter(new GameTypesConverter(controller));
        cbxGameTypes.setValue(controller.getGameTypes());
        cbxGameTypes.setOnAction(e -> {
            table.setItems(controller.getRecords(cbxGameTypes.getValue(), cbxBoardTypes.getValue()));
        });

        HBox hBox = new HBox();
        hBox.getStyleClass().add("hbox");
        hBox.getChildren().addAll(labGameTypes, cbxGameTypes);
        return hBox;
    }

    private TableView createTableView() {
        TableView table = new TableView();
        table.setEditable(true);

        TableColumn dateCol = createTableColumn("RecordsView.Date","date_game");
        dateCol.setPrefWidth(150);

        TableColumn gameTypesCol = createTableColumn("RecordsView.GameTypes","game_types");
        gameTypesCol.setPrefWidth(150);

        TableColumn boardTypesCol = createTableColumn("RecordsView.BoardTypes","board_types");
        boardTypesCol.setPrefWidth(150);

        TableColumn numberMovesCol = createTableColumn("RecordsView.NumberMoves","number_moves");
        numberMovesCol.setPrefWidth(150);

        TableColumn winnerCol = createTableColumn("RecordsView.Winner","winner");
        winnerCol.setPrefWidth(150);

        TableColumn loserCol = createTableColumn("RecordsView.Loser","loser");
        loserCol.setPrefWidth(150);

        TableColumn winnerScoreCol = createTableColumn("RecordsView.WinnerScore","winner_score");
        winnerScoreCol.setPrefWidth(150);

        TableColumn loserScoreCol = createTableColumn("RecordsView.LoserScore","loser_score");
        loserScoreCol.setPrefWidth(150);

        table.getColumns().addAll(dateCol, gameTypesCol, boardTypesCol, numberMovesCol, winnerCol, loserCol, winnerScoreCol, loserScoreCol);

        table.setItems(controller.getRecords(cbxGameTypes.getValue(), cbxBoardTypes.getValue()));

        return table;
    }

    private TableColumn createTableColumn(String keyMessage, String mapValue) {
        TableColumn tableColumn = new TableColumn(controller.getMessage(keyMessage));
        tableColumn.setCellValueFactory(new MapValueFactory(mapValue));
        return tableColumn;
    }
}
