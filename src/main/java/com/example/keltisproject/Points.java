package com.example.keltisproject;

import java.util.ArrayList;
import java.util.Arrays;

public class Points {
    public static int get_points_stack_length(int stack_length){
        ArrayList<Integer> points_per_stack_length = new ArrayList<Integer>(
                Arrays.asList(0, -4, -3, 2, 3, 6, 10)); // 6 or more chips result in 10 points
        if (stack_length > points_per_stack_length.size()){
            stack_length = points_per_stack_length.size();
        }
        return points_per_stack_length.get(stack_length);
    }

    public static int get_points_wish_amount(int wish_amount){
        ArrayList<Integer> points_per_wish_amount = new ArrayList<Integer>(
                Arrays.asList(-4, -3, 2, 3, 6, 10)); // 5 or more chips result in 10 points
        if (wish_amount > points_per_wish_amount.size()){
            wish_amount = points_per_wish_amount.size();
        }
        return points_per_wish_amount.get(wish_amount);
    }
}
