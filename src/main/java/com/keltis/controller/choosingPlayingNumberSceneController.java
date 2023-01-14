package com.keltis.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class choosingPlayingNumberSceneController {
    private Parent root;
    private Stage window;
    private Scene scene;
    @FXML
    private ImageView imageView;


    public void switchTo2PlayersSelected(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("2playersnames.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void switchTo3PlayersSelected(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("3playersnames.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void switchTo4PlayersSelected(MouseEvent mouseEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("4playersnames.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    // Back Button - Back to Menu
    @FXML
    public void switchToStartScene(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    // Back Button - Back to Player Number
    @FXML
    public void switchToPlayerNumber(MouseEvent mouseEventevent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choosingPlayerNumber.fxml"));
        window = (Stage)((Node)mouseEventevent.getSource()).getScene().getWindow();
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }



}




