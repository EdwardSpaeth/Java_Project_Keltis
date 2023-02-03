package com.KeltisT.Game;

import com.KeltisT.Chips.Chip;
import com.KeltisT.Chips.ChipGenerator;
import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class GameBoard {
    static SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    static int M_Height = (int) sizeOfMonitor.getSizeOfMonitor()[0];
    static int M_Width = (int) sizeOfMonitor.getSizeOfMonitor()[1];
    private ArrayList<PhysicalChip> pchips;
    private Group gameboard_chips_group;
    private AnchorPane blocker;
    public GameBoard(AnchorPane blocker_input){
        pchips = ChipGenerator.generate_chips(5, 11,
                3, 3, 5);

        gameboard_chips_group = new Group();

        for (PhysicalChip pchip : pchips) {
            //gameboard_chips_group.getChildren().addAll(pchip.get_rectangle(), pchip.get_text());
            gameboard_chips_group.getChildren().addAll(pchip.getPhysical_Chip());
        }
        blocker = blocker_input;
        blocker.setVisible(false);
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
    public void make_blocker_visible(Boolean b) {
        blocker.setVisible(b);
    }
    public Group get_gameboard_chips_group() {
        return gameboard_chips_group;
    }
}
