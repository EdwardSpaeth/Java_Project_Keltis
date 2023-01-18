package com.keltis.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    private Parent root;
    private Stage window;
    private Scene scene;



    // Back Button - Back to Player Number
    @FXML
    public void Back_2(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("2playersNames.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
    @FXML
    public void Back_3(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("3playersNames.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
    @FXML
    public void Back_4(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("4playersNames.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
