package com.KeltisT.Game;

import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private static int seconds = 60;
    public static Boolean closed = false;
    public static Boolean paused = false;

    private final Text textField;

    private final GameEngine gameEngine;

    public GameTimer(Text timerText, GameEngine gameEngine_input) {
        textField = timerText;
        gameEngine = gameEngine_input;
        closed = false;
        seconds = 60;
    }

    public void refresh() {
        seconds = 60;
        textField.setText("01:00");
    }
    public static void pauseTimer(Boolean iPaused){
        paused = iPaused;
    }
    public static void closeTimer(){
        closed = true;
    }

    public void timer() {
        // If timer reaches 0, a random move is done.

        Timer time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (!paused) {
                    seconds--;
                    //seconds -= 30;
                    // Timer is running
                    if (seconds >= 10) {
                        textField.setText("00:" + seconds);
                    } else {
                        textField.setText("00:0" + seconds);
                    }
                    // Timer reset
                    if (seconds == 0) {
                        gameEngine.skip_turn();
                        textField.setText("01:00");
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

    //Delay For Loading Screen
    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
}
