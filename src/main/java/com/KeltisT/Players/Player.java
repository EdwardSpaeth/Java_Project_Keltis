package com.KeltisT.Players;

import com.KeltisT.Chips.ChipGenerator;
import com.KeltisT.Chips.PhysicalChip;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final Group player_chips_group;
    private final String name;
    private final int order;
    private int points;
    private final ArrayList<Stack> stacks;
    private final Label points_label;
    private final ImageView current_player_border;
    private int rank;

    public Player(String name_input, int order_input, Label point_label_input, ImageView current_player_border_input){
        name = name_input;
        order = order_input;
        stacks = new ArrayList<>();
        for (int color = 0; color < 5; color++){
            stacks.add(new Stack());
        }
        player_chips_group = new Group();
        //dummychips = dummychips_input;
        ArrayList<PhysicalChip> dummychips = ChipGenerator.generate_dummy_chips(5, 11);
        for (PhysicalChip dchip : dummychips) {
            dchip.getPhysical_Chip().setVisible(false);
            dchip.uncover();
            player_chips_group.getChildren().addAll(dchip.getPhysical_Chip());
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
        points_label = point_label_input;
        current_player_border = current_player_border_input;
    }
    public void current_player_border_set_visible(Boolean b) {
        current_player_border.setVisible(b);
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

    public void update_points() {
        points = get_total_points();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                points_label.setText(points + " Points");
            }
        });
        //points_label.setText(points + " Points");
    }

    public int get_points() {
        return points;
    }

    public int get_rank() {
        return rank;
    }

    public void set_rank(int r) {
        rank = r;
    }
}
