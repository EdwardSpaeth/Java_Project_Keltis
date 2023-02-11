package com.KeltisT.Window;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Class which gives us the monitor size and let us set the window.
 */

public class SizeOfMonitor extends Application {

    // Not relevant
    @Override
        public void start(Stage primaryStage) {
        //Get primary screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);
    }

    /**
     * Getting Sizes of Monitor
     * @return the height and width of the user monitor in an array.
     */
        public double[] getSizeOfMonitor(){
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double HEIGHT = primaryScreenBounds.getHeight();
            double WIDTH = primaryScreenBounds.getWidth();
            return new double[]{HEIGHT, WIDTH};
        }

    /**
     * @param stage is given from the app class.
     * @return sets the stage for our program.
     */
        public Stage setStageSize(Stage stage) {

            //Size of Window
            stage.setMinHeight(800);
            stage.setMinWidth(1280);
            stage.setMaximized(true);

            //Icon and Title
            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Keltis");

            return stage;
        }

}