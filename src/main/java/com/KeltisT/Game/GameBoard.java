package com.KeltisT.Game;

import com.KeltisT.Chips.ChipGenerator;
import com.KeltisT.Chips.PhysicalChip;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class GameBoard {
    private final ArrayList<PhysicalChip> pchips;
    private final Group gameboard_chips_group;
    private final AnchorPane blocker;
    public GameBoard(AnchorPane blocker_input){
        pchips = ChipGenerator.generate_chips(5, 11, 10, 10, 10);
        gameboard_chips_group = new Group();
        for (PhysicalChip pchip : pchips) {
            gameboard_chips_group.getChildren().addAll(pchip.getPhysical_Chip());
        }
        blocker = blocker_input;
    }
    public ArrayList<PhysicalChip> get_chips(){
        return pchips;
    }
    public PhysicalChip transfer_chip_ownership(PhysicalChip pc){
        pchips.remove(pc);
        return pc;
    }
    public void make_blocker_visible(Boolean b) {
        blocker.setVisible(b);
    }
    public Boolean is_blocker_visible() {
        return blocker.isVisible();
    }
    public Group get_gameboard_chips_group() {
        return gameboard_chips_group;
    }
}
