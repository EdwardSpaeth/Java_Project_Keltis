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
        private Stage window;
        private Scene scene;

        @FXML
        void exitprogram(MouseEvent mouseEventevent) {
            Platform.exit();
        }

    @FXML
    private ImageView imageView;
        public void initialize(URL url, ResourceBundle rb) {
            Image image = new Image(getClass().getResourceAsStream("WhatsApp Bild 2023-01-11 um 13.02.15.jpg"));
            imageView.setImage(image);

        }
        @FXML
        public void switchToSettingsScene(MouseEvent mouseEvent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("settingsscene.fxml"));
            window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setMaximized(true);

            scene = new Scene(root);
            window.setScene(scene);
            window.show();

        }
        @FXML
    public void switchToOpeningScene(MouseEvent mouseEvent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("openingscene.fxml"));
            window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setMaximized(true);
            scene = new Scene(root);
            window.setScene(scene);
            window.show();
    }

    @FXML
    public void switchToStartScene(MouseEvent mouseEventevent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choosingplayerscene.fxml"));
        window = (Stage)((Node)mouseEventevent.getSource()).getScene().getWindow();
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    //Color paint = new Color(0.08, 0.32, 0.136, 1.0);
    @FXML
    private AnchorPane imageview;


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
}


