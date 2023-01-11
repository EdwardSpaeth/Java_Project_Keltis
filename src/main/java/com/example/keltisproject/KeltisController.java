package com.example.keltisproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class KeltisController {
        private Parent root;
        private Stage stage;
        private Scene scene;

        @FXML
        void exitprogram(MouseEvent event) {
            Platform.exit();
        }

    @FXML
    private ImageView imageView;
        public void initialize(URL url, ResourceBundle rb) {
            Image image = new Image(getClass().getResourceAsStream("WhatsApp Bild 2023-01-11 um 13.02.15.jpg"));
            imageView.setImage(image);

        }
        @FXML
        public void switchToSettingsScene(MouseEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("settingsscene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        @FXML
    public void switchToOpeningScene(MouseEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("openingscene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    public void switchToStartScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startscene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Color paint = new Color(0.08, 0.32, 0.136, 1.0);
    @FXML
    private AnchorPane imageview;


    public void player2selected(MouseEvent mouseEvent) {

    }
}


