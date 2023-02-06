package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Controllers.soundController;
import com.KeltisT.Players.Player;
import com.KeltisT.Players.PlayerConfig;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameEngine {
    private final ArrayList<Player> players;
    private Player curr_player;
    private final GameBoard gameboard;
    private final GameTimer timer;
    private final Button takeButton;
    private final Button leaveButton;
    private final soundController sound;
    //private PhysicalChip current_pchip;
    private final VBox victoryVBox;
    private final Label youCanTakeBox;
    private ArrayList<Player> players_in_order;

    public GameEngine(int amount_of_players, Text timerText, Button takeButton_input, Button leaveButton_input, ArrayList<Label> player_point_labels, AnchorPane blocker, ArrayList<ImageView> current_player_borders, VBox gameOverVBox, Label youCanTakeBox_input){
        players = new ArrayList<>();
        gameboard = new GameBoard(blocker);
        ArrayList<String> player_names = PlayerConfig.get_player_config(amount_of_players);
        for (int i = 0; i < amount_of_players; i++){
            players.add(new Player(player_names.get(i), i, player_point_labels.get(i), current_player_borders.get(i)));
        }
        curr_player = players.get(0);
        curr_player.current_player_border_set_visible(true);
        timer = new GameTimer(timerText, this);
        timer.timer();
        takeButton = takeButton_input;
        leaveButton = leaveButton_input;
        sound = new soundController();
        victoryVBox = gameOverVBox;
        youCanTakeBox = youCanTakeBox_input;
    }
    public void next_turn(Boolean clover_was_played){
        curr_player.update_points();
        if (!clover_was_played) {
            curr_player = players.get((curr_player.get_order() + 1) % players.size());
        }
        timer.refresh();

        for (PhysicalChip pc : get_gameboard().get_chips()) {
            pc.getPhysical_Chip().setDisable(false);
        }
        for (PhysicalChip pc : get_gameboard().get_chips()) {
            if (!pc.get_is_hidden() && !get_curr_player().get_stacks().get(pc.get_color()).check_if_insert_possible(pc)) {
                pc.getPhysical_Chip().setDisable(true);
            }
        }
        for (Player p : players) {
            p.current_player_border_set_visible(false);
        }
        curr_player.current_player_border_set_visible(true);
    }

    public void skip_turn() {
        int amt_chips = get_gameboard().get_chips().size();
        Random rand = new Random();
        int randindex = rand.nextInt(0, amt_chips);
        PhysicalChip corresponding_chip = get_gameboard().get_chips().get(randindex);
        corresponding_chip.uncover();
        corresponding_chip.remove();
        get_curr_player().get_stacks().get(corresponding_chip.get_color()).insert(get_gameboard().transfer_chip_ownership(corresponding_chip));
        next_turn(corresponding_chip.get_clover());

    }

    public Boolean check_if_game_over(){
        // Does not work yet...
        boolean covered_chip_found = Boolean.FALSE;
        for (PhysicalChip pchip : gameboard.get_chips()) {
            if (pchip.get_is_hidden()) {
                covered_chip_found = Boolean.TRUE;
                break;
            }
        }
        return !covered_chip_found;
    }

    public ArrayList<Player> determine_winner(){

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
            for (Player player : players_to_rank) {
                if (player.get_points() == max_value) {
                    player.set_rank(position_to_award);
                    players_not_in_this_rank.remove(player);
                    amount_players_in_this_rank++;
                }
            }
            players_to_rank = players_not_in_this_rank;
            position_to_award += amount_players_in_this_rank;
        }
        ArrayList<Player> players_in_order = new ArrayList<>();
        for (int position = 1; position <= 4; position++) {
            for (Player p : players) {
                if (p.get_rank() == position) {
                    players_in_order.add(p);
                }
            }
        }
        return players_in_order;
    }

    public GameBoard get_gameboard() {
        return gameboard;
    }

    public soundController getSound() {
        return sound;
    }

    public ArrayList<Player> get_players() {
        return players;
    }

    public Player get_curr_player() {
        return curr_player;
    }

    public Button get_takeButton() {
        return takeButton;
    }

    public Button get_leaveButton() {
        return leaveButton;
    }
/*
    public PhysicalChip get_current_pchip() {
        return current_pchip;
    }
*/
    public void game_over() {
        // Enter Game over scene here!
        System.out.println("Game Over!");
        players_in_order = determine_winner();
        get_gameboard().make_blocker_visible(true);
        victoryVBox.setVisible(true);

    }
    public ArrayList<Player> getPlayers_in_order() {
        return players_in_order;
    }

    public void showYouCanTakeString(int value, int color) {
        String text = "";
        int direction = curr_player.get_stacks().get(color).get_direction();
        ArrayList<Integer> takeable_ascending = new ArrayList<>();
        if (direction == 0 || direction == 1) {
            for (PhysicalChip pc : gameboard.get_chips()) {
                if (pc.get_color() == color && pc.get_value() > value) {
                    takeable_ascending.add(pc.get_value());
                }
            }
        }
        Collections.sort(takeable_ascending);
        ArrayList<Integer> takeable_descending = new ArrayList<>();
        if (direction == 0 || direction == -1) {
            for (PhysicalChip pc : gameboard.get_chips()) {
                if (pc.get_color() == color && pc.get_value() < value) {
                    takeable_descending.add(pc.get_value());
                }
            }
        }
        takeable_descending.sort(Collections.reverseOrder());
        switch(direction) {
            case -1 -> {
                if (takeable_descending.size() == 0) {
                    text = "Your Stack is Descending\nYou will not be able to take any more values:\n";
                }
                else{
                    text = "Your Stack is Descending\nYou can take values:\n";
                    for (int val : takeable_descending) {
                        text = text.concat(val + ", ");
                    }
                    text = text.substring(0, text.length() - 2);
                }
            }
            case 0 -> {
                // This Chip would now decide direction
                if (curr_player.get_stacks().get(color).count_chips() == 1) {
                    if (value > curr_player.get_stacks().get(color).get_bound_val()) {
                        text = "Your Stack will become Ascending\nYou will be able to take values:\n";
                        for (int val : takeable_ascending) {
                            text = text.concat(val + ", ");
                        }
                    }
                    else {
                        text = "Your Stack will become Descending\nYou will be able to take values:\n";
                        for (int val : takeable_descending) {
                            text = text.concat(val + ", ");
                        }
                    }
                }
                else {
                    text = "Your Stack is Neutral\nYou can take values:\n";
                    for (int val : takeable_ascending) {
                        text = text.concat(val + ", ");
                    }
                    text = text.substring(0, text.length() - 2);
                    text = text.concat("\n or values:\n");
                    for (int val : takeable_descending) {
                        text = text.concat(val + ", ");
                    }
                }
                text = text.substring(0, text.length() - 2);
            }
            case 1 -> {
                if (takeable_ascending.size() == 0) {
                    text = "Your Stack is Ascending\nYou will not be able to take any more values:\n";
                }
                else {
                    text = "Your Stack is Ascending\nYou can take values:\n";
                    for (int val : takeable_ascending) {
                        text = text.concat(val + ", ");
                    }
                    text = text.substring(0, text.length() - 2);
                }
            }
        }
        youCanTakeBox.setText(text);
        youCanTakeBox.setVisible(true);
    }
    public void hideYouCanTakeString() {
        youCanTakeBox.setVisible(false);
    }



}
