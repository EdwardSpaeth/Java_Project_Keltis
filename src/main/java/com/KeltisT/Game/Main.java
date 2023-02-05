package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Players.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Main {
    public static void start_game(GameEngine gameEngine) {
        for (Player p : gameEngine.get_players()) {
            p.update_points();
        }
        for (PhysicalChip pchip : gameEngine.get_gameboard().get_chips()) {
            pchip.getPhysical_Chip().setOnMouseClicked(new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    chip_has_been_selected(pchip, gameEngine);
                    gameEngine.showYouCanTakeString(pchip.get_value(), pchip.get_color());
                }
            });
            pchip.getPhysical_Chip().setOnMouseEntered(new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!pchip.get_is_hidden()) {
                        gameEngine.showYouCanTakeString(pchip.get_value(), pchip.get_color());
                    }
                }
            });
            pchip.getPhysical_Chip().setOnMouseExited(new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!pchip.get_is_hidden() && !gameEngine.get_gameboard().is_blocker_visible()) {
                        gameEngine.hideYouCanTakeString();
                    }
                }
            });
        }
    }

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
            pChip.remove();
            gameEngine.get_curr_player().update_points();
            gameEngine.next_turn(pChip.get_clover());
        }
    }

    //Boolean take_chip_boolean = new Boolean(Boolean.FALSE);
    private static void take_chip_or_not(GameEngine gameEngine, PhysicalChip pchip) {
        gameEngine.get_takeButton().setVisible(true);
        gameEngine.get_leaveButton().setVisible(true);
        gameEngine.showYouCanTakeString(pchip.get_value(), pchip.get_color());
        if (gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).check_if_insert_possible(pchip) == Boolean.FALSE) {
            gameEngine.get_takeButton().setDisable(true);
        }
        else {
            gameEngine.get_takeButton().setDisable(false);
            gameEngine.get_gameboard().make_blocker_visible(true);
        }
        gameEngine.get_takeButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.getSound().clickSound();
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pchip));
                pchip.remove();
                gameEngine.get_curr_player().update_points();
                gameEngine.next_turn(pchip.get_clover());
                gameEngine.hideYouCanTakeString();
                if (gameEngine.check_if_game_over()) {
                    gameEngine.game_over();
                }
                //gameEngine.game_over();
            }
        });
        gameEngine.get_leaveButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.getSound().clickSound();
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                // If you are just uncovering a chip, you cannot get its clover bonus. Therefore argument is FALSE
                gameEngine.next_turn(Boolean.FALSE);
                gameEngine.hideYouCanTakeString();
                if (gameEngine.check_if_game_over()) {
                    gameEngine.game_over();
                }
            }
        });
    }
}
