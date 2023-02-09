package com.KeltisT.Game;

import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which handles the timer.
 */
public class GameTimer {
    private static int max_seconds = 60;
    private static int seconds;
    public static Boolean closed = false;
    public static Boolean paused = false;
    private final Text textField;
    private final GameEngine gameEngine;

    /**
     * Constructor
     * @param timerText label which is updates with time left
     * @param gameEngine_input reference to the GameEngine instance, which called it, so that it is able to call the skip_turn() function
     */
    public GameTimer(Text timerText, GameEngine gameEngine_input) {
        textField = timerText;
        gameEngine = gameEngine_input;
        closed = false;
        paused = false;
        seconds = max_seconds;
    }

    /**
     * Creates a String displaying time left in format "MM:SS"
     * @param time time for which a string is to be created in seconds
     * @return the correspondingly formatted string
     */
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

    /**
     * Refreshes the timer.
     */
    public void refresh() {
        seconds = max_seconds;
        textField.setText(create_timer_string(max_seconds));
    }

    /**
     * Pauses/unpauses the timer when the "P"-button is pressed.
     * @param iPaused boolean of whether to pause or unpause
     */
    public static void pauseTimer(Boolean iPaused){
        paused = iPaused;
    }

    /**
     * Closes the timer when the application is exited.
     */
    public static void closeTimer(){
        closed = true;
    }

    /**
     * Initiates a timer and gives it a Task so that it can work in the background.
     */
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

    /**
     * Function which puts the application to sleep in order for the loading screen to work.
     * @param millis amount of milliseconds which it has to sleep for
     * @param continuation Runnable object
     */
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
