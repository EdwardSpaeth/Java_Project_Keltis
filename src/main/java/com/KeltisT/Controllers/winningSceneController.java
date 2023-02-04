package com.KeltisT.Controllers;

import com.KeltisT.Game.GameEngine;
import com.KeltisT.Game.GameTimer;
import com.KeltisT.Players.Player;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class winningSceneController {

    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    public Text winnerT, secondPlaceT, thirdPlaceT, fourthPlaceT;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    private soundController Sounds = new soundController();

    @FXML
    // Next Button
    public void AgainButton(ActionEvent event) throws IOException {
        /*Sounds.clickSound();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/gameT.fxml"));
        root = loader.load();

        ArrayList<String> chosen_player_names = new ArrayList<>();
        chosen_player_names.add(firstPlayer.getText());
        chosen_player_names.add(secondPlayer.getText());
        if (amount >= 3) {
            chosen_player_names.add(thirdPlayer.getText());
        }
        if (amount >= 4) {
            chosen_player_names.add(fourthPlayer.getText());
        }
        PlayerConfig.set_player_config(chosen_player_names);

        gameController GameController = loader.getController();
        GameController.setPlayer_3_4(amount);
        GameController.setChipField(amount);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root, WIDTH, HEIGHT);

        GameController.getKeyControls(scene);

        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("l"));
        stage.setFullScreen(true);
        stage.show();
*/
    }
    private static ArrayList<Player> players_in_order;
    public static void add_players_in_order(ArrayList<Player> players_in_order_input) {
        players_in_order = players_in_order_input;
    }

    @FXML
    public void MenuButton(ActionEvent event) throws IOException{
        Sounds.clickSound();
        GameTimer.closeTimer();
        root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
        for (Player p : players_in_order) {
            System.out.println(p.get_name());
        }

    }

    @FXML
    void ExitButton() {
        Sounds.clickSound();
        GameTimer.closeTimer();
        Platform.exit();

    }


}
