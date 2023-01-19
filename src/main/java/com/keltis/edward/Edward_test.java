package com.keltis.edward;

import java.util.ArrayList;

public class Edward_test {
    public static void main(String[] args){

        ArrayList<String> players = new ArrayList<>();
        players.add("NewP3P1");
        players.add("NewP3P2");
        players.add("NewP3P3");
        PlayerConfig.set_player_config(players);
        //ArrayList<String> test = PlayerConfig.get_player_config(4);
    }
}
