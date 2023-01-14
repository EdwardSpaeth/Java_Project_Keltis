package com.example.keltisproject;

import java.util.ArrayList;

public class Edward_test {
    public static void main(String[] args){
        ArrayList<Integer> points = new ArrayList<Integer>();
        points.add(21);
        points.add(25);
        points.add(13);
        points.add(25);

        int max_value = points.get(0);
        for (int i = 1; i < 4; i++){
            if (points.get(i) > max_value){
                max_value = points.get(i);
            }
        }
        ArrayList<Integer> winners = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            if (points.get(i) == max_value){
                winners.add(i);
                System.out.println(i);
            }
        }
    }
}
