package com.KeltisT.Chips;

import com.KeltisT.Window.SizeOfMonitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This is a class which decides which generates Chips and randomly assigns additional bonuses.
 * Those bonuses are:
 *  Bonus points
 *  Clovers
 *  Wish stones
 * They are uniformly randomly distributed across the indices
 * No chip can have multiple bonuses.
 * Therefore, being chosen for a bonus makes this particular chip ineligible to be considered for another.
 */

public class ChipGenerator {

    static SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    static int M_Height = (int) sizeOfMonitor.getSizeOfMonitor()[0];
    static int M_Width = (int) sizeOfMonitor.getSizeOfMonitor()[1];

    /**
     * Generates chips for the main game board (The middle with the covered chips)
     * @param amt_colors how many colors are there in total?
     * @param chips_per_color how many chips are there per color?
     * @param amt_clovers how many clovers are to be distributed?
     * @param amt_wishes how many wish stones are to be distributed?
     * @param amt_bonus_points how many chips are supposed to have bonus points
     * @return ArrayList containing the generated chips
     */
    public static ArrayList<PhysicalChip> generate_chips(int amt_colors, int chips_per_color, int amt_clovers,
                                                         int amt_wishes, int amt_bonus_points) {
        // Create an empty ArrayList of Type Chip_Test
        // Use nested for loops to generate chips of corresponding color and value.
        // Amount of colors is provided as argument.
        // Aswell as chips_per_color, which is the same for all colors.

        int WIDTH = M_Width / 25;
        int HEIGHT = M_Height / 25;
        int HORIZONTAL_SPACE = WIDTH + M_Width/50;
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
        //ArrayList<PhysicalChip> unshuffled_chips = (ArrayList<PhysicalChip>) pchips.clone();
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
        //return new ArrayList<>(Arrays.asList(pchips, unshuffled_chips));
        return pchips;
    }

    /**
     * Generates so-called "dummy chips", which are placed onto the players' stacks
     * and made visible/changed when a player takes a chip.
     * @param amt_colors how many colors are there in total?
     * @param chips_per_color how many chips are there per color?
     * @return ArrayList of dummy chips
     */
    public static ArrayList<PhysicalChip> generate_dummy_chips(int amt_colors, int chips_per_color) {
        int DUMMY_HEIGHT = M_Width / 50;
        int DUMMY_WIDTH = M_Height / 15;
        int DUMMY_HORIZONTAL_SPACE = DUMMY_WIDTH + DUMMY_WIDTH/6;
        int DUMMY_VERTICAL_SPACE = DUMMY_HEIGHT + DUMMY_HEIGHT/3;


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

        for (int y = 0; y < amt_colors; y++) {
            for (int x = 0; x < chips_per_color; x++) {
                pchips.get(y*chips_per_color+x).set_cords(x_cords.get(x % chips_per_color), y_cords.get(y % amt_colors), DUMMY_WIDTH, DUMMY_HEIGHT);
                pchips.get(y*chips_per_color+x).set_ui_elements();
            }
        }

        return pchips;
    }
}

