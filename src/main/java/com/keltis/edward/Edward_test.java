package com.keltis.edward;

import java.util.ArrayList;
import java.util.Arrays;

public class Edward_test {
    public static void main(String[] args){
        ArrayList<Integer> players = new ArrayList<>(Arrays.asList(0,1,2,3));
        int curr_player = players.get(0);

        for (int i = 0; i < 30; i++) {
            curr_player = players.get((curr_player + 1) % players.size());
            System.out.println(curr_player);
        }
    }
}
