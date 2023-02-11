package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Players.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Class where most event handlers are defined.
 */
public class Main {
    /**
     * Function to start the game.
     * @param gameEngine GameEngine object which is responsible for this game
     */
    public static void start_game(GameEngine gameEngine) {
        for (Player p : gameEngine.get_players()) {
            p.update_points();
        }
        for (PhysicalChip pChip : gameEngine.get_gameboard().get_chips()) {
            pChip.getPhysical_Chip().setOnMouseClicked(new EventHandler<>() {
                /**
                 * Event when a chip is clicked. Proceed to chip_has_been_selected() function.
                 * @param mouseEvent MouseEvent instance
                 */
                @Override
                public void handle(MouseEvent mouseEvent) {
                    chip_has_been_selected(pChip, gameEngine);
                }
            });
            pChip.getPhysical_Chip().setOnMouseEntered(new EventHandler<>() {
                /**
                 * Event when a chip is hovered over. Call showYouCanTakeString() function if chip is visible.
                 * @param mouseEvent MouseEvent instance
                 */
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!pChip.get_is_hidden()) {
                        if (gameEngine.get_curr_player().get_stacks().get(pChip.get_color()).check_if_insert_possible(pChip)){
                            gameEngine.showYouCanTakeString(pChip.get_value(), pChip.get_color());
                        }
                    }
                }
            });
            pChip.getPhysical_Chip().setOnMouseExited(new EventHandler<>() {
                /**
                 * Event when a chip is not hovered over anymore. Hide youCanTakeString after showing it with the setOnMouseEntered event or after uncovering a chip.
                 * @param mouseEvent MouseEvent instance
                 */
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!pChip.get_is_hidden() && !gameEngine.get_gameboard().is_blocker_visible()) {
                        gameEngine.hideYouCanTakeString();
                    }
                }
            });
        }
    }

    /**
     * Function which calls the chip to be added to a player's inventory if it's uncovered. If not it initiates the function take_chip_or_not().
     * @param pChip PhysicalChip instance which has been selected
     * @param gameEngine GameEngine instance which is responsible for the game
     */
    private static void chip_has_been_selected(PhysicalChip pChip, GameEngine gameEngine) {
        if (pChip.get_is_hidden()) {
            pChip.uncover();
            if(pChip.get_clover()){
                gameEngine.getSound().cloverSound();
            }
            else if(pChip.get_wish()){
                gameEngine.getSound().wishStoneSound();
            }
            else if(pChip.get_bonus() > 0){
                gameEngine.getSound().bonusPointsSound();
            }
            else {
                gameEngine.getSound().clickSound();
            }
            take_chip_or_not(gameEngine, pChip);
        } else {
            // TRANSFER CHIP
            gameEngine.getSound().clickSound();
            gameEngine.get_curr_player().get_stacks().get(pChip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pChip));
            gameEngine.next_turn(pChip.get_clover());
        }
    }

    /**
     * Decide what to do after uncovering a chip.
     * @param gameEngine GameEngine instance
     * @param pChip PhysicalChip instance which has been uncovered
     */
    private static void take_chip_or_not(GameEngine gameEngine, PhysicalChip pChip) {
        gameEngine.set_chipSelected(pChip);
        gameEngine.get_takeButton().setVisible(true);
        gameEngine.get_leaveButton().setVisible(true);
        if (gameEngine.get_curr_player().get_stacks().get(pChip.get_color()).check_if_insert_possible(pChip)) {
            gameEngine.showYouCanTakeString(pChip.get_value(), pChip.get_color());
        }
        gameEngine.get_takeButton().setDisable(gameEngine.get_curr_player().get_stacks().get(pChip.get_color()).check_if_insert_possible(pChip) == Boolean.FALSE);
        gameEngine.get_gameboard().make_blocker_visible(true);
        gameEngine.get_takeButton().setOnAction(new EventHandler<>() {
            /**
             * If the player wants to take the chip.
             * @param actionEvent ActionEvent instance
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.getSound().clickSound();
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                gameEngine.get_curr_player().get_stacks().get(pChip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pChip));
                gameEngine.next_turn(pChip.get_clover());
                gameEngine.hideYouCanTakeString();
                if (gameEngine.check_if_game_over()) {
                    gameEngine.game_over();
                }
            }
        });
        gameEngine.get_leaveButton().setOnAction(new EventHandler<>() {
            /**
             * If the player does not want to take the chip.
             * @param actionEvent ActionEvent instance
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.getSound().clickSound();
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                // If you are just uncovering a chip, you cannot get its clover bonus, therefore argument is FALSE
                gameEngine.next_turn(Boolean.FALSE);
                gameEngine.hideYouCanTakeString();
                if (gameEngine.check_if_game_over()) {
                    gameEngine.game_over();
                }
            }
        });
    }
}
