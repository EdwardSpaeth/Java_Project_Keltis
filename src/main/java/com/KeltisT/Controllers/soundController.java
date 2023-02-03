package com.KeltisT.Controllers;

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

public class soundController {

    @FXML
    public Slider MusicSlider, SFXSlider;
    public Label MusicButton, SFXButton;
    public Text MusicText, SFXText;
    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    static String path ="src/main/resources/Music/backgroundMusic.mp3";
    static Media media = new Media(new File(path).toURI().toString());
    private static MediaPlayer mediaPlayer = new MediaPlayer(media);
    String clickPath ="src/main/resources/Music/chipClick.mp3";
    Media clickMedia = new Media(new File(clickPath).toURI().toString());
    MediaPlayer clickPlayer = new MediaPlayer(clickMedia);
    private static Boolean SFXOff = false;
    private static Boolean MusicOff = false;


    //volumeSlider.setValue(mediaPlayer.getVolume()*100);

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
            mediaPlayer.setVolume(0.1);
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
            clickPlayer.play();
            clickPlayer.setVolume(0.5);
            clickPlayer.getVolume();
        }
    }
    @FXML
    public void wonderStoneSound(){
        if(!SFXOff) {
            String wonderPath = "src/main/resources/Music/wonderStone.mp3";
            Media wonderMedia = new Media(new File(wonderPath).toURI().toString());
            MediaPlayer wonderSound = new MediaPlayer(wonderMedia);
            wonderSound.stop();
            wonderSound.setVolume(0.5);
            wonderSound.getVolume();
            wonderSound.play();
        }
    }

    @FXML
    public void cloverSound(){
        if(!SFXOff) {
            String cloverPath = "src/main/resources/Music/clover.mp3";
            Media cloverMedia = new Media(new File(cloverPath).toURI().toString());
            MediaPlayer cloverSound = new MediaPlayer(cloverMedia);
            cloverSound.stop();
            cloverSound.setVolume(0.5);
            cloverSound.getVolume();
            cloverSound.play();
        }
    }
    @FXML
    public void bonusPointsSound(){
        if(!SFXOff) {
            String bonusPointsPath = "src/main/resources/Music/bonusPoints.mp3";
            Media bonusPointsMedia = new Media(new File(bonusPointsPath).toURI().toString());
            MediaPlayer bonusPointsSounds = new MediaPlayer(bonusPointsMedia);
            bonusPointsSounds.stop();
            bonusPointsSounds.setVolume(0.5);
            bonusPointsSounds.getVolume();
            bonusPointsSounds.play();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                       SFX                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                       Mute                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void muteButton() {
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
    public void play(){
        MusicOff = false;
        playMusic();
        SFXOff = false;
    }

    public void mute(){
        MusicOff = true;
        playMusic();
        SFXOff = true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                 InGame AudioToggle                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    void adjustMusic(ActionEvent event){
      //  volumeSlider.valueProperty().addListener(new);
        // volumeSlider = new Slider();

    }

    @FXML
    void adjustSFX(ActionEvent event){

    }


    @FXML
    public void switchToSettingScene(MouseEvent mouseEvent) throws IOException {
        clickSound();
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/settings.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}


