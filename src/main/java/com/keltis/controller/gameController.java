package com.keltis.controller;

import com.keltis.SizeOfMonitor;
import com.keltis.edward.GameBoard;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.security.Key;
import java.util.ArrayList;

public class gameController {


    @FXML
    public AnchorPane PauseMenu;

    public ImageView Grey;

    @FXML
    private Label player_1_field;
    @FXML
    private Label player_2_field;

    @FXML
    private Label player_3_field;

    @FXML
    private Label player_4_field;

    public ArrayList<String> player_names;


    public void newNames(ArrayList<String> player_names_input) {
        //testLabel.setText(names.get(0) + " " + names.get(1) + " " + names.get(2) + " " + names.get(3));
        player_names = player_names_input;
        com.keltis.edward.PlayerConfig.set_player_config(player_names);

        player_1_field.setText(player_names.get(0));
        player_2_field.setText(player_names.get(1));
        if (player_names.size() >= 3) {
            player_3_field.setText(player_names.get(2));
        }
        if (player_names.size() >= 4) {
            player_4_field.setText(player_names.get(3));
        }

    }

    public void setPauseMenu(KeyEvent keyEvent){
        PauseMenu.setVisible(true);
    }

    public void ZoomIn(MouseEvent mouseEvent) {
        Grey.translateZProperty().set(Grey.getTranslateZ() - 1000);
    }

    public void exit_was_clicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    // NOT USED ANYMORE
    public static Group returnroot(com.keltis.edward.GameEngine gameengine) {
        Group root2 = new Group();
/*
        ArrayList<com.keltis.edward.Chip> chips = com.keltis.edward.ChipGenerator.generate_chips(5,
                11, 5, 5, 5);
*/
        // remove
        /*
        int width = 50;
        int height = 50;
        int horizontal_space = 100;
        int vertical_space = 100;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 11; col++) {
                int corresponding_chip_index = row * 11 + col;
                com.keltis.edward.PhysicalChip pchip = new com.keltis.edward.PhysicalChip(row, col, width, height, gameboard.get_chips().get(corresponding_chip_index));

                root2.getChildren().addAll(pchip.get_rectangle(), pchip.get_text());
            }
        }
        */
        return root2;
    }

    public void setPauseMenu(){
        PauseMenu.setVisible(true);
    }


}


