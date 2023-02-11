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
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Controller for the start scene.
 */

public class startController {
    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    private final soundController Sounds = new soundController();

    /**
     * function for the start button in the start scene.
     * It switches to the choose player scene.
     */
    public void switchToChoosePlayerNumberScene(ActionEvent event) throws IOException {
        Sounds.clickSound();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/choosePlayerT.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * function for the settings button in the start scene.
     * It switches to the settings scene.
     */
    public void switchToSettingsScene(ActionEvent event) throws IOException{
        Sounds.clickSound();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/settings.fxml"));
        root = loader.load();
        settingsController settings = loader.getController();
        settings.updateButton();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * function for the rules button in the start scene.
     * It switches to the rules' scene.
     */
    public void switchToRulesScene(ActionEvent event) throws IOException{
        Sounds.clickSound();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/rules.fxml")));
        Path filename  = Path.of("src/main/resources/Rules.txt");
        String rulesText = Files.readString(filename);
        Text text = new Text(rulesText);
        text.setFill(Color.GOLD);
        text.setStrokeWidth(0.3);
        text.setFont(Font.font("Papyrus", 15));
        text.setTextAlignment(TextAlignment.LEFT);
        StackPane stack = new StackPane(text);
        stack.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(stack, 250.0);
        AnchorPane.setLeftAnchor(stack, 250.0);
        pane.getChildren().add(stack);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * function for the exit button in the start scene.
     * It ends our program.
     */
    @FXML
    void Exit() {
        Sounds.clickSound();
        Platform.exit();
    }

}
