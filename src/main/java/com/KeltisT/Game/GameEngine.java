package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Controllers.soundController;
import com.KeltisT.Players.Player;
import com.KeltisT.Players.PlayerConfig;
import com.KeltisT.Players.Stack;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Handles most game logic and is the mediator for the UI elements.
 */
public class GameEngine {
    static SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    static int M_Height = (int) sizeOfMonitor.getSizeOfMonitor()[0];
    static int M_Width = (int) sizeOfMonitor.getSizeOfMonitor()[1];
    private final ArrayList<Player> players;
    private Player curr_player;
    private final GameBoard gameboard;
    private GameTimer timer;
    private final Button takeButton;
    private final Button leaveButton;
    private final soundController sound;
    //private PhysicalChip current_pchip;
    private final VBox victoryVBox;
    private final Label youCanTakeBox;
    private ArrayList<Player> players_in_order;
    private AnchorPane chipSelected;
    private PhysicalChip chipSelectedPC;
    private Boolean isTimerOff = true;

    /**
     * Creates the GameEngine object with the references to the necessary UI elements.
     * @param amount_of_players specifies how many players are playing this game
     * @param timerText  reference of the text, which indicates the time and is thereafter passed to the GameTimer object
     * @param takeButton_input reference for the button with which chips are taken
     * @param leaveButton_input reference for the button with which chips are left
     * @param player_point_labels reference to an ArrayList of labels, which show the player points and are thereafter passed to the Player objects
     * @param blocker reference to the blocker, which is then passed onto the GameBoard class
     * @param current_player_borders reference to an ArrayList of images, which are toggled on or off depending on whom the current player is. These are those rings around the current player.
     * @param gameOverVBox box which shows up when the game is over
     * @param youCanTakeBox_input label for information about which chips are takeable. Elaboration follows in below function
     * @param chipSelected_input reference to the AnchorPane where to show the chip, which is currently tried to be taken (between take and leave button)
     * @param chipSelectedHBox reference to the HBox for the same purpose. It's for showing the chip, which is currently tried to be taken.
     */
    public GameEngine(int amount_of_players, Text timerText, Button takeButton_input, Button leaveButton_input, ArrayList<Label> player_point_labels,
                      AnchorPane blocker, ArrayList<ImageView> current_player_borders, VBox gameOverVBox, Label youCanTakeBox_input, AnchorPane chipSelected_input, HBox chipSelectedHBox){
        players = new ArrayList<>();
        gameboard = new GameBoard(blocker);
        ArrayList<String> player_names = PlayerConfig.get_player_config(amount_of_players);
        for (int i = 0; i < amount_of_players; i++){
            players.add(new Player(player_names.get(i), i, player_point_labels.get(i), current_player_borders.get(i)));
        }
        curr_player = players.get(0);
        curr_player.current_player_border_set_visible(true);
        if(isTimerOff){
            timerText.setText("∞");
        }
        else{
            timer = new GameTimer(timerText, this);
            timer.timer();
        }
        takeButton = takeButton_input;
        leaveButton = leaveButton_input;
        sound = new soundController();
        victoryVBox = gameOverVBox;
        youCanTakeBox = youCanTakeBox_input;
        chipSelected = chipSelected_input;
        chipSelectedHBox.setAlignment(Pos.CENTER);
        chipSelectedPC = new PhysicalChip(0, 0);
        chipSelectedPC.set_cords(0,0, M_Width / 25, M_Height / 25);
        chipSelectedPC.set_ui_elements();
        chipSelectedPC.set_dummy(0, 0, false, false, 2);
        chipSelectedPC.getText().setCursor(Cursor.DEFAULT);
        chipSelectedHBox.getChildren().add(chipSelectedPC.getPhysical_Chip());
        chipSelected.setVisible(false);
        chipSelected.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        chipSelected.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    /**
     * Function which changes the current player and updates points. Also sets the current player border.
     * @param clover_was_played specifies whether the current player has taken a clover. If so, then they may do another turn
     */
    public void next_turn(Boolean clover_was_played){
        show_chipSelected(false);
        curr_player.update_points();
        curr_player.current_player_border_set_visible(false);
        //curr_player.update_points();
        if (!clover_was_played) {
            curr_player = players.get((curr_player.get_order() + 1) % players.size());
        }
        if(!isTimerOff) {
            timer.refresh();
        }
        for (Player p : players) {
            p.update_points();
        }
        for (PhysicalChip pc : get_gameboard().get_chips()) {
            pc.getPhysical_Chip().setDisable(false);
        }
        for (PhysicalChip pc : get_gameboard().get_chips()) {
            if (!pc.get_is_hidden() && !get_curr_player().get_stacks().get(pc.get_color()).check_if_insert_possible(pc)) {
                pc.getPhysical_Chip().setDisable(true);
            }
        }
        curr_player.current_player_border_set_visible(true);
    }

    /**
     * Skips a turn if the time on the Timer has run out
     */
    public void skip_turn() {
        if (leaveButton.isVisible()) {
            get_takeButton().setVisible(Boolean.FALSE);
            get_leaveButton().setVisible(Boolean.FALSE);
            get_gameboard().make_blocker_visible(false);
            // If you are just uncovering a chip, you cannot get its clover bonus. Therefore argument is FALSE
            next_turn(Boolean.FALSE);
            hideYouCanTakeString();
            if (check_if_game_over()) {
                game_over();
            }
            return;
        }
        int amt_chips = get_gameboard().get_chips().size();
        Random rand = new Random();
        int randindex = rand.nextInt(0, amt_chips);
        PhysicalChip corresponding_chip = get_gameboard().get_chips().get(randindex);
        corresponding_chip.uncover();
        if (get_curr_player().get_stacks().get(corresponding_chip.get_color()).check_if_insert_possible(corresponding_chip)) {
            get_curr_player().get_stacks().get(corresponding_chip.get_color()).insert(get_gameboard().transfer_chip_ownership(corresponding_chip));
            next_turn(corresponding_chip.get_clover());
        }
        else {
            next_turn(false);
        }
    }

    /**
     * Function to check whether a game is over or not
     * @return boolean of whether game is over or not
     */
    public Boolean check_if_game_over(){
        boolean covered_chip_found = Boolean.FALSE;
        for (PhysicalChip pchip : gameboard.get_chips()) {
            if (pchip.get_is_hidden()) {
                covered_chip_found = Boolean.TRUE;
                break;
            }
        }
        return !covered_chip_found;
    }

    /**
     * Determines the ranking of the players at the end of the game.
     * @return ArrayList of players, which are sorted by ranking.
     */
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

        for (Player player : players_to_rank) {
            players_to_rank.remove(player);
        }

        return players_in_order;
    }

