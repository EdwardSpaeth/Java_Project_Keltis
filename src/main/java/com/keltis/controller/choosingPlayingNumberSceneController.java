package com.keltis.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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




    // Reading Player Names from File
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("playerNames.txt"));
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null){
                playerNames[i] = line;
                i++;
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

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

        String player1 = playerNames[0];
        String player2 = playerNames[1];
        String player3 = playerNames[2];
        String player4 = playerNames[3];


        // Load the Enter Name Scene
        if(players == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playersNames.fxml"));
            root = loader.load();
            enterNameSceneController disable = loader.getController();
            disable.setTwo();
            enterNameSceneController name = loader.getController();
            name.setNames(player1, player2, player3, player4);

        } else if (players == 3) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playersNames.fxml"));
            root = loader.load();

            enterNameSceneController disable = loader.getController();
            disable.setThree();

            enterNameSceneController name = loader.getController();
            name.setNames(player1, player2, player3, player4);


        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playersNames.fxml"));
            root = loader.load();

            enterNameSceneController disable = loader.getController();
            disable.setFour();

            enterNameSceneController name = loader.getController();
            name.setNames(player1, player2, player3, player4);


        }
        window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    // Back Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setMinWidth(1080);
        window.setMinHeight(720);
        window.setMaximized(true);
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }




}




