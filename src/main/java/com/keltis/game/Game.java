package com.keltis.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//test
public class Game {

    @FXML
    Label testLabel;

    public String[] newNames = {"", "", "", ""};

    public void newNames(String name1, String name2, String name3, String name4){
        newNames[0] = name1;
        newNames[1] = name2;
        newNames[2] = name3;
        newNames[3] = name4;
        testLabel.setText(name1 + " " + name2 + " " + name3 + " " + name4);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("playerNames.txt"));
            writer.write(name1);
            writer.write("\n" + name2);
            writer.write("\n" + name3);
            writer.write("\n" + name4);

            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Parent root;
    private Stage window;
    private Scene scene;



    // Back Button - Back to Player Number
    @FXML
    public void Back_2(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("playersNames.fxml"));
        window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);

        window.setScene(scene);
        window.show();
    }
    @FXML
    public void Back_3(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("com.keltis.controller.3playersNames.fxml"));
        window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
    }
    @FXML
    public void Back_4(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("4playersNames.fxml"));
        window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);

        window.setScene(scene);
        window.show();
    }

}
