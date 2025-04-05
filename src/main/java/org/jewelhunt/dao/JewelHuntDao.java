package org.jewelhunt.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.gametypes.ServiceGame;
import org.jewelhunt.model.BoardTypes;
import org.jewelhunt.model.Game;
import org.jewelhunt.gametypes.GameTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class JewelHuntDao {

    private static final String CREATE_TABLE_RECORDS =
            "CREATE TABLE if not exists records (" +
                    " id_game INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " date_game TEXT, " +
                    " game_types TEXT, " +
                    " board_types TEXT, " +
                    " number_moves INTEGER, " +
                    " winner TEXT, " +
                    " loser TEXT, " +
                    " winner_score INTEGER, " +
                    " loser_score INTEGER);";

    private static final String INSERT_GAME =
            "INSERT INTO records(" +
                    " date_game, game_types, board_types, number_moves, winner, loser, winner_score, loser_score)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_GAMES =
            "SELECT rec.id_game, rec.date_game, rec.game_types, " +
                    "rec.board_types, rec.number_moves, rec.winner, rec.loser, " +
                    "rec.winner_score, rec.loser_score " +
                    "FROM records rec " +
                    "WHERE rec.game_types= ? AND rec.board_types = ? " +
                    "ORDER BY rec.number_moves ASC, rec.winner_score DESC LIMIT 10";

    private static final String CLEAR_RECORDS = "DELETE FROM records";

    private static ComboConnectionBuilder comboConnectionBuilder;

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(comboConnectionBuilder == null) {
            comboConnectionBuilder = new ComboConnectionBuilder();
        }
        return comboConnectionBuilder.getConnection();
    }

    public static void clearRecords() {
        try (Statement st = getConnection().createStatement()) {
            st.execute(CLEAR_RECORDS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createRecords() {
        try (Statement st = getConnection().createStatement()) {
            st.execute(CREATE_TABLE_RECORDS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveRecord(Controller controller) {
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT_GAME)) {

            Game game = controller.getGame();
            ServiceGame service = controller.getServiceGame();;


            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date_game = new java.util.Date();
            stmt.setString(1, formater.format(date_game));
            stmt.setString(2, controller.getGameTypes().toString());
            stmt.setString(3, game.getBoardTypes().toString());
            stmt.setInt(4, game.getNumberMoves());

            String winner = "";
            String loser = "";
            int winner_score = 0;
            int loser_score = 0;
            if(service.getScorePlayer() >= service.getScoreAi()) {
                winner = "Player";
                loser = "AI." + service.getAiOpponent().getType().name();
                winner_score = service.getScorePlayer();
                loser_score = service.getScoreAi();
            } else {
                winner = "AI." + service.getAiOpponent().getType().name();
                loser = "Player";
                winner_score = service.getScoreAi();
                loser_score = service.getScorePlayer();
            }
            stmt.setString(5, winner);
            stmt.setString(6, loser);
            stmt.setInt(7, winner_score);
            stmt.setInt(8, loser_score);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Map>  getRecords(GameTypes gameTypes, BoardTypes boardTypes) {
        ObservableList<Map> allData = FXCollections.observableArrayList();

        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SELECT_GAMES)) {
            stmt.setString(1, gameTypes.toString());
            stmt.setString(2, boardTypes.toString());

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Map<String, String> dataRow = new HashMap<>();

                dataRow.put("date_game", rs.getString("date_game"));
                dataRow.put("game_types", rs.getString("game_types"));
                dataRow.put("board_types", rs.getString("board_types"));
                dataRow.put("number_moves", rs.getString("number_moves"));
                dataRow.put("winner", rs.getString("winner"));
                dataRow.put("loser", rs.getString("loser"));
                dataRow.put("winner_score", rs.getString("winner_score"));
                dataRow.put("loser_score", rs.getString("loser_score"));

                allData.add(dataRow);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return allData;
    }
}
