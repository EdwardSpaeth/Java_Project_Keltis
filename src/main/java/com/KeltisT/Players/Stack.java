package com.KeltisT.Players;

import com.KeltisT.Chips.Chip;
import com.KeltisT.Chips.PhysicalChip;

import java.util.ArrayList;
import java.util.Arrays;

public class  Stack {
    private int color;
    private ArrayList<Chip> pchips;
    private String direction;
    private int bound_val;

    private ArrayList<PhysicalChip> dummychips;

    Stack(int col){
        color = col;
        pchips = new ArrayList<>();
        direction = "NA";
        bound_val = -1;
    }

    public void set_dummychips(ArrayList<PhysicalChip> dchips) {
        dummychips = dchips;
    }
    private String get_color_string(){
        // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
        ArrayList<String> colors = new ArrayList<>(Arrays.asList("brown", "yellow", "pink", "green", "blue"));
        return colors.get(color);
    }
    public void insert(PhysicalChip pc){
        // Ideally if check is not necessary
        if (check_if_insert_possible(pc)){
            pchips.add(pc);
            bound_val = pc.get_value();
            if (direction.equals("NA")){
                if (bound_val < pc.get_value()){
                    direction = "ASC";
                }
                else{
                    direction = "DESC";
                }
            }
        }
    }
    public Boolean check_if_insert_possible(PhysicalChip pc){
        // First move is always valid.
        if (pchips.size() == 0){
            return Boolean.TRUE;
        }
        else {
            // Calculate in which direction the user wants to insert.
            String direction_of_desired_insertion;
            if (bound_val < pc.get_value()){
                direction_of_desired_insertion = "ASC";
            }
            else{
                direction_of_desired_insertion = "DESC";
            }
            // For the second chip, the direction has not yet been decided.
            // This chip will now decide the direction.
            if (direction.equals("NA")){
                pchips.add(pc);
                bound_val = pc.get_value();
                direction = direction_of_desired_insertion;
                return Boolean.TRUE;
            }
            else{
                // Direction was already decided and intent aligns with restriction. Valid move.
                if (direction_of_desired_insertion.equals(direction)){
                    pchips.add(pc);
                    bound_val = pc.get_value();
                    return Boolean.TRUE;
                }
                // Direction was already decided and intent does not align with restriction. Invalid move.
                else{
                    return Boolean.FALSE;
                }
            }
        }
    }

    public int count_wishes(){
        int amt_wishes = 0;
        for (Chip c : pchips){
            if (c.get_wish()){
                amt_wishes++;
            }
        }
        return amt_wishes;
    }

    public int count_bonus_points(){
        int sum_bonus_points = 0;
        for (Chip c : pchips){
            sum_bonus_points += c.get_bonus();
        }
        return sum_bonus_points;
    }

    public int count_chips(){
        return pchips.size();
    }
}
