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
            System.out.println();
            return stage;
        }

        public double[] Resize(){
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double SIZE[] = {1080, 720};

            SIZE[0] = primaryScreenBounds.getHeight();
            SIZE[1] = primaryScreenBounds.getWidth();

            return SIZE;
        }

}
//test