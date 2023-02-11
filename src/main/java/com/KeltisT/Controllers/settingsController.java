package com.KeltisT.Controllers;

import com.KeltisT.SettingsConfig.SettingsConfig;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Controller for the settings scene.
 */

public class settingsController {

    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    public Button timerButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    private soundController Sounds = new soundController();

    /**
     * function for the back button in the settings scene.
     * it switches the scene back to start scene.
     */
    public void switchToStart(ActionEvent event) throws IOException {
        Sounds.clickSound();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * function for the music button in the settings scene.
     * it switches the scene to the audio scene.
     */
    public void switchToMusic(MouseEvent event) throws Exception {
        Sounds.clickSound();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/sound.fxml"));
        root = loader.load();
        Sounds = loader.getController();
        Sounds.updateVolume();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button which Toggles the game timer.
     * @param mouseEvent MouseEvent instance
     */
    public void toggleTimer(MouseEvent mouseEvent) {
    public void updateButton(){
        if(!Boolean.valueOf(SettingsConfig.getAudioConfig().get(4))) {
            timerButton.setText("Timer is off");
            timerButton.setBackground(Background.fill(Color.BLACK));
            timerButton.setBorder(Border.stroke(Color.BLACK));
            timerButton.setStyle("-fx-font-size:12;" +
                    "-fx-background-color: grey;" +
                    "-fx-border-color: black;" +
                    "-fx-text-fill: black");
        }
        else{
            timerButton.setText("Timer");
            timerButton.setBackground(Background.fill(Color.RED));
            timerButton.setBorder(Border.stroke(Color.YELLOW));
            timerButton.setStyle("-fx-font-size:18;" +
                    "-fx-background-color: red;" +
                    "-fx-border-color: yellow;" +
                    "-fx-text-fill: yellow");
        }

    }

    public void toggleTimer() {
        ArrayList<String> settings = SettingsConfig.getAudioConfig();
        String timerOn = settings.get(4);
        String negation_of_timer_on = Boolean.toString(!Boolean.valueOf(timerOn));
        settings.set(4, negation_of_timer_on);
        SettingsConfig.setAudioConfig(settings);
        if(!Boolean.valueOf(SettingsConfig.getAudioConfig().get(4))) {
            timerButton.setText("Timer is off");
            timerButton.setBackground(Background.fill(Color.BLACK));
            timerButton.setBorder(Border.stroke(Color.BLACK));
            timerButton.setStyle("-fx-font-size:12;" +
                    "-fx-background-color: grey;" +
                    "-fx-border-color: black;" +
                    "-fx-text-fill: black");
        }
        else{
            timerButton.setText("Timer");
            timerButton.setBackground(Background.fill(Color.RED));
            timerButton.setBorder(Border.stroke(Color.YELLOW));
            timerButton.setStyle("-fx-font-size:18;" +
                    "-fx-background-color: red;" +
                    "-fx-border-color: yellow;" +
                    "-fx-text-fill: yellow");
        }
    }
}
