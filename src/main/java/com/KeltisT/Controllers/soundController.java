package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class soundController {

    @FXML Slider volumeSlider;
    @FXML
   // private Slider volumeSlider;
    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    String path ="src/main/resources/Music/backgroundmusic.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    //volumeSlider.setValue(mediaPlayer.getVolume()*100);

    @FXML
    void playMusic(MouseEvent event) {
       /* String path ="src/main/resources/com/keltis/img/Music/backgroundmusic.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media); */
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loop
       // mediaPlayer.setAutoPlay(true); für den Start
        mediaPlayer.play();

    }




    @FXML
    void pauseMusic(MouseEvent event) {

        mediaPlayer.pause();

    }

    @FXML
    void muteAll(ActionEvent event) {
    mediaPlayer.setVolume(0);
      //  mediaPlayer.setMute(true);
    }

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
        root = FXMLLoader.load(getClass().getResource("/Fxml/settings.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}


