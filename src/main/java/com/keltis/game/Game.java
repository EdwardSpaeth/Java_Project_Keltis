package com.keltis.game;

import javafx.application.Platform;
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
import java.util.ArrayList;

//test
public class Game {


    public String[] newNames = {"", "", "", ""};

    public void newNames(ArrayList<String> names){
        //testLabel.setText(names.get(0) + " " + names.get(1) + " " + names.get(2) + " " + names.get(3));
        com.keltis.edward.PlayerConfig.set_player_config(names);


    }

    public void exit_was_clicked(MouseEvent mouseEvent) {
        Platform.exit();
    }
    // Back Button - Back to Player Number


}
