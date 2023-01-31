package com.KeltisT.Players;

import com.KeltisT.Chips.ChipGenerator;
import com.KeltisT.Players.Points;
import com.KeltisT.Players.Stack;
import com.KeltisT.Chips.PhysicalChip;
import javafx.scene.Group;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private Group player_chips_group;
    private String name;
    private int order;
    private ArrayList<Stack> stacks;
    private ArrayList<PhysicalChip> dummychips;

    public Player(String name_input, int order_input){
        name = name_input;
        order = order_input;
        stacks = new ArrayList<>();
        for (int color = 0; color < 5; color++){
            stacks.add(new Stack(color));
        }
        player_chips_group = new Group();
        dummychips = ChipGenerator.generate_dummy_chips(5, 11);
        for (PhysicalChip dchip : dummychips) {
            dchip.get_rectangle().setVisible(false);
            dchip.get_text().setVisible(false);
            dchip.uncover();
            player_chips_group.getChildren().addAll(dchip.get_rectangle(), dchip.get_text());
            System.out.println("color = " + dchip.get_color() + ", value = " + dchip.get_value());
        }
        for (int i = 0; i < 5; i++) {
            ArrayList<PhysicalChip> corresponding_color_dummies = new ArrayList<>();
            for (int value = i*11; value < i*11+11; value++) {
                corresponding_color_dummies.add(dummychips.get(value));
            }
            if (order % 2 == 1) {
                Collections.reverse(corresponding_color_dummies);
            }
            stacks.get(i).set_dummychips(corresponding_color_dummies);
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
