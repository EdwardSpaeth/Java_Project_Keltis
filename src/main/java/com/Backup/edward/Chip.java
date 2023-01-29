package com.Backup.edward;

/**
 * This is a class which contains the information of each chip (player "cards")
 * This class contains information about chips aswell as basic setters and getters
 */

public class Chip{
    private int value;
    private int color;
    // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
    // "brown" = "sienna", yellow = "gold", "pink" = "hotpink", "green" = "mediumseagreen", "blue" = "skyblue"
    private int bonus;
    private Boolean clover;
    private Boolean wish;
    private Boolean is_hidden;

    // Constructor called with desired value and desired color. Specialties are disabled by default
    Chip(int val, int col){
        value = val;
        color = col;
        clover = Boolean.FALSE;
        wish = Boolean.FALSE;
        bonus = 0;
        is_hidden = Boolean.TRUE;
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
    public Boolean get_is_hidden() { return is_hidden;}

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
    public void set_is_hidden_to_false() { is_hidden = Boolean.FALSE; }
}


