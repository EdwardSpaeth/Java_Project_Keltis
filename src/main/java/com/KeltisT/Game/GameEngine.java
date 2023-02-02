package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Players.Player;
import com.KeltisT.Players.PlayerConfig;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Player> players;
    private Player curr_player;
    private GameBoard gameboard;
    private GameTimer timer;
    private Button takeButton;
    private Button leaveButton;

    public GameEngine(int amount_of_players, Text timerText, Button takeButton_input, Button leaveButton_input, ArrayList<Label> player_point_labels, AnchorPane blocker){
        players = new ArrayList<>();
        gameboard = new GameBoard(blocker);
        ArrayList<String> player_names = PlayerConfig.get_player_config(amount_of_players);
        for (int i = 0; i < amount_of_players; i++){
            players.add(new Player(player_names.get(i), i, player_point_labels.get(i)));
        }
        curr_player = players.get(0);
        timer = new GameTimer(timerText, this);
        timer.timer();
        takeButton = takeButton_input;
        leaveButton = leaveButton_input;
    }

    public void next_turn(Boolean clover_was_played){
        if (!clover_was_played) {
            curr_player = players.get((curr_player.get_order() + 1) % players.size());
        }
        timer.refresh();

        for (PhysicalChip pc : get_gameboard().get_chips()) {
            // pc.setDisable(false);
        }
        for (PhysicalChip pc : get_gameboard().get_chips()) {
            if (!pc.get_is_hidden() && !get_curr_player().get_stacks().get(pc.get_color()).check_if_insert_possible(pc)) {
                // pc.setDisable(true);
            }
        }
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

        ArrayList<Integer> ranks = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            ranks.add(0);
        }
        ArrayList<Player> players_to_rank = (ArrayList<Player>) players.clone();
        int position_to_award = 1;
        while (players_to_rank.size() > 0) {
            int max_value = players_to_rank.get(0).get_points();
            for (Player p : players_to_rank) {
                if (p.get_points() > max_value) {
                    max_value = p.get_points();
                }
            }
            int amount_players_in_this_rank = 0;
            ArrayList<Player> players_not_in_this_rank = (ArrayList<Player>) players_to_rank.clone();
            for (int i = 0; i < players_to_rank.size(); i++) {
                if (players_to_rank.get(i).get_points() == max_value) {
                    ranks.set(players_to_rank.get(i).get_order(), position_to_award);
                    players_not_in_this_rank.remove(players_to_rank.get(i));
                    amount_players_in_this_rank++;
                }
            }
            players_to_rank = players_not_in_this_rank;
            position_to_award += amount_players_in_this_rank;
        }
        int i = 0;
        for (Player p : players) {
            System.out.println("Order: " + p.get_order() + ", Points: " + p.get_points() + ", Rank: " + ranks.get(i));
            i++;
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
