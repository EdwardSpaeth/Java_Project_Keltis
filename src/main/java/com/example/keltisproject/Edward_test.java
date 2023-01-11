package com.example.keltisproject;

import java.util.ArrayList;

public class Edward_test {
    public static void main(String[] args){
        // piep
        ArrayList<Chip> chips = ChipGenerator.generate_chips(5, 11, 3, 3, 5);

        String[] colors = {"brown", "yellow", "pink", "green", "blue"};
        for (Chip c : chips){
            System.out.print("Chip has color " + colors[c.get_color()] + " and value " + c.get_value());
            if (c.get_clover()){
                System.out.print(" CLOVER");
            }
            if (c.get_wish()){
                System.out.print(" WISH");
            }
            if (c.get_bonus() != 0){
                System.out.print(" BONUS = " + c.get_bonus());
            }
            System.out.print("\n");
        }
    }
}
