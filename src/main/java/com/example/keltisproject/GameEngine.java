package com.example.keltisproject;

import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Player> players; // Made it static?
    private Player curr_player; // Made it static?
    private GameBoard gameboard; // Made it static?

    GameEngine(){
        gameboard = new GameBoard();
        ArrayList<String> player_names = PlayerConfig.get_player_config();
        for (int i = 0; i < player_names.size(); i++){
            players.add(new Player(player_names.get(i), i));
        }
        curr_player = players.get(0);
    }

    public void next_turn(Boolean clover_was_played){
        if (!clover_was_played) {
            curr_player = players.get(curr_player.get_order() + 1 % players.size());
        }
    }

    public Boolean check_if_game_over(){
        return gameboard.get_chips().size() == 0;
    }

    public void hey(){
        System.out.println("Hey");
    }
}
