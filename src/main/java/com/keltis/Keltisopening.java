package com.keltis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Keltisopening extends Application {
    Button start;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("openingscene.fxml"));
        start = new Button("start button");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root, start);
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
