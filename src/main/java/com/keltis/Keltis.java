package com.keltis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//test
public class Keltis extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SizeOfMonitor Size = new SizeOfMonitor();
        primaryStage = Size.getSizeOfMonitor(primaryStage);

        Parent root = FXMLLoader.load(getClass().getResource("controller/startScene.fxml"));
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        // Stage Settings
        Image icon = new Image("icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Keltis");
        primaryStage.setMaximized(true);

        // Loading App
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
