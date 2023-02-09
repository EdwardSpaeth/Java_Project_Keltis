package com.KeltisT;

import com.KeltisT.Controllers.soundController;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class which extends Application.
 */
public class App extends Application {
    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        soundController Sounds = new soundController();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/start.fxml")));
        Sounds.startMusic();
        Scene scene = new Scene(root);

        stage.setMaximized(true);
        stage = sizeOfMonitor.setStageSize(stage);
        stage.setScene(scene);
        stage.show();
    }
}
