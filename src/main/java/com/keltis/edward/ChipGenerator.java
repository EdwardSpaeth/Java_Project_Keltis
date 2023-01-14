package com.keltis.edward;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a class which decides which Chips (player "cards") are chosen to have additional bonuses.
 * Those bonuses are:
 *  Bonus points
 *  Clover
 *  Wish(-stone)
 * They are distributed uniformly across the indices
 * No chip can have multiple bonuses.
 * Therefore, being chosen for a bonus makes this particular chip ineligible to be considered for another.
 * @author Edward Späth
 * @version 1.0
 */

public class ChipGenerator {
    public static ArrayList<Chip> generate_chips(int amt_colors, int chips_per_color, int amt_clovers,
                                                     int amt_wishes, int amt_bonus_points) {
        // Create an empty ArrayList of Type Chip
        // Use nested for loops to generate chips of corresponding color and value.
        // Amount of colors is provided as argument.
        // Aswell as chips_per_color, which is the same for all colors.
        ArrayList<Chip> chips = new ArrayList<>();
        for (int color = 0; color < amt_colors; color++){
            for (int value = 0; value < chips_per_color+1; value++){
                chips.add(new Chip(value, color));
            }
        }

        // Create an ArrayList of indices [0, 1, 2... length(chips)-1]
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < chips.size(); i++) {
            indices.add(i);
        }

        // Create an Array to simplify for-loops,
        // which contains information on how many clovers, wishes, and bonus points to generate.
        // Information is also given as parameter
        int[] items_to_be_set = {amt_clovers, amt_wishes, amt_bonus_points};

        // Since nextInt is not a static method, class Random is instantiated as rand
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            // Iterate through amount of items to set
            for (int j = 0; j < items_to_be_set[i]; j++) {
                int selected_index = indices.get(rand.nextInt(indices.size()));
                switch (i) {
                    case 0 -> chips.get(selected_index).set_clover();
                    case 1 -> chips.get(selected_index).set_wish();
                    case 2 -> {
                        // Bound = 3 means, that values are between 0 and 2. Adding one makes it 1:3
                        int bonus_amount = rand.nextInt(3) + 1;
                        chips.get(selected_index).set_bonus(bonus_amount);
                    }
                }
                indices.remove(Integer.valueOf(selected_index));
            }
        }
        return chips;
    }
}

