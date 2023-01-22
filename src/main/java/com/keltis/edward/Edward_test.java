package com.keltis.edward;

import java.util.ArrayList;
import java.util.Arrays;

public class Edward_test {
    public static void main(String[] args){

        ArrayList<Chip> chips = ChipGenerator.generate_chips(5,
                11, 5, 5, 5);

        ArrayList<Integer> color_amounts = new ArrayList<>(Arrays.asList(0,0,0,0,0));
        for (Chip c : chips){
            int col = c.get_color();
            color_amounts.set(col, color_amounts.get(col) + 1);

        }

        for (int amount : color_amounts) {
            System.out.println("Color-amount: " + amount);
        }
    }
}
