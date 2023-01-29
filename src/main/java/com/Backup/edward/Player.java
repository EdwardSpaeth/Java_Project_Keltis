package com.Backup.edward;

import javafx.scene.Group;

import java.util.ArrayList;

public class Player {
    private Group player_chips_group;
    private String name;
    private int order;
    private ArrayList<Stack> stacks;
    private ArrayList<PhysicalChip> dummychips;

    Player(String name_input, int order_input){
        name = name_input;
        order = order_input;
        stacks = new ArrayList<>();
        for (int color = 0; color < 5; color++){
            stacks.add(new Stack(color));
        }
        player_chips_group = new Group();
        //dummychips = com.KeltisT.Chips.ChipGenerator.generate_dummy_chips(5, 11);
        for (PhysicalChip dchip : dummychips) {
            player_chips_group.getChildren().addAll(dchip.get_rectangle(), dchip.get_text());
        }

    }

    public String get_name(){ return name; }

    public int get_order(){
        return order;
    }

    public ArrayList<Stack> get_stacks() {
        return stacks;
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

    public Group get_player_chips_group() {

        return player_chips_group;
    }
}
