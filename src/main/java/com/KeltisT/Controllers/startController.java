package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class startController {
    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];

    // Start Button
    public void switchToChoosePlayerNumberScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Fxml/choosePlayer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    // Settings Button

    public void switchToSettingsScene(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("/Fxml/settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    // Rules Button

    public void switchToRulesScene(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("/Fxml/rules.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    // Exit Button
    @FXML
    void Exit(ActionEvent event) {
        Platform.exit();
    }

}
