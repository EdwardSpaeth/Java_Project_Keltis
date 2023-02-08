package com.KeltisT.Controllers;

import com.KeltisT.SettingsConfig.SettingsConfig;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.Objects;

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





    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                     Music                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    @FXML
    public void playMusic() {
        if(!MusicOff) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loop
            mediaPlayer.setVolume(MusicVolume);
            mediaPlayer.getVolume();
            mediaPlayer.play();
        }
        else {
            mediaPlayer.pause();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                     Music                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                       SFX                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    @FXML
    public void clickSound() {
        if(!SFXOff) {
            clickPlayer.stop();
            clickPlayer.setVolume(SFXVolume);
            clickPlayer.getVolume();
            clickPlayer.play();
        }
    }
    @FXML
    public void wishStoneSound(){
        if(!SFXOff) {
            wonderSound.stop();
            wonderSound.setVolume(SFXVolume);
            wonderSound.getVolume();
            wonderSound.play();
        }
    }
    @FXML
    public void cloverSound(){
        if(!SFXOff) {
            cloverSound.stop();
            cloverSound.setVolume(SFXVolume);
            cloverSound.getVolume();
            cloverSound.play();
        }
    }
    @FXML
    public void bonusPointsSound(){
        if(!SFXOff) {
            bonusPointsSounds.stop();
            bonusPointsSounds.setVolume(SFXVolume);
            bonusPointsSounds.getVolume();
            bonusPointsSounds.play();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                       SFX                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                      Mute                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void muteButton() throws Exception {
        clickSound();
        // Music Mute
        MusicOff = true;
        MusicButton.setBackground(Background.fill(Color.GREY));
        MusicText.setFill(Color.BLACK);
        MusicText.setText(" Music Paused");
        MusicText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        playMusic();
        // SFX Mute
        SFXOff = true;
        SFXButton.setBackground(Background.fill(Color.GREY));
        SFXText.setFill(Color.BLACK);
        SFXText.setText("SFX Deactivated");
        SFXText.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 10));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                       Mute                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                 InGame AudioToggle                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void play() throws Exception {
        MusicOff = false;
        playMusic();
        SFXOff = false;
    }

    public void mute() throws Exception {
        MusicOff = true;
        playMusic();
        SFXOff = true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                 InGame AudioToggle                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @FXML
    public void adjustMusic(){
        DoubleProperty volume = new SimpleDoubleProperty();
      /*  volumeSlider.valueProperty().bindBidirectional(volume);
        volumeSlider.setMin(0.0);
        volumeSlider.setMax(1);
        volume.bindBidirectional(mediaPlayer.volumeProperty());
        mediaPlayer.play(); */
        MusicSlider.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                MusicVolume = MusicSlider.getValue();
                mediaPlayer.setVolume(MusicVolume);
            }
        });
          }


    @FXML
    public void adjustSFX(){
        SFXSlider.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                SFXVolume = SFXSlider.getValue();
                clickPlayer.setVolume(SFXVolume);
                bonusPointsSounds.setVolume(SFXVolume);
                cloverSound.setVolume(SFXVolume);
                wonderSound.setVolume(SFXVolume);
            }
        });

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                 Controller Function                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void switchToSettingScene(MouseEvent mouseEvent) throws IOException {
        clickSound();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/settings.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                 Controller Function                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateVolume(){
        ArrayList<String> values = SettingsConfig.getAudioConfig();
        MusicVolume = Double.valueOf(values.get(0));
        SFXVolume = Double.valueOf(values.get(1));
        System.out.println(MusicOff);
        MusicOff = Boolean.valueOf(values.get(2));
        System.out.println(MusicOff);
        SFXOff = Boolean.valueOf(values.get(3));
        playMusic();
        MusicSlider.setValue(MusicVolume);
        SFXSlider.setValue(SFXVolume);
        if(MusicOff){
            MusicButton.setBackground(Background.fill(Color.BLACK));
        }
        if(SFXOff){
            SFXButton.setBackground(Background.fill(Color.BLACK));
        }
    }
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



