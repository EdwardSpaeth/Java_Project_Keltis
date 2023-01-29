package com.KeltisT.Controllers;

import com.KeltisT.Players.PlayerConfig;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class choosePlayerController {

    private int amount = 4;
    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    @FXML
    private Text PlayerText;

    @FXML
    private RadioButton Players_2, Players_3, Players_4;

    @FXML
    private Button start_button;
    @FXML
    private ImageView PlayerIMG_1, PlayerIMG_2, PlayerIMG_3, PlayerIMG_4, N_PlayerIMG_1, N_PlayerIMG_2, N_PlayerIMG_3, N_PlayerIMG_4;

    @FXML
    private TextField firstPlayer, secondPlayer, thirdPlayer, fourthPlayer;
    public Boolean isPlayer3 = true;
    public Boolean isPlayer4 = true;
    @FXML
    private StackPane gameboard_chips_stackpane;

    // Radio Buttons
    public void chooseAmount(ActionEvent event){

        if(Players_2.isSelected()){
            amount = 2;
            start_button.setDisable(false);
            /*
            isPlayer3 = false;
            isPlayer4 = false;
            PlayerText.setText(amount + " Players");
            thirdPlayer.setCursor(Cursor.DEFAULT);
            fourthPlayer.setCursor(Cursor.DEFAULT);
            ArrayList<String> player_names = com.KeltisT.Players.PlayerConfig.get_player_config(amount);
            firstPlayer.setText(player_names.get(0));
            secondPlayer.setText(player_names.get(1));
            */

        }
        else if(Players_3.isSelected()){
            amount = 3;
            start_button.setDisable(false);
            /*
            isPlayer3 = true;
            isPlayer4 = false;
            PlayerText.setText(amount + " Players");
            thirdPlayer.setCursor(Cursor.TEXT);
            fourthPlayer.setCursor(Cursor.DEFAULT);
            ArrayList<String> player_names = com.KeltisT.Players.PlayerConfig.get_player_config(amount);
            firstPlayer.setText(player_names.get(0));
            secondPlayer.setText(player_names.get(1));

             */

        }
        else if(Players_4.isSelected()){
            amount = 4;
            start_button.setDisable(false);
            /*
            isPlayer3 = true;
            isPlayer4 = true;
            PlayerText.setText(amount + " Players");
            thirdPlayer.setCursor(Cursor.TEXT);
            fourthPlayer.setCursor(Cursor.TEXT);

             */

        }

        firstPlayer.setDisable(false);
        secondPlayer.setDisable(false);
        PlayerIMG_1.setVisible(true);
        PlayerIMG_2.setVisible(true);
        N_PlayerIMG_1.setVisible(false);
        N_PlayerIMG_2.setVisible(false);
        firstPlayer.setCursor(Cursor.TEXT);
        secondPlayer.setCursor(Cursor.TEXT);

        thirdPlayer.setCursor(Cursor.DEFAULT);
        fourthPlayer.setCursor(Cursor.DEFAULT);

        isPlayer3 = true;
        isPlayer4 = true;

        if (amount < 3) {
            isPlayer3 = false;
        }
        if (amount < 4) {
            isPlayer4 = false;
        }
        PlayerText.setText(amount + " Players");

        ArrayList<String> player_names = com.KeltisT.Players.PlayerConfig.get_player_config(amount);
        firstPlayer.setText(player_names.get(0));
        secondPlayer.setText(player_names.get(1));

        if (isPlayer3) {
            thirdPlayer.setText(player_names.get(2));
            thirdPlayer.setCursor(Cursor.TEXT);
        }

        if (isPlayer4) {
            fourthPlayer.setText(player_names.get(3));
            fourthPlayer.setCursor(Cursor.TEXT);
        }
        // Third player settings
        PlayerIMG_3.setVisible(isPlayer3);
        N_PlayerIMG_3.setVisible(!isPlayer3);
        thirdPlayer.setDisable(!isPlayer3);

        // Fourth player settings
        PlayerIMG_4.setVisible(isPlayer4);
        N_PlayerIMG_4.setVisible(!isPlayer4);
        fourthPlayer.setDisable(!isPlayer4);


    }

    public void getAmount(int i_amount){
        i_amount = amount;
    }

    // Next Button
    public void switchToGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/game.fxml"));
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
        stage.show();
        SizeOfMonitor Size = new SizeOfMonitor();
        stage = Size.setStageSize(stage);
       // stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    // Back Button
    public void switchToStart(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
