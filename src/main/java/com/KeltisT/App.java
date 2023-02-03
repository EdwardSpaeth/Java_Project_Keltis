package com.KeltisT;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {

    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        music();
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
        Scene scene = new Scene(root);


        stage.setMaximized(true);
        stage = sizeOfMonitor.setStageSize(stage);
        stage.setScene(scene);
        stage.show();
    }

    public void music(){
        String path ="src/main/resources/Music/backgroundMusic.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loop
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }


}
