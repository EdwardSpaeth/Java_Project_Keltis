package com.keltis.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
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

    private Parent root;
    private Stage window;
    private Scene scene;

    @FXML
    private ImageView imageView;

    // Menu Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setResizable(false);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    // Back Button - Back to Player Number
    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choosingPlayerNumber.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setResizable(false);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
