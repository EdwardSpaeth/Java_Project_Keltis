package com.keltis.edward;

import javafx.scene.Group;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<Chip> chips;

    private Group gameboard_chips_group;
    public GameBoard(){
        chips = ChipGenerator.generate_chips(5, 11,
                3, 3, 5);

        gameboard_chips_group= new Group();
        int width = 50;
        int height = 50;
        int horizontal_space = 100;
        int vertical_space = 100;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 11; col++) {
                int corresponding_chip_index = row * 11 + col;
                com.keltis.edward.PhysicalChip pchip = new com.keltis.edward.PhysicalChip(row, col, width, height, chips.get(corresponding_chip_index));

                gameboard_chips_group.getChildren().addAll(pchip.get_rectangle(), pchip.get_text());
            }
        }
    }
    public ArrayList<Chip> get_chips(){
        return chips;
    }
    public void uncover_chip(Chip c){
        c.uncover();
    }
    public Chip transfer_chip_ownership(Chip c){
        chips.remove(c);
        return c;
    }

    public Group get_gameboard_chips_group() {
        return gameboard_chips_group;
    }
}
