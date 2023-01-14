package com.example.keltisproject;

import java.util.ArrayList;

public class Player {
    private String name;
    private int order;
    private ArrayList<Stack> stacks;

    Player(String name_input, int order_input){
        name = name_input;
        order = order_input;
        for (int color = 0; color < 5; color++){
            stacks.add(new Stack(color));
        }
    }

    public int get_order(){
        return order;
    }
}
