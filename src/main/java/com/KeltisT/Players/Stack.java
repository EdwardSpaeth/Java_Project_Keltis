package com.KeltisT.Players;

import com.KeltisT.Chips.Chip;
import com.KeltisT.Chips.PhysicalChip;
import java.util.ArrayList;

/**
 * Class which contains the chips of the players. Each Stack represents a color.
 */
public class  Stack {
    private final ArrayList<PhysicalChip> pchips;
    private int direction; // -1 = Descending, 0 = Neutral, 1 = Ascending
    private int bound_val;
    private ArrayList<PhysicalChip> dummychips;

    /**
     * Constructor
     */
    Stack(){
        pchips = new ArrayList<>();
        direction = 0;
        bound_val = -1;
    }

    /**
     * Setter for the dummy chips of the player
     * @param dchips ArrayList of PhysicalChip instances, which are this stack's dummy chips
     */
    public void set_dummychips(ArrayList<PhysicalChip> dchips) {
        dummychips = dchips;
    }

    /**
     * Function to insert a chip into a stack.
     * @param pc PhysicalChip instance which is to be inserted
     */
    public void insert(PhysicalChip pc){
        // Ideally if check is not necessary
        if (check_if_insert_possible(pc)){
            dummychips.get(pchips.size()).set_dummy(pc.get_value(), pc.get_color(), pc.get_clover(), pc.get_wish(), pc.get_bonus());
            pchips.add(pc);
            if (pchips.size() > 1 && direction == 0){
                if (bound_val < pc.get_value()){
                    direction = 1;
                }
                else{
                    direction = -1;
                }
            }
            bound_val = pc.get_value();
        }
    }

    /**
     * Function which given a chip as input, tells whether the chip can be inserted or not (according to the rules).
     * @param pc PhysicalChip instance which is to be tested
     * @return boolean of whether the insert would be possible or not
     */
    public Boolean check_if_insert_possible(PhysicalChip pc){
        // First move is always valid.
        if (pchips.size() == 0){
            return Boolean.TRUE;
        }
        else {
            // Calculate in which direction the user wants to insert.
            int direction_of_desired_insertion;
            if (bound_val < pc.get_value()){
                direction_of_desired_insertion = 1;
            }
            else{
                direction_of_desired_insertion = -1;
            }
            // For the second chip, the direction has not yet been decided.
            // This chip will now decide the direction.
            if (direction == 0){
                return Boolean.TRUE;
            }
            else{
                // Direction was already decided and intent aligns with restriction. Valid move.
                if (direction_of_desired_insertion == direction){
                    return Boolean.TRUE;
                }
                // Direction was already decided and intent does not align with restriction. Invalid move.
                else{
                    return Boolean.FALSE;
                }
            }
        }
    }
    /**
     * Counts how many wish stones a given stack has.
     * @return the amount of wish stones in that stack
     */
    public int count_wishes(){
        int amt_wishes = 0;
        for (Chip c : pchips){
            if (c.get_wish()){
                amt_wishes++;
            }
        }
        return amt_wishes;
    }

    /**
     * Sums up the bonus points of a given stack.
     * @return the sum of bonus points of that stack
     */
    public int count_bonus_points(){
        int sum_bonus_points = 0;
        for (Chip c : pchips){
            sum_bonus_points += c.get_bonus();
        }
        return sum_bonus_points;
    }

    /**
     * Returns how many chips are in that stack.
     * @return integer value representing the size of that stack
     */
    public int count_chips(){
        return pchips.size();
    }

    /**
     * Getter for the direction of the stack (Direction in which all subsequent chips have to be --> ascending, descending, neutral).
     * @return integer-coded value. Ascending = 1, neutral = 0, descending = -1
     */
    public int get_direction() {
        return direction;
    }

    /**
     * Getter for the boundary value, which is the value of the chip which has been most recently taken.
     * @return boundary value of that stack
     */
    public int get_bound_val() {
        return bound_val;
    }
}
