package com.KeltisT.Game;

import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private static int seconds = 60;
    Boolean closed = false, paused = false;

    private Text textfield;

    private GameEngine gameEngine;
    public GameTimer(Text timerText, GameEngine gameEngine_input) {
        textfield = timerText;
        gameEngine = gameEngine_input;
    }

    public void refresh() {
        seconds = 60;
        textfield.setText("01:00");
    }
    public void timer() {

        // 1.Bedingung Timer läuft ab = nächster Spieler
        // 4.Bedingung Chip wurde genommen = Timer reset und nächster Spieler

        Timer time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            //int seconds = 60;

            @Override
            public void run() {
                if (!paused) {
                    seconds--;
                    // Timer is running
                    if (seconds >= 10) {
                        textfield.setText("00:" + seconds);
                    } else if (seconds < 10) {
                        textfield.setText("00:0" + seconds);
                    }
                    // Timer reset
                    if (seconds == 0) {
                        gameEngine.next_turn(Boolean.FALSE);
                        textfield.setText("01:00");
                        seconds = 60;
                    }
                    // Timer ends
                    if (closed) {
                        time.cancel();
                    }
                }
            }
        }, 1000, 1000);

    }
}
