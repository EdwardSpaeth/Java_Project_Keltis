package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Players.Player;
import com.KeltisT.Players.PlayerConfig;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class GameEngine {
    private ArrayList<Player> players;
    private Player curr_player;
    private GameBoard gameboard;
    private GameTimer timer;
    private Button takeButton;
    private Button leaveButton;

    public GameEngine(int amount_of_players, Text timerText, Button takeButton_input, Button leaveButton_input){
        players = new ArrayList<>();
        gameboard = new GameBoard();
        ArrayList<String> player_names = PlayerConfig.get_player_config(amount_of_players);
        for (int i = 0; i < amount_of_players; i++){
            players.add(new Player(player_names.get(i), i));
        }
        curr_player = players.get(0);
        timer = new GameTimer(timerText, this);
        timer.timer();
        takeButton = takeButton_input;
        leaveButton = leaveButton_input;
        //determine_winner();
    }

    public void next_turn(Boolean clover_was_played){
        if (!clover_was_played) {
            curr_player = players.get((curr_player.get_order() + 1) % players.size());
        }
        timer.refresh();
    }

    public Boolean check_if_game_over(){
        // Does not work yet...
        Boolean covered_chip_found = Boolean.FALSE;
        for (PhysicalChip pchip : gameboard.get_chips()) {
            if (pchip.get_is_hidden()) {
                covered_chip_found = Boolean.TRUE;
            }
        }
        return !covered_chip_found;
    }

    public ArrayList<Integer> determine_winner(){
        players.get(0).set_points(10);
        players.get(1).set_points(20);
        players.get(2).set_points(30);
        players.get(3).set_points(40);


        ArrayList<Integer> ranks = new ArrayList<>(Arrays.asList(0,0,0,0));
        ArrayList<Player> players_to_rank = players;
        int position_to_award = 1;
        while (players_to_rank.size() > 0) {
            int max_value = players_to_rank.get(0).get_points_experimental();
            for (Player p : players_to_rank) {
                if (p.get_points_experimental() > max_value) {
                    max_value = p.get_points_experimental();
                }
            }
            int amount_players_in_this_rank = 0;
            for (Player p : players_to_rank) {
                if (p.get_points_experimental() == max_value) {
                    ranks.set(p.get_order(), position_to_award);
                    players_to_rank.remove(p);
                    amount_players_in_this_rank++;
                }
            }
            position_to_award += amount_players_in_this_rank;
        }
        for (int r : ranks) {
            System.out.println(r);
        }
        return ranks;
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

    public Button get_takeButton() {
        return takeButton;
    }

    public Button get_leaveButton() {
        return leaveButton;
    }
}
