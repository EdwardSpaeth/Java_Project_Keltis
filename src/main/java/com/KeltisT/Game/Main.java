package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    // DOES NOT WORK YET
    public static void start_game(GameEngine gameEngine) {

        ArrayList<String> color_names = new ArrayList<>(Arrays.asList("brown", "yellow", "pink", "green", "blue"));
        for (PhysicalChip pchip : gameEngine.get_gameboard().get_chips()) {
            EventHandler<MouseEvent> myhandler;
            //pchip.get_rectangle().setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {
            pchip.getPhysical_Chip().setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    chip_has_been_selected(pchip, gameEngine);
                }
            });
            //pchip.get_text().setOnMouseClicked(myhandler);
        }
    }

    private static void chip_has_been_selected(PhysicalChip pChip, GameEngine gameEngine) {
        if (pChip.get_is_hidden()) {
            pChip.uncover();
            take_chip_or_not(gameEngine, pChip);
        } else {
            // TRANSFER CHIP
            gameEngine.get_curr_player().get_stacks().get(pChip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pChip));
            pChip.remove();
            gameEngine.get_curr_player().update_points();
            gameEngine.next_turn(pChip.get_clover());
        }
    }

    //Boolean take_chip_boolean = new Boolean(Boolean.FALSE);
    private static void take_chip_or_not(GameEngine gameEngine, PhysicalChip pchip) {
        gameEngine.get_gameboard().make_blocker_visible(true);
        gameEngine.get_takeButton().setVisible(true);
        gameEngine.get_leaveButton().setVisible(true);
        if (gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).check_if_insert_possible(pchip) == Boolean.FALSE) {
            gameEngine.get_takeButton().setDisable(true);
        }
        else {
            gameEngine.get_takeButton().setDisable(false);
        }
        gameEngine.get_takeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pchip));
                pchip.remove();
                gameEngine.get_curr_player().update_points();
                gameEngine.next_turn(pchip.get_clover());
                if(gameEngine.check_if_game_over()) {
                    game_over(gameEngine);
                }
            }
        });
        gameEngine.get_leaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                // If you are just uncovering a chip, you cannot get its clover bonus. Therefore argument is FALSE
                gameEngine.next_turn(Boolean.FALSE);
                if(gameEngine.check_if_game_over()) {
                    game_over(gameEngine);
                }
            }
        });
    }

    private static void game_over(GameEngine gameEngine) {
        // Enter Game over scene here!
        System.out.println("Game Over!");
        gameEngine.determine_winner();
    }
}
