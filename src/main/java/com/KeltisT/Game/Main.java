package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    //private static soundController Sound = new soundController();
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
                gameEngine.getSound().clickSound();
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pchip));
                pchip.remove();
                gameEngine.get_curr_player().update_points();
                gameEngine.next_turn(pchip.get_clover());
                if(gameEngine.check_if_game_over()) {
                    gameEngine.game_over();
                }
                gameEngine.game_over();
                gameEngine.getYouCanTakeString(0, true);
            }
        });
        gameEngine.get_leaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.getSound().clickSound();
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                gameEngine.get_gameboard().make_blocker_visible(false);
                // If you are just uncovering a chip, you cannot get its clover bonus. Therefore argument is FALSE
                gameEngine.next_turn(Boolean.FALSE);
                if(gameEngine.check_if_game_over()) {
                    gameEngine.game_over();
                }
            }
        });
    }
}
