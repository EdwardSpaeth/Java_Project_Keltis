package com.KeltisT.Game;

import com.KeltisT.Chips.Chip;
import com.KeltisT.Chips.ChipGenerator;
import com.KeltisT.Chips.PhysicalChip;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;

public class GameBoard {

    private ArrayList<Chip> chips;

    private ArrayList<PhysicalChip> pchips;
    private Group gameboard_chips_group;
    public GameBoard(){
        pchips = ChipGenerator.generate_chips(5, 11,
                3, 3, 5);

        gameboard_chips_group = new Group();

        for (PhysicalChip pchip : pchips) {
            gameboard_chips_group.getChildren().addAll(pchip.get_rectangle(), pchip.get_text());
        }
    }
    public ArrayList<PhysicalChip> get_chips(){
        return pchips;
    }
    public void uncover_chip(Chip c){
        c.set_is_hidden_to_false();
    }
    public PhysicalChip transfer_chip_ownership(PhysicalChip pc){
        pchips.remove(pc);
        return pc;
    }

    public Group get_gameboard_chips_group() {
        return gameboard_chips_group;
    }
}
