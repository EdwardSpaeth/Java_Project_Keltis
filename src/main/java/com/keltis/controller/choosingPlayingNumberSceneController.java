package com.keltis.controller;

import com.keltis.SizeOfMonitor;
import com.keltis.edward.PlayerConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

//test

public class choosingPlayingNumberSceneController {
    private Parent root;
    private Stage window;

    public int players = 4;
    public ArrayList<String> playerNames;
    //public String[] playerNames = {"", "", "", ""};

    //ArrayList<String> playerNames = com.keltis.edward.PlayerConfig.get_player_config(players);



    /*
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

     */

    // Choose 2 Players
    public void switchTo2PlayersSelected() throws IOException {
        players = 2;
    }

    // Choose 3 Players
    @FXML
    void switchTo3PlayersSelected() throws IOException {
        players = 3;
        //playerNames = com.keltis.edward.PlayerConfig.get_player_config(players);

    }

    // Choose 4 Players
    @FXML
    void switchTo4PlayersSelected() throws IOException{
        players = 4;
        //playerNames = com.keltis.edward.PlayerConfig.get_player_config(players);

    }

    // Next Button
    @FXML
    void Next(MouseEvent mouseEvent) throws IOException{

        /*
        String player1 = playerNames[0];
        String player2 = playerNames[1];
        String player3 = playerNames[2];
        String player4 = playerNames[3];

         */
        /*
        String player1 = playerNames.get(0);
        String player2 = playerNames.get(1);
        String player3 = playerNames.get(2);
        String player4 = playerNames.get(3);

         */


        // Load the Enter Name Scene
        if(players == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playersNames.fxml"));
            root = loader.load();

            enterNameSceneController disable = loader.getController();
            disable.setTwo();

            enterNameSceneController name = loader.getController();
            name.setNames(com.keltis.edward.PlayerConfig.get_player_config(players));

        } else if (players == 3) {
            System.out.println("Hey");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playersNames.fxml"));
            root = loader.load();

            enterNameSceneController disable = loader.getController();
            disable.setThree();

            enterNameSceneController name = loader.getController();
            name.setNames(com.keltis.edward.PlayerConfig.get_player_config(players));



        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playersNames.fxml"));
            root = loader.load();

            enterNameSceneController disable = loader.getController();
            disable.setFour();

            enterNameSceneController name = loader.getController();
            name.setNames(com.keltis.edward.PlayerConfig.get_player_config(players));



        }
        window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        layout.setFillWidth(true);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    // Back Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }


}




