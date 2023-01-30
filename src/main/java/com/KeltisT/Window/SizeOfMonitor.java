package com.KeltisT.Window;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class SizeOfMonitor extends Application {

    @Override
        public void start(Stage primaryStage) {
        //Get primary screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);
    }

        // Getting Sizes of Monitor
        public double[] getSizeOfMonitor(){
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            /*
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.setWidth(primaryScreenBounds.getWidth());
            stage.setHeight(primaryScreenBounds.getHeight());
            System.out.println(primaryScreenBounds.getMinX());
            System.out.println(primaryScreenBounds.getMaxX());
            System.out.println(primaryScreenBounds.getMinY());
            System.out.println(primaryScreenBounds.getMaxY());
            System.out.println(primaryScreenBounds.getWidth());
            System.out.println(primaryScreenBounds.getHeight());
            */

            double HEIGHT = primaryScreenBounds.getHeight();
            double WIDTH = primaryScreenBounds.getWidth();
            //System.out.println("Height: " + HEIGHT + ", Width:" + WIDTH);
            return new double[]{HEIGHT, WIDTH};
        }

        // Set the Window
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