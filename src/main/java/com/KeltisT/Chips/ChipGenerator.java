package com.KeltisT.Chips;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.scene.Cursor;

import java.util.ArrayList;
import java.util.Collections;
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


    public static ArrayList<PhysicalChip> generate_chips(int amt_colors, int chips_per_color, int amt_clovers,
                                                         int amt_wishes, int amt_bonus_points) {
        // Create an empty ArrayList of Type Chip_Test
        // Use nested for loops to generate chips of corresponding color and value.
        // Amount of colors is provided as argument.
        // Aswell as chips_per_color, which is the same for all colors.

        /*
        int WIDTH = 50;
        int HEIGHT = 50;
        int HORIZONTAL_SPACE = 100;
        int VERTICAL_SPACE = 100;
        */
        SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
        double[] Size_double = sizeOfMonitor.getSizeOfMonitor();
        int[] Size = {(int) Size_double[0], (int) Size_double[1]};
        int WIDTH = Size[1] / 75;
        int HEIGHT = Size[0] / 30;
        int HORIZONTAL_SPACE = WIDTH * (5/2);
        int VERTICAL_SPACE = HEIGHT * (5/2);


        ArrayList<PhysicalChip> pchips = new ArrayList<>();
        for (int color = 0; color < amt_colors; color++){
            for (int value = 0; value < chips_per_color; value++){
                pchips.add(new PhysicalChip(value, color));
            }
        }

        // Create an ArrayList of indices [0, 1, 2... length(chips)-1]
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < pchips.size(); i++) {
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
                    case 0 -> pchips.get(selected_index).set_clover();
                    case 1 -> pchips.get(selected_index).set_wish();
                    case 2 -> {
                        // Bound = 3 means, that values are between 0 and 2. Adding one makes it 1:3
                        int bonus_amount = rand.nextInt(3) + 1;
                        pchips.get(selected_index).set_bonus(bonus_amount);
                    }
                }
                indices.remove(Integer.valueOf(selected_index));
            }
        }

        Collections.shuffle(pchips);

        ArrayList<Integer> x_cords = new ArrayList<>();
        ArrayList<Integer> y_cords = new ArrayList<>();
        for (int x = 0; x < chips_per_color; x++) {
            x_cords.add(x * HORIZONTAL_SPACE);
        }
        for (int y = 0; y < amt_colors; y++) {
            y_cords.add(y * VERTICAL_SPACE);
        }
        Collections.shuffle(x_cords);
        Collections.shuffle(y_cords);

        for (int i = 0; i < pchips.size(); i++) {
            pchips.get(i).set_cords(x_cords.get(i % chips_per_color), y_cords.get(i % amt_colors), WIDTH, HEIGHT);
            pchips.get(i).set_ui_elements();
        }
        return pchips;
    }

    public static ArrayList<PhysicalChip> generate_dummy_chips(int amt_colors, int chips_per_color) {
        int DUMMY_WIDTH = 25;
        int DUMMY_HEIGHT = 25;
        int DUMMY_HORIZONTAL_SPACE = 50;
        int DUMMY_VERTICAL_SPACE = 25 + 25/2;
        ArrayList<PhysicalChip> pchips = new ArrayList<>();

        for (int color = 0; color < amt_colors; color++){
            for (int value = 0; value < chips_per_color; value++){
                pchips.add(new PhysicalChip(value, color));
            }
        }

        ArrayList<Integer> x_cords = new ArrayList<>();
        ArrayList<Integer> y_cords = new ArrayList<>();
        for (int x = 0; x < chips_per_color; x++) {
            x_cords.add(x * DUMMY_HORIZONTAL_SPACE);
        }
        for (int y = 0; y < amt_colors; y++) {
            y_cords.add(y * DUMMY_VERTICAL_SPACE);
        }

        for (int i = 0; i < pchips.size(); i++) {
            pchips.get(i).set_cords(x_cords.get(i % chips_per_color), y_cords.get(i % amt_colors), DUMMY_WIDTH, DUMMY_HEIGHT);
            pchips.get(i).set_ui_elements();
        }
        for (PhysicalChip pchip : pchips) {
            pchip.get_rectangle().setCursor(Cursor.DEFAULT);
            pchip.get_text().setCursor(Cursor.DEFAULT);
        }
        return pchips;
    }
}

