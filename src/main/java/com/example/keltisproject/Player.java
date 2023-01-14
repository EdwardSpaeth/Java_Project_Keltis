package com.example.keltisproject;

import java.util.ArrayList;

public class Player {
    private String name;
    private int order;
    private ArrayList<Stack> stacks;

    Player(String name_input, int order_input){
        name = name_input;
        order = order_input;
        stacks = new ArrayList<>();
        for (int color = 0; color < 5; color++){
            stacks.add(new Stack(color));
        }
    }

    public String get_name(){ return name; }

    public int get_order(){
        return order;
    }

    public int get_total_points(){
        int score = 0;
        int amt_wishes = 0;
        for (Stack s : stacks){
            score += Points.get_points_stack_length(s.count_chips());
            score += s.count_bonus_points();
            amt_wishes += s.count_wishes();
        }
        score += Points.get_points_wish_amount(amt_wishes);
        return score;
    }
}
