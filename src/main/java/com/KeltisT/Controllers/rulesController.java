package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//still figuring out how to fix the rules text
public class rulesController {
    private final soundController Sounds = new soundController();
    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];

    // Back Button
    public void switchToStart(ActionEvent event) throws IOException {
        Sounds.clickSound();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/start.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

}
