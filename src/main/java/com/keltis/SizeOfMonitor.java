package com.keltis;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SizeOfMonitor extends Application {

        @Override
        public void start(Stage primaryStage) {
        //Get primary screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);
    }

        public Stage getSizeOfMonitor(Stage stage){
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.setWidth(primaryScreenBounds.getWidth());
            stage.setHeight(primaryScreenBounds.getHeight());

            return stage;
        }

}