    /**
     * Getter for the game board.
     * @return GameBoard instance
     */
    public GameBoard get_gameboard() {
        return gameboard;
    }

    /**
     * Getter for the sound controller.
     * @return soundController instance
     */
    public soundController getSound() {
        return sound;
    }

    /**
     * Getter for the players.
     * @return ArrayList of Player instances
     */
    public ArrayList<Player> get_players() {
        return players;
    }

    /**
     * Getter for the player whose turn it currently is.
     * @return Player instance of the player whose turn it currently is
     */
    public Player get_curr_player() {
        return curr_player;
    }

    /**
     * Getter for the button with which to take a chip.
     * @return Button instance representing the take button
     */
    public Button get_takeButton() {
        return takeButton;
    }

    /**
     * Getter for the button with which to leave a chip.
     * @return Button instance representing the leave button
     */
    public Button get_leaveButton() {
        return leaveButton;
    }

    /**
     * Function which terminates a game once its over.
     */
    public void game_over() {
        // Enter Game over scene here!
        players_in_order = determine_winner();
        get_gameboard().make_blocker_visible(true);
        victoryVBox.setVisible(true);
    }

    /**
     * Getter for the players in ranking order as calculated by determine_winner()
     * @return ArrayList of players in order according to rank
     */
    public ArrayList<Player> getPlayers_in_order() {
        return players_in_order;
    }

