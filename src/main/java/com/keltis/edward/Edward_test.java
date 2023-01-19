package com.keltis.edward;

import java.util.ArrayList;

public class Edward_test {
    public static void main(String[] args){
        for (String name : PlayerConfig.get_player_config(3)) {
            System.out.println(name);
        }
    }
}
