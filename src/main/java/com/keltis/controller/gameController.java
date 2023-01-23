package com.keltis.controller;

import com.keltis.edward.GameBoard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class gameController {


    //test


    public ImageView Grey;

    @FXML
    private Label player_1_field;
    @FXML
    private Label player_2_field;

    @FXML
    private Label player_3_field;

    @FXML
    private Label player_4_field;


    public void newNames(ArrayList<String> names) {
        //testLabel.setText(names.get(0) + " " + names.get(1) + " " + names.get(2) + " " + names.get(3));
        com.keltis.edward.PlayerConfig.set_player_config(names);

        player_1_field.setText(names.get(0));
        player_2_field.setText(names.get(1));
        if (names.size() >= 3) {
            player_3_field.setText(names.get(2));
        }
        if (names.size() >= 4) {
            player_4_field.setText(names.get(3));
        }

    }

    public void ZoomIn(MouseEvent mouseEvent) {
        Grey.translateZProperty().set(Grey.getTranslateZ() - 1000);
    }

    public void exit_was_clicked(MouseEvent mouseEvent) {
        Platform.exit();
    }
    // Back Button - Back to Player Number


    // NOT USED ANYMORE
    public static Group returnroot(com.keltis.edward.GameEngine gameengine) {
        Group root2 = new Group();
/*
        ArrayList<com.keltis.edward.Chip> chips = com.keltis.edward.ChipGenerator.generate_chips(5,
                11, 5, 5, 5);
*/
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
}


