package com.KeltisT.Game;

import com.KeltisT.Chips.ChipGenerator;
import com.KeltisT.Chips.PhysicalChip;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * Class which contains the chips in the middle of the board and manages the blocker.
 */
public class GameBoard {
    private final ArrayList<PhysicalChip> pChips;
    private final Group gameboard_chips_group;
    private final AnchorPane blocker;

    /**
     * Constructor
     * @param blocker_input reference to the AnchorPane, which is used as a blocker
     */
    public GameBoard(AnchorPane blocker_input){
        pChips = ChipGenerator.generate_chips(5, 11, 10, 10, 10);
        gameboard_chips_group = new Group();
        for (PhysicalChip pChip : pChips) {
            gameboard_chips_group.getChildren().addAll(pChip.getPhysical_Chip());
        }
        blocker = blocker_input;
    }

    /**
     * Getter for the chips in the middle.
     * @return Chips in the middle as an ArrayList
     */
    public ArrayList<PhysicalChip> get_chips(){
        return pChips;
    }

    /**
     * Removes the chip from the middle of the board (Chip was taken).
     * @param pc the chip to be removed
     * @return the removed chip, so that it may be saved in the player's inventory
     */
    public PhysicalChip transfer_chip_ownership(PhysicalChip pc){
        // pChips.remove(pc) removes the chip logically from the gameboard
        pChips.remove(pc);
        // pc.remove() removes the chip visually from the gameboard
        pc.remove();
        return pc;
    }

    /**
     * Activates/deactivates the blocker.
     * @param b specifies whether to activate or deactivate
     */
    public void make_blocker_visible(Boolean b) {
        blocker.setVisible(b);
    }

    /**
     * Returns whether blocker is visible or not.
     * @return boolean of whether blocker is visible
     */
    public Boolean is_blocker_visible() {
        return blocker.isVisible();
    }

    /**
     * Getter for the group of the chips in the middle. Group is JavaFX Scene related
     * @return the group of chips in the middle
     */
    public Group get_gameboard_chips_group() {
        return gameboard_chips_group;
    }
}
