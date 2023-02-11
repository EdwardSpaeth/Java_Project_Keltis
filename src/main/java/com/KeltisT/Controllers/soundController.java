package com.KeltisT.Controllers;

import com.KeltisT.SettingsConfig.SettingsConfig;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Sound scene controller and audio settings
 */

public class soundController {

    public Label MusicButton, SFXButton;
    public Text MusicText, SFXText;
    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    static String path ="src/main/resources/Music/backgroundMusic.mp3";
    static Media media = new Media(new File(path).toURI().toString());
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);
    static String clickPath ="src/main/resources/Music/chipClick.mp3";
    static Media clickMedia = new Media(new File(clickPath).toURI().toString());
    private static final MediaPlayer clickPlayer = new MediaPlayer(clickMedia);
    static String wonderPath = "src/main/resources/Music/wishStone.mp3";
    static Media wonderMedia = new Media(new File(wonderPath).toURI().toString());
    private static final MediaPlayer wonderSound = new MediaPlayer(wonderMedia);
    static String cloverPath = "src/main/resources/Music/clover.mp3";
    static Media cloverMedia = new Media(new File(cloverPath).toURI().toString());
    private static final MediaPlayer cloverSound = new MediaPlayer(cloverMedia);
    static String bonusPointsPath = "src/main/resources/Music/bonusPoints.mp3";
    static Media bonusPointsMedia = new Media(new File(bonusPointsPath).toURI().toString());
    private static final MediaPlayer bonusPointsSounds = new MediaPlayer(bonusPointsMedia);
    private static Boolean SFXOff = false;
    private static Boolean MusicOff = false;
    public Double MusicVolume = 0.5, SFXVolume = 0.5;
    @FXML
    public Slider MusicSlider, SFXSlider;



    /**
     * This function is for the Music button.
     * It starts and pauses the music settings.
     */
    @FXML
    public void activateDeactivateMusic(){
        if(MusicOff) {
            MusicOff = false;
            MusicButton.setBackground(Background.fill(Color.RED));
            MusicText.setFill(Color.YELLOW);
            MusicText.setText("Music");
            MusicText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        }
        else {
            MusicOff = true;
            MusicButton.setBackground(Background.fill(Color.GREY));
            MusicText.setFill(Color.BLACK);
            MusicText.setText(" Music Paused");
            MusicText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        }
        clickSound();
        playMusic();
    }

    /**
     * This function starts the media player for the music.
     */
    @FXML
    public void playMusic() {
        if(!MusicOff) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(MusicVolume);
            mediaPlayer.getVolume();
            mediaPlayer.play();
        }
        else {
            mediaPlayer.pause();
        }
    }

    /**
     * This function is for the music slider.
     * It toggles the volume of the music.
     */
    @FXML
    public void adjustMusic() {
        MusicSlider.valueProperty().addListener((observableValue, number, t1) -> {
            MusicVolume = MusicSlider.getValue();
            mediaPlayer.setVolume(MusicVolume);
        });
    }

    /**
     * This function is for the SFX Button.
     * It starts and pauses the SFX Settings.
     */
    @FXML
    public void activateClickSound(){
        if(SFXOff) {
            SFXOff = false;
            SFXButton.setBackground(Background.fill(Color.RED));
            SFXText.setFill(Color.YELLOW);
            SFXText.setText("SFX");
            SFXText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 18));
            clickSound();
        }
        else {
            SFXOff = true;
            SFXButton.setBackground(Background.fill(Color.GREY));
            SFXText.setFill(Color.BLACK);
            SFXText.setText("SFX Deactivated");
            SFXText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        }
    }

    /**
     * This function is used for the click sound.
     * It will be used when something gets clicked on.
     */
    @FXML
    public void clickSound() {
        //startMusic();
        if(!SFXOff) {
            clickPlayer.stop();
            clickPlayer.setVolume(SFXVolume);
            clickPlayer.getVolume();
            clickPlayer.play();
        }
    }

    /**
     * This function is used for the wish stone bonus.
     * Everytime someone reveals a wish stone while the game this sound will start.
     */
    @FXML
    public void wishStoneSound(){
        //startMusic();
        if(!SFXOff) {
            wonderSound.stop();
            wonderSound.setVolume(SFXVolume);
            wonderSound.getVolume();
            wonderSound.play();
        }
    }

    /**
     * This function is used for the clover bonus.
     * Everytime someone reveals a clover while the game this sound will start.
     */
    @FXML
    public void cloverSound(){
        //startMusic();
        if(!SFXOff) {
            cloverSound.stop();
            cloverSound.setVolume(SFXVolume);
            cloverSound.getVolume();
            cloverSound.play();
        }
    }

    /**
     * This function is used for the bonus points.
     * Everytime someone reveals a bonus point while the game this sound will start.
     */
    @FXML
    public void bonusPointsSound(){
        //startMusic();
        if(!SFXOff) {
            bonusPointsSounds.stop();
            bonusPointsSounds.setVolume(SFXVolume);
            bonusPointsSounds.getVolume();
            bonusPointsSounds.play();
        }
    }

    /**
     * This function is for the SFX Slider.
     * It adjusts the SFX Volume.
     */
    @FXML
    public void adjustSFX(){
        SFXSlider.valueProperty().addListener((observableValue, number, t1) -> {
            SFXVolume = SFXSlider.getValue();
            clickPlayer.setVolume(SFXVolume);
            bonusPointsSounds.setVolume(SFXVolume);
            cloverSound.setVolume(SFXVolume);
            wonderSound.setVolume(SFXVolume);
        });

    }

    /**
     * This function is for the Mute Button.
     * It mutes every audio.
     */
    @FXML
    public void muteButton() {
        clickSound();
        // Music Mute
        MusicOff = true;
        MusicButton.setBackground(Background.fill(Color.GREY));
        MusicText.setFill(Color.BLACK);
        MusicText.setText("Music Paused");
        MusicText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        playMusic();
        // SFX Mute
        SFXOff = true;
        SFXButton.setBackground(Background.fill(Color.GREY));
        SFXText.setFill(Color.BLACK);
        SFXText.setText("SFX Deactivated");
        SFXText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
    }

    /**
     * This function loads the audio settings and the buttons and sliders.
     */
    @FXML
    public void updateVolume(){
        ArrayList<String> values = SettingsConfig.getAudioConfig();
        MusicVolume = Double.valueOf(values.get(0));
        SFXVolume = Double.valueOf(values.get(1));
        MusicOff = Boolean.valueOf(values.get(2));
        SFXOff = Boolean.valueOf(values.get(3));
        playMusic();
        MusicSlider.setValue(MusicVolume);
        SFXSlider.setValue(SFXVolume);
        if(MusicOff){
            MusicButton.setBackground(Background.fill(Color.GREY));
            MusicText.setFill(Color.BLACK);
            MusicText.setText(" Music Paused");
            MusicText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        }
        if(SFXOff){
            SFXOff = true;
            SFXButton.setBackground(Background.fill(Color.GREY));
            SFXText.setFill(Color.BLACK);
            SFXText.setText("SFX Paused");
            SFXText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        }
    }

    /**
     * This function loads the audio settings at the beginning.
     */
    public void startMusic() {
        ArrayList<String> values = SettingsConfig.getAudioConfig();
        MusicVolume = Double.valueOf(values.get(0));
        SFXVolume = Double.valueOf(values.get(1));
        MusicOff = Boolean.valueOf(values.get(2));
        SFXOff = Boolean.valueOf(values.get(3));
        if(!MusicOff) {
            playMusic();
        }
    }

    /**
     * This function is for the mute button in game.
     * It starts the audio sounds.
     */
    public void play() {
        MusicOff = false;
        playMusic();
        SFXOff = false;
    }

    /**
     * This function is for the mute button in game.
     * It pauses the audio sounds.
     */
    public void mute() {
        MusicOff = true;
        playMusic();
        SFXOff = true;
    }

    /**
     * This function is for the back button in the audio sound scene.
     * It switches the scene back to the settings scene.
     */
    @FXML
    public void switchToSettingScene(MouseEvent mouseEvent) throws IOException {
        clickSound();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/settings.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This function is for the confirm button in the audio sound scene.
     * It saves the audio settings and switches the scene back to the settings scene.
     */
    public void confirmSettings(ActionEvent event) throws IOException {
        ArrayList<String> values = new ArrayList<>(Arrays.asList(
                String.valueOf(MusicVolume),
                String.valueOf(SFXVolume),
                String.valueOf(MusicOff),
                String.valueOf(SFXOff)));
        SettingsConfig.setAudioConfig(values);
        clickSound();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/settings.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}