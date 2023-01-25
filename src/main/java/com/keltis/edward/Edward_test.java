package com.keltis.edward;

import java.util.ArrayList;
import java.util.Arrays;

public class Edward_test {
    public static void main(String[] args){

        ArrayList<PhysicalChip> pchips = ChipGenerator.generate_chips(5, 11,
                3, 3, 5);

        for (PhysicalChip pchip : pchips) {
            System.out.println("Pchip: color=" + pchip.get_color() + " value=" + pchip.get_value() + " x=" + pchip.get_x() + " y=" + pchip.get_y());
        }















    }
}
