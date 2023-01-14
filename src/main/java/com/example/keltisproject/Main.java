package com.example.keltisproject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameEngine gameengine = new GameEngine();

        while (!gameengine.check_if_game_over()) {
            //Boolean clover_was_played = PLAYER_TURN(gameengine.curr_player);
            Boolean clover_was_played = Boolean.FALSE;
            gameengine.next_turn(clover_was_played);
        }
    }
}
