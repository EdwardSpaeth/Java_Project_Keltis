package com.keltis.edward;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<Chip> chips;
    GameBoard(){
        chips = ChipGenerator.generate_chips(5, 11,
                3, 3, 5);
    }
    public ArrayList<Chip> get_chips(){
        return chips;
    }
    public void uncover_chip(Chip c){
        c.uncover();
    }
    public Chip transfer_chip_ownership(Chip c){
        chips.remove(c);
        return c;
    }
}
