package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//still figuring out how to fix the rules text
public class rulesController {
    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];

    public rulesController() throws IOException {
        Path filename  = Path.of("src/main/resources/Rules.txt");
        String rulestext = Files.readString(filename);
        Text text = new Text(rulestext);
        text.setFill(Color.RED);
        text.setStroke(Color.YELLOW);
        text.setStrokeWidth(0.5);
        text.setFont(Font.font("Papyrus", FontWeight.NORMAL, FontPosture.REGULAR, 12));

    }
    // Back Button
    public void switchToStart(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        System.out.println(WIDTH + " and " + HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
