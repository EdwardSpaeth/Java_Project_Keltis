package com.example.keltisproject;

/**
 * This is a class which contains the information of each chip (player "cards")
 * This class contains informations about chips aswell as basic setters and getters
 */

public class Chip{
    private int value;
    private int color;
    // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
    private int bonus;
    private Boolean clover;
    private Boolean wish;

    // Constructor called with desired value and desired color. Specialties are disabled by default
    Chip(int val, int col){
        value = val;
        color = col;
        clover = Boolean.FALSE;
        wish = Boolean.FALSE;
        bonus = 0;
    }

    // Getters
    public int get_value(){
        return value;
    }
    public int get_color(){
        return color;
    }
    public Boolean get_clover(){
        return clover;
    }
    public Boolean get_wish(){
        return wish;
    }
    public int get_bonus(){
        return bonus;
    }

    // Setters
    public void set_value(int val){
        value = val;
    }
    public void set_color(int col){
        color = col;
    }
    public void set_clover(){
        clover = Boolean.TRUE;
    }
    public void set_wish(){
        wish = Boolean.TRUE;
    }
    public void set_bonus(int input_bonus){
        bonus = input_bonus;
    }
}


