package com.example.keltisproject;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Stack> stacks;

    Player(String name_input){
        name = name_input;
        for (int color = 0; color < 5; color++){
            stacks.add(new Stack(color));
        }
    }
}
