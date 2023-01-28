package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

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
        AnchorPane pane = new AnchorPane();
        pane = FXMLLoader.load(getClass().getResource("/Fxml/rules.fxml"));
        Path filename  = Path.of("src/main/resources/Rules.txt");
        String rulestext = Files.readString(filename);
        Text text = new Text(rulestext);
        text.setFill(Color.RED);
        text.setStroke(Color.YELLOW);
        text.setStrokeWidth(0.5);
        text.setFont(Font.font("Papyrus", FontWeight.NORMAL, FontPosture.REGULAR, 15.5));
        text.setTextAlignment(TextAlignment.LEFT);
        StackPane stack = new StackPane(text);
        stack.setAlignment(Pos.CENTER);
        stack.setPrefWidth(WIDTH- 370);
        stack.setPrefHeight(HEIGHT + 100);
        pane.getChildren().add(stack);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    // Exit Button
    @FXML
    void Exit(ActionEvent event) {
        Platform.exit();
    }

}
