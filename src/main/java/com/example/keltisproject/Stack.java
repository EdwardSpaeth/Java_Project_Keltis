package com.example.keltisproject;

import java.util.ArrayList;

public class Stack {
    private int color;
    private ArrayList<Chip> chips;
    private String direction;
    private int bound_val;

    Stack(int col){
        chips = new ArrayList<Chip>();
        direction = "NA";
        bound_val = -1;
    }

    public Boolean try_to_insert(Chip c){
        // First move is always valid.
        if (chips.size() == 0){
            chips.add(c);
            bound_val = c.get_value();
            return Boolean.TRUE;
        }
        else {
            // Calculate in which direction the user wants to insert.
            String direction_of_desired_insertion;
            if (bound_val < c.get_value()){
                direction_of_desired_insertion = "ASC";
            }
            else{
                direction_of_desired_insertion = "DESC";
            }
            // For the second chip, the direction has not yet been decided.
            // This chip will now decide the direction.
            if (direction.equals("NA")){
                chips.add(c);
                bound_val = c.get_value();
                direction = direction_of_desired_insertion;
                return Boolean.TRUE;
            }
            else{
                // Direction was already decided and intent aligns with restriction. Valid move.
                if (direction_of_desired_insertion.equals(direction)){
                    chips.add(c);
                    bound_val = c.get_value();
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
        for (Chip c : chips){
            if (c.get_wish()){
                amt_wishes++;
            }
        }
        return amt_wishes;
    }

    public int count_bonus_points(){
        int sum_bonus_points = 0;
        for (Chip c : chips){
            sum_bonus_points += c.get_bonus();
        }
        return sum_bonus_points;
    }

    public int count_chips(){
        return chips.size();
    }
}
