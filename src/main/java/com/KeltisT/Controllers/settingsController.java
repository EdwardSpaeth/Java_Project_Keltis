package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class settingsController {

    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];

    // Back Button
    public void switchToStart(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        System.out.println(WIDTH + " and " + HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
