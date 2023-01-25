package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
    private ImageView PlayerIMG_3, PlayerIMG_4, N_PlayerIMG_3, N_PlayerIMG_4;

    @FXML
    private TextField thirdPlayer, fourthPlayer;

    // Radio Buttons
    public void chooseAmount(ActionEvent event){

        Boolean isPlayer3 = true;
        Boolean isPlayer4 = true;

        if(Players_2.isSelected()){
            amount = 2;
            isPlayer3 = false;
            isPlayer4 = false;
            PlayerText.setText(amount + " Players");
            thirdPlayer.setCursor(Cursor.DEFAULT);
            fourthPlayer.setCursor(Cursor.DEFAULT);

        }
        else if(Players_3.isSelected()){
            amount = 3;
            isPlayer3 = true;
            isPlayer4 = false;
            PlayerText.setText(amount + " Players");
            thirdPlayer.setCursor(Cursor.TEXT);
            fourthPlayer.setCursor(Cursor.DEFAULT);

        }
        else if(Players_4.isSelected()){
            isPlayer3 = true;
            isPlayer4 = true;
            amount = 4;
            PlayerText.setText(amount + " Players");
            thirdPlayer.setCursor(Cursor.TEXT);
            fourthPlayer.setCursor(Cursor.TEXT);

        }

        PlayerIMG_3.setVisible(isPlayer3);
        N_PlayerIMG_3.setVisible(!isPlayer3);
        PlayerIMG_4.setVisible(isPlayer4);
        N_PlayerIMG_4.setVisible(!isPlayer4);


    }

    public void getAmount(int i_amount){
        i_amount = amount;
    }

    // Next Button
    public void switchToGame(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Fxml/Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    // Back Button
    public void switchToStart(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

}
