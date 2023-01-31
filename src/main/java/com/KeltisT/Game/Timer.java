package com.KeltisT.Game;
import com.KeltisT.Controllers.gameController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Timer implements Initializable {

    @FXML
    public static Label TimerLabel;

    private static int seconds = 60;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimerLabel.setText("01:00");
    }

    public static void initializee(Label time) {
        TimerLabel = time;
        System.out.println(TimerLabel);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e-> {
            seconds--;
            if (seconds >= 10){
                TimerLabel.setText("00:" + String.valueOf(seconds));
            }
            else if(seconds < 10) {
                TimerLabel.setText("00:0" + String.valueOf(seconds));
            }
            if(seconds == 0) {
                TimerLabel.setText("01:00");
                seconds = 60;
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
}
