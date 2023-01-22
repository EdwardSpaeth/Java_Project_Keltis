package com.keltis.controller;

import com.keltis.SizeOfMonitor;
import com.keltis.edward.PhysicalChip;
import com.keltis.game.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;


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


    public void setNames(ArrayList<String> playerNames){

        for (String name : playerNames){
            System.out.println(name);
        }
        firstPlayer.setText(playerNames.get(0));
        secondPlayer.setText(playerNames.get(1));

        if (playerNames.size() >= 3){
            thirdPlayer.setText(playerNames.get(2));
        }
        /*
        else{
            thirdPlayer.setText("");
        }
        */
        if (playerNames.size() >= 4){
            fourthPlayer.setText(playerNames.get(3));
        }
        /*
        else{
            fourthPlayer.setText("");
        }
        */
    }
/*
    public void setTwo(){
        thirdPlayer.setDisable(true);
        fourthPlayer.setDisable(true);
        thirdPlayer.setCursor(Cursor.DEFAULT);
        fourthPlayer.setCursor(Cursor.DEFAULT);
        PlayerText.setText("2 Players");
        PlayerIMG_3.setVisible(false);
        PlayerIMG_4.setVisible(false);
        N_PlayerIMG_3.setVisible(true);
        N_PlayerIMG_4.setVisible(true);
    }

    public void setThree(){
        thirdPlayer.setDisable(false);
        fourthPlayer.setDisable(true);
        thirdPlayer.setCursor(Cursor.TEXT);
        fourthPlayer.setCursor(Cursor.DEFAULT);
        PlayerText.setText("3 Players");
        PlayerIMG_3.setVisible(true);
        PlayerIMG_4.setVisible(false);
        N_PlayerIMG_3.setVisible(false);
        N_PlayerIMG_4.setVisible(true);
    }

    public void setFour(){
        thirdPlayer.setDisable(false);
        fourthPlayer.setDisable(false);
        thirdPlayer.setCursor(Cursor.TEXT);
        fourthPlayer.setCursor(Cursor.TEXT);
        PlayerText.setText("4 Players");
        PlayerIMG_3.setVisible(true);
        PlayerIMG_4.setVisible(true);
        N_PlayerIMG_3.setVisible(false);
        N_PlayerIMG_4.setVisible(false);
    }
*/
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
        names.add(firstPlayer.getText());
        names.add(secondPlayer.getText());
        System.out.println("Thirdplayer.gettext() returns: <" + thirdPlayer.getText()+">");
        if (!thirdPlayer.getText().equals("")) {
            names.add(thirdPlayer.getText());
        }
        if (!fourthPlayer.getText().equals("")) {
            names.add(fourthPlayer.getText());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = loader.load();

        // RECTANGLE CREATION
        //Group root2 = new Group(rectangle);
        Group root2 = new Group();

        ArrayList<com.keltis.edward.Chip> chips = com.keltis.edward.ChipGenerator.generate_chips(5,
                11, 5, 5, 5);


        int width = 50;
        int height = 50;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 11; col++) {

                //com.keltis.edward.PhysicalChip pchip = new com.keltis.edward.PhysicalChip(row, col, width, height);
                int x = col*100;
                int y = row*100;
                Rectangle rectangle = new Rectangle(x, y, width, height);
                rectangle.setFill(Color.GREY);
                Text text = new Text(x+width/2, y+height/2, "?");
                text.setFont(Font.font(30));
                int desired_color = chips.get(row*11+col).get_color();

                String desired_value = Integer.toString(chips.get(row*11+col).get_value());
                EventHandler<MouseEvent> myhandler;
                rectangle.setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
                        switch (desired_color) {
                            case 0 -> rectangle.setFill(Color.BROWN);
                            case 1 -> rectangle.setFill(Color.YELLOW);
                            case 2 -> rectangle.setFill(Color.PINK);
                            case 3 -> rectangle.setFill(Color.GREEN);
                            case 4 -> rectangle.setFill(Color.BLUE);
                        }


                        //pchip.get_text().setText(Integer.toString(chips.get(index).get_value()));
                        //System.out.println(pchip.get_pane().getChildren());
                        //String number = Integer.toString(chips.get(index).get_value());
                        text.setText(desired_value);
                        text.setTextAlignment(TextAlignment.LEFT);
                        //pchip.get_text().setText(Integer.toString(chips.get(index).get_value()));
                    }
                });
                text.setOnMouseClicked(myhandler);
                root2.getChildren().addAll(rectangle, text);
            }
        }

        Game game = loader.getController();
        game.newNames(names);
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        StackPane layout = new StackPane();
        layout.getChildren().addAll(root, root2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        // scene = new Scene(root);
        SizeOfMonitor Size = new SizeOfMonitor();
        window = Size.getSizeOfMonitor(window);
        window.setScene(scene);
        window.show();
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
