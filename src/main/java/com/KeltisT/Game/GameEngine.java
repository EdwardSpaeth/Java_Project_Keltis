package com.KeltisT.Game;

import com.KeltisT.Game.GameBoard;
import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Players.Player;
import com.KeltisT.Players.PlayerConfig;

import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Player> players;
    private Player curr_player;
    private GameBoard gameboard;

    public GameEngine(int amount_of_players){
        players = new ArrayList<>();
        gameboard = new GameBoard();
        ArrayList<String> player_names = PlayerConfig.get_player_config(amount_of_players);
        for (int i = 0; i < amount_of_players; i++){
            players.add(new Player(player_names.get(i), i));
        }
        curr_player = players.get(0);
    }

    public void next_turn(Boolean clover_was_played){
        if (!clover_was_played) {
            curr_player = players.get((curr_player.get_order() + 1) % players.size());
        }
    }

    public Boolean check_if_game_over(){
        return gameboard.get_chips().size() == 0;
    }

    public ArrayList<Player> determine_winner(){
        ArrayList<Integer> points = new ArrayList<>();
        for (Player player : players) {
            points.add(player.get_total_points());
        }
        int max_value = points.get(0);
        for (int i = 1; i < players.size(); i++){
            if (points.get(i) > max_value){
                max_value = points.get(i);
            }
        }
        ArrayList<Player> winners = new ArrayList<>();
        for (int i = 0; i < players.size(); i++){
            if (points.get(i) == max_value){
                winners.add(players.get(i));
            }
        }
        return winners;
    }

    public GameBoard get_gameboard() {
        return gameboard;
    }


    public ArrayList<Player> get_players() {
        return players;
    }

    public Player get_curr_player() {
        return curr_player;
    }
    public Boolean chip_has_been_interacted_with(PhysicalChip pchip) {

        return pchip.get_clover();
    }
}