package com.keltis.controller;

import com.keltis.controller.gameController;
import com.keltis.SizeOfMonitor;
import com.keltis.edward.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public class enterNameSceneController {

    @FXML
    TextField firstPlayer;
    @FXML
    TextField secondPlayer;
    @FXML
    TextField thirdPlayer;
    @FXML
    TextField fourthPlayer;
    @FXML
    Text PlayerText;
    @FXML
    private ImageView PlayerIMG_3;
    @FXML
    private ImageView PlayerIMG_4;
    @FXML
    private ImageView N_PlayerIMG_3;
    @FXML
    private ImageView N_PlayerIMG_4;


    private Parent root;
    private Stage window;

    public ArrayList<String> player_names = new ArrayList<>();

    public int amount_of_players;

    public void setNames(ArrayList<String> player_names_input){
        amount_of_players = player_names_input.size();
        player_names = player_names_input;
        firstPlayer.setText(player_names.get(0));
        secondPlayer.setText(player_names.get(1));

        if (player_names.size() >= 3){
            thirdPlayer.setText(player_names.get(2));
        }

        if (player_names.size() >= 4){
            fourthPlayer.setText(player_names.get(3));
        }

    }

    public void disableButton(int amount_of_players){
        Boolean hide_third_player = Boolean.FALSE;
        Boolean hide_fourth_player = Boolean.FALSE;

        Cursor third_player_cursor = Cursor.TEXT;
        Cursor fourth_player_cursor = Cursor.TEXT;
        if (amount_of_players < 3) {
            hide_third_player = Boolean.TRUE;
            third_player_cursor = Cursor.DEFAULT;

        }
        if (amount_of_players < 4){
            hide_fourth_player = Boolean.TRUE;
            fourth_player_cursor = Cursor.DEFAULT;

        }

        thirdPlayer.setDisable(hide_third_player);
        thirdPlayer.setCursor(third_player_cursor);
        fourthPlayer.setDisable(hide_fourth_player);
        fourthPlayer.setCursor(fourth_player_cursor);
        PlayerIMG_3.setVisible(!hide_third_player);
        PlayerIMG_4.setVisible(!hide_fourth_player);
        N_PlayerIMG_3.setVisible(hide_third_player);
        N_PlayerIMG_4.setVisible(hide_fourth_player);

        PlayerText.setText(amount_of_players + " Players");

    }

    // Start Button
    @FXML
    public void Start(MouseEvent mouseEvent) throws IOException {

        ArrayList<String> names = new ArrayList<>();
        player_names.set(0, firstPlayer.getText());
        player_names.set(1, secondPlayer.getText());
        if (player_names.size() >= 3) {
            player_names.set(1, thirdPlayer.getText());
        }
        if (player_names.size() >= 4) {
            player_names.set(1, fourthPlayer.getText());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = loader.load();
        // Calculate player amount
        //int amount_players = player_names.size();
        /*
        if (!fourthPlayer.getText().equals("") && !thirdPlayer.getText().equals("")) {
            amount_players = 4;
        }
        else if (!thirdPlayer.getText().equals("")) {
            amount_players = 3;
        }
        else {
            amount_players = 2;
        }
        */
        com.keltis.edward.GameEngine gameengine = new com.keltis.edward.GameEngine(amount_of_players);
        // Call to returnroot
        Group root2 = new Group(gameengine.get_gameboard().get_gameboard_chips_group());

        com.keltis.controller.gameController game = loader.getController();
        game.newNames(player_names);
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        StackPane layout = new StackPane();

        Scene scene = new Scene(layout);
        layout.getChildren().addAll(root, root2);
        layout.setAlignment(Pos.CENTER);
/*
        StackPane player1_layout = new StackPane();
        Group root3 = generate_chips_for_player1();

        player1_layout.getAlignment();
        player1_layout.getChildren().addAll(root3);
        //player1_layout.setAlignment(root3);


        Group layout_group = new Group(layout, player1_layout);
*/
        //Scene scene = new Scene(layout_group);
        // scene = new Scene(root);
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        window.setScene(scene);
        window.show();
        Main.start_game(gameengine);
    }

    private Group generate_chips_for_player1() {
        Group root3 = new Group();
        int WIDTH = 25;
        int HEIGHT = 25;
        int HORIZONTAL_SPACE = 50;
        int VERTICAL_SPACE = 50;
        ArrayList<Integer> x_cords = new ArrayList<>();
        ArrayList<Integer> y_cords = new ArrayList<>();

        for (int x = 0; x < 11; x++) {
            x_cords.add(2000 + x * HORIZONTAL_SPACE);
        }
        for (int y = 0; y < 5; y++) {
            y_cords.add(2000 + y * VERTICAL_SPACE);
        }

        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 5; y++) {
                Rectangle rectangle = new Rectangle(x_cords.get(x), y_cords.get(y), WIDTH, HEIGHT);
                switch (y) {
                    case 0 -> rectangle.setFill(Color.SIENNA);
                    case 1 -> rectangle.setFill(Color.GOLD);
                    case 2 -> rectangle.setFill(Color.HOTPINK);
                    case 3 -> rectangle.setFill(Color.MEDIUMSEAGREEN);
                    case 4 -> rectangle.setFill(Color.SKYBLUE);
                }
                root3.getChildren().addAll(rectangle);
            }
        }
        return root3;
    }

    // Menu Button - Back to Menu
    @FXML
    public void Menu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startSceneTest.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        //scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    // Back Button - Back to Player Number
    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choosingPlayerNumber.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        //scene = new Scene(root);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(root);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}
