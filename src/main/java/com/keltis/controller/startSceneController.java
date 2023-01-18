package com.keltis.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;
public class startSceneController {


        private Parent root;
        private Stage window;
        private Scene scene;

        // Background
        @FXML
        private ImageView imageView;


    // Play Button
        @FXML
        public void switchToPlayerNumberScene(MouseEvent mouseEventevent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("choosingPlayerNumber.fxml"));
            window = (Stage)((Node)mouseEventevent.getSource()).getScene().getWindow();
            window.setMinWidth(1080);
            window.setMinHeight(720);
            window.setMaximized(true);
            scene = new Scene(root);
            window.setScene(scene);
            window.show();
        }

    // Settings Button
        @FXML
        public void SwitchToSettingsScene(MouseEvent mouseEvent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("settingScene.fxml"));
            window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setMinWidth(1080);
            window.setMinHeight(720);
            window.setMaximized(true);
            scene = new Scene(root);
            window.setScene(scene);
            window.show();
        }

        // Exit Button
        @FXML
        void Exit(MouseEvent event) {
        Platform.exit();
    }

        //Color paint = new Color(0.08, 0.32, 0.136, 1.0);
        @FXML
        private AnchorPane imageview;



}