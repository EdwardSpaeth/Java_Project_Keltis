package com.keltis.controller;

import com.keltis.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

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


    private Parent root;
    private Stage window;
    private Scene scene;


    @FXML
    private ImageView imageView;


    public void setNames(String name1, String name2, String name3, String name4 ){
        firstPlayer.setText(name1);
        secondPlayer.setText(name2);
        thirdPlayer.setText(name3);
        fourthPlayer.setText(name4);
    }


    // Start Button
    @FXML
    public void Start(MouseEvent mouseEvent) throws IOException {

        String name = firstPlayer.getText();
        String name2 = secondPlayer.getText();
        String name3 = thirdPlayer.getText();
        String name4 = fourthPlayer.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameController.fxml"));
        root = loader.load();
        Game game = loader.getController();
        game.newNames(name, name2, name3, name4);

        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
    }

    // Menu Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    // Back Button - Back to Player Number
    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choosingPlayerNumber.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
