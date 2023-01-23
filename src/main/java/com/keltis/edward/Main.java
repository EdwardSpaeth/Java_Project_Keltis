package com.keltis.edward;

public class Main {
    // DOES NOT WORK YET
    public static void start_game(GameEngine gameengine) {

        System.out.println("Main has started!");

        for (int x = 0; x < 10; x++) {
        // Change it to line below WHEN it works
        //while (!gameengine.check_if_game_over()) {
            //Boolean clover_was_played = PLAYER_TURN(gameengine.curr_player);
            Boolean clover_was_played = Boolean.FALSE;
            gameengine.next_turn(clover_was_played);
        }
        gameengine.determine_winner();
    }
}
