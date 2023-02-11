package com.KeltisT.Controllers;

import com.KeltisT.SettingsConfig.SettingsConfig;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
        ArrayList<String> settings = SettingsConfig.getAudioConfig();
        String timeron = settings.get(4);
        String negation_of_timer_on = Boolean.toString(!Boolean.valueOf(timeron));
        settings.set(4, negation_of_timer_on);
        SettingsConfig.setAudioConfig(settings);
    }
}
