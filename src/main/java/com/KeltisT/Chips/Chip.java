package com.KeltisT.Chips;

/**
 * This is a class which contains the information of each chip
 * This class contains information with setters and getters regarding the chip's basic information such as value, color, etc.
 */
public class Chip {
    private int value;
    private int color;
    // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
    private int bonus;
    private Boolean clover;
    private Boolean wish;
    private Boolean is_hidden;

    // Constructor called with desired value and desired color. Specialties are disabled by default

    /**
     * Constructor
     * @param val value of the chip ranging from 0 to 10
     * @param col integer-coded color of the chip ranging from 0 to 4
     */
    Chip(int val, int col){
        value = val;
        color = col;
        clover = Boolean.FALSE;
        wish = Boolean.FALSE;
        bonus = 0;
        is_hidden = Boolean.TRUE;
    }

    /**
     * Getter for value
     * @return integer representing the value of the chip
     */
    public int get_value(){
        return value;
    }

    /**
     * Getter for color
     * @return integer representing the color of the chip
     */
    public int get_color(){
        return color;
    }

    /**
     * Getter for whether a chip has a clover or not
     * @return boolean of whether a chip has a clover or not
     */
    public Boolean get_clover(){
        return clover;
    }

    /**
     * getter for whether a chip has a wish stone or not
     * @return boolean of whether a chip has a wish stone or not
     */
    public Boolean get_wish(){
        return wish;
    }

    /**
     * getter for how many bonus points (if any) a chip has
     * @return integer of how many bonus points (if any) a chip has
     */
    public int get_bonus(){
        return bonus;
    }

    /**
     * getter for whether the chip is uncovered or not
     * @return boolean of whether the chip is uncovered or not
     */
    public Boolean get_is_hidden() { return is_hidden;}

    /**
     * setter for value
     * @param val input which value is to be set to
     */
    public void set_value(int val){
        value = val;
    }

    /**
     * setter for color
     * @param col input which color is to be set to
     */
    public void set_color(int col){
        color = col;
    }

    /**
     * for assigning a clover to a chip
     */
    public void set_clover(){
        clover = Boolean.TRUE;
    }

    /**
     * for assigning a wish stone to a chip
     */
    public void set_wish(){
        wish = Boolean.TRUE;
    }

    /**
     * for assigning bonus points to a chip
     * @param input_bonus input which sets bonus points equal to that input
     */
    public void set_bonus(int input_bonus){
        bonus = input_bonus;
    }

    /**
     * for uncovering a chip, settings its status to is_hidden = false
     */
    public void set_is_hidden_to_false() { is_hidden = Boolean.FALSE; }
}


