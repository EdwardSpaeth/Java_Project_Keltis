package com.keltis.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

import static javafx.application.Application.launch;

public class choosingPlayingNumberSceneController {
    private Parent root;
    private Stage window;
    private Scene scene;
    @FXML
    private ImageView imageView;

    public int players = 4;
    public String[] playerNames = {"", "", "", ""};

    @FXML
    private TextField firstPlayer;


    // Choose 2 Players
    public void switchTo2PlayersSelected(MouseEvent mouseEvent) throws IOException {
        players = 2;
    }

    // Choose 3 Players
    @FXML
    void switchTo3PlayersSelected(MouseEvent mouseEvent) throws IOException {
        players = 3;
    }

    // Choose 4 Players
    @FXML
    void switchTo4PlayersSelected(MouseEvent mouseEvent) throws IOException{
        players = 4;
    }

    // Next Button
    @FXML
    void Next(MouseEvent mouseEvent) throws IOException{

        // Read Player Names from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("playerNames.txt"));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                playerNames[i] = line;
                i++;
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(playerNames[0]);
        System.out.println(playerNames[1]);
        System.out.println(playerNames[2]);
        System.out.println(playerNames[3]);


        // Load the Enter Name Scene
        if(players == 2) {
            root = FXMLLoader.load(getClass().getResource("2playersNames.fxml"));
        } else if (players == 3) {
            root = FXMLLoader.load(getClass().getResource("3playersNames.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("4playersNames.fxml"));
        }
        window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setResizable(false);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    // Back Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setResizable(false);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }




}