    /**
     * Shows the label, which tells you which chips you will be able to take if you take the chip in question.
     * @param value value of the chip hovered over or uncovered
     * @param color color of the chip hovered over or uncovered
     */
    public void showYouCanTakeString(int value, int color) {
        String text = "";
        Stack corresponding_stack = curr_player.get_stacks().get(color);
        int direction = corresponding_stack.get_direction();
        ArrayList<Integer> future_takeable_ascending = new ArrayList<>();
        ArrayList<Integer> future_takeable_descending = new ArrayList<>();
        if (direction == 0 || direction == 1) {
            for (PhysicalChip pc : gameboard.get_chips()) {
                if (pc.get_color() == color && corresponding_stack.check_if_insert_possible(pc) && pc.get_value() > value) {
                    future_takeable_ascending.add(pc.get_value());
                }
                if (pc.get_color() == color && corresponding_stack.check_if_insert_possible(pc) && pc.get_value() < value) {
                    future_takeable_descending.add(pc.get_value());
                }
            }
        }
        Collections.sort(future_takeable_ascending);
        future_takeable_descending.sort(Collections.reverseOrder());
        switch(direction) {
            case -1 -> {
                if (future_takeable_descending.size() == 0) {
                    text = "Your Stack is Descending\nYou will not be able to take any more values\n";
                }
                else{
                    text = "Your Stack is Descending\nYou will be able to take values:\n";
                    for (int val : future_takeable_descending) {
                        text = text.concat(val + ", ");
                    }
                    text = text.substring(0, text.length() - 2);
                }
            }
            case 0 -> {
                // This Chip would now decide direction
                if (curr_player.get_stacks().get(color).count_chips() == 1) {
                    if (value > curr_player.get_stacks().get(color).get_bound_val()) {
                        text = "Your Stack will become Ascending\nYou will be able to take ";
                        if (future_takeable_ascending.size() == 0) {
                            text = text.concat("Nothing\n");
                        }
                        else {
                            text = text.concat("values:\n");
                            for (int val : future_takeable_ascending) {
                                text = text.concat(val + ", ");
                            }
                            text = text.substring(0, text.length() - 2);
                        }
                    }
                    else {
                        text = "Your Stack will become Descending\nYou will be able to take ";
                        if (future_takeable_descending.size() == 0) {
                            text = text.concat("Nothing\n");
                        }
                        else {
                            text = text.concat("values:\n");
                            for (int val : future_takeable_descending) {
                                text = text.concat(val + ", ");
                            }
                            text = text.substring(0, text.length() - 2);
                        }
                    }
                }
                // This chip will not decide direction --> This is the first chip
                else {
                    text = "Your Stack is Neutral\nYou will be able to take ";
                    if (future_takeable_ascending.size() == 0) {
                        text = text.concat("Nothing");
                    }
                    else {
                        text = text.concat("values:\n");
                        for (int val : future_takeable_ascending) {
                            text = text.concat(val + ", ");
                        }
                        text = text.substring(0, text.length() - 2);
                    }
                    text = text.concat("\n or ");
                    if (future_takeable_descending.size() == 0) {
                        text = text.concat("Nothing");
                    }
                    else {
                        text = text.concat("values:\n");
                        for (int val : future_takeable_descending) {
                            text = text.concat(val + ", ");
                        }
                        text = text.substring(0, text.length() - 2);
                    }
                }
            }
            case 1 -> {
                if (future_takeable_ascending.size() == 0) {
                    text = "Your Stack is Ascending\nYou will not be able to take any more values\n";
                }
                else {
                    text = "Your Stack is Ascending\nYou can take values:\n";
                    for (int val : future_takeable_ascending) {
                        text = text.concat(val + ", ");
                    }
                    text = text.substring(0, text.length() - 2);
                }
            }
        }
        youCanTakeBox.setText(text);
        youCanTakeBox.setVisible(true);
    }

    /**
     * Hides the label, which tells you which chips you will be able to take if you take the chip in question.
     */
    public void hideYouCanTakeString() {
        youCanTakeBox.setVisible(false);
    }
    /**
     * This is a function used for debugging.
     * This plays pretty much an entire game automatically and leaves just 2 uncovered chips to uncover manually.
     * Not in usage under ordinary circumstances.
     */
    public void play_the_game_for_me() {
        int amt_covered_chips;
        while (true) {
            amt_covered_chips = 0;
            for (PhysicalChip pchip : gameboard.get_chips()) {
                if (pchip.get_is_hidden()) {
                    amt_covered_chips++;
                }
            }
            if (amt_covered_chips < 3) {
                break;
            }
            skip_turn();
        }
        for (Player p : players) {
            p.update_points();
        }
    }

    /**
     * Setter for the chip currently selected.
     * @param pc the chip which is currently selected
     */
    public void set_chipSelected(PhysicalChip pc) {
        chipSelectedPC.set_dummy(pc.get_value(), pc.get_color(), pc.get_clover(), pc.get_wish(), pc.get_bonus());
        show_chipSelected(true);
    }

    /**
     * Displays the chip which is currently selected (between take and leave button).
     * @param b boolean of whether to hide the currently selected chip or show it
     */
    public void show_chipSelected(Boolean b) {
        chipSelected.setVisible(b);
    }

}
