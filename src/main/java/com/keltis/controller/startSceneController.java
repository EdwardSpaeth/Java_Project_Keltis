package com.keltis.controller;

import com.keltis.SizeOfMonitor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

//test

public class startSceneController {


        private Parent root;
        private Stage window;
        private Scene scene;

        // Background
        @FXML
        private ImageView imageView;


    // Play Button
        @FXML
        public void switchToPlayerNumberScene(MouseEvent mouseEvent) throws IOException {
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

    // Settings Button
        @FXML
        public void SwitchToSettingsScene(MouseEvent mouseEvent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("settingScene.fxml"));
            window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            SizeOfMonitor Size = new SizeOfMonitor();
            window = Size.getSizeOfMonitor(window);

            //VBox layout = new VBox(20);
            //layout.getChildren().addAll(root);
            //layout.setAlignment(Pos.CENTER);
            //Scene scene = new Scene(layout);
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

        @FXML
        void SwitchToRulesScene(MouseEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("RulesScene.fxml"));
            window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
