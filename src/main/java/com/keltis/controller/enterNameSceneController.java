package com.keltis.controller;

import com.keltis.SizeOfMonitor;
import com.keltis.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

//test
import static javafx.application.Application.launch;

public class enterNameSceneController {

    @FXML
    TextField firstPlayer;
    @FXML
    TextField secondPlayer;
    @FXML
    TextField thirdPlayer;
    @FXML
    TextField fourthPlayer;
    @FXML
    Text PlayerText;


    private Parent root;
    private Stage window;
    private Scene scene;


    @FXML
    private ImageView imageView;


    public void setNames(ArrayList<String> playerNames){

        for (String name : playerNames){
            System.out.println(name);
        }
        firstPlayer.setText(playerNames.get(0));
        secondPlayer.setText(playerNames.get(1));

        if (playerNames.size() >= 3){
            thirdPlayer.setText(playerNames.get(2));
        }
        else{
            thirdPlayer.setText("");
        }
        if (playerNames.size() >= 4){
            fourthPlayer.setText(playerNames.get(3));
        }
        else{
            fourthPlayer.setText("");
        }
    }

    public void setTwo(){
        thirdPlayer.setDisable(true);
        fourthPlayer.setDisable(true);
        thirdPlayer.setCursor(Cursor.DEFAULT);
        fourthPlayer.setCursor(Cursor.DEFAULT);
        PlayerText.setText("2 Players");
    }

    public void setThree(){
        thirdPlayer.setDisable(false);
        fourthPlayer.setDisable(true);
        thirdPlayer.setCursor(Cursor.TEXT);
        fourthPlayer.setCursor(Cursor.DEFAULT);
        PlayerText.setText("3 Players");
    }

    public void setFour(){
        thirdPlayer.setDisable(false);
        fourthPlayer.setDisable(false);
        thirdPlayer.setCursor(Cursor.TEXT);
        fourthPlayer.setCursor(Cursor.TEXT);
        PlayerText.setText("4 Players");
    }

    // Start Button
    @FXML
    public void Start(MouseEvent mouseEvent) throws IOException {

        String name = firstPlayer.getText();
        String name2 = secondPlayer.getText();
        String name3 = thirdPlayer.getText();
        String name4 = fourthPlayer.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = loader.load();
        Game game = loader.getController();
        game.newNames(name, name2, name3, name4);

        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        // scene = new Scene(root);
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        window.setScene(scene);
        window.show();
    }

    // Menu Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        //scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    // Back Button - Back to Player Number
    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choosingPlayerNumber.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        //scene = new Scene(root);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}
