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

public class settingSceneController {
    private Parent root;
    private Stage window;
    private Scene scene;
    @FXML
    private ImageView imageView;

        // Back Menu
        @FXML
        public void switchToStartScene(MouseEvent mouseEvent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
            window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setResizable(false);
            scene = new Scene(root);
            window.setScene(scene);
            window.show();
    }
}