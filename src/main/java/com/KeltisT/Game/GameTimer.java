package com.KeltisT.Game;

import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    //private static int max_seconds = 60;
    private static int max_seconds = 10;
    private static int seconds;
    public static Boolean closed = false;
    public static Boolean paused = false;

    private final Text textField;

    private final GameEngine gameEngine;

    public GameTimer(Text timerText, GameEngine gameEngine_input) {
        textField = timerText;
        gameEngine = gameEngine_input;
        closed = false;
        paused = false;
        seconds = max_seconds;
    }

    public String create_timer_string(int time) {
        int secs = time % 60;
        int mins = (time - secs) / 60;
        String secs_string = "0" + secs;
        String mins_string = "0" + mins;
        if (secs >= 10) {
            secs_string = Integer.toString(secs);
        }
        if (mins >= 10) {
            mins_string = Integer.toString(mins);
        }
        return (mins_string + ":" + secs_string);
    }

    public void refresh() {
        seconds = max_seconds;
        textField.setText(create_timer_string(max_seconds));
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
                    // Timer is running
                    seconds--;
                    textField.setText(create_timer_string(seconds));
                    // Timer reset
                    if (seconds == 0) {
                        gameEngine.skip_turn();
                        refresh();
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
