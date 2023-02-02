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

        //System.out.println("Main has started!");

        //PhysicalChip pchip = gameengine.get_gameboard().get_chips().get(0);
        ArrayList<String> color_names = new ArrayList<>(Arrays.asList("brown", "yellow", "pink", "green", "blue"));
        for (PhysicalChip pchip : gameEngine.get_gameboard().get_chips()) {
            EventHandler<MouseEvent> myhandler;
            pchip.get_rectangle().setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    chip_has_been_selected(pchip, gameEngine);
                }
            });
            pchip.get_text().setOnMouseClicked(myhandler);
        }

        /*
        for (int x = 0; x < 10; x++) {
        // Change it to line below WHEN it works
        //while (!gameengine.check_if_game_over()) {
            //Boolean clover_was_played = PLAYER_TURN(gameengine.curr_player);
            Boolean clover_was_played = Boolean.FALSE;
            gameengine.next_turn(clover_was_played);
        }
        */
    }

    private static void chip_has_been_selected(PhysicalChip pChip, GameEngine gameEngine) {
        ArrayList<String> color_names = new ArrayList<>(Arrays.asList("brown", "yellow", "pink", "green", "blue"));
        if (pChip.get_is_hidden()) {
            pChip.uncover();
            //System.out.println("Player " + gameEngine.get_curr_player().get_name() + " has uncovered chip with color=" + color_names.get(pChip.get_color()) + " and value=" + pChip.get_value());
            take_chip_or_not(gameEngine, pChip);
        } else {
            // TRANSFER CHIP
            take_chip_or_not(gameEngine, pChip);
        }
    }

    //Boolean take_chip_boolean = new Boolean(Boolean.FALSE);
    private static void take_chip_or_not(GameEngine gameEngine, PhysicalChip pchip) {
        /*
        Button take = new Button("Take Chip");
        take.setLayoutX(500);
        take.setLayoutY(0);
        gameController GameController = new gameController();
        GameController.showButtons();
        */
        gameEngine.get_takeButton().setVisible(true);
        if (gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).check_if_insert_possible(pchip) == Boolean.FALSE) {
            gameEngine.get_takeButton().setDisable(true);
        }
        /*
        Button leave = new Button("Leave Chip");
        leave.setLayoutX(500);
        leave.setLayoutY(100);
        */
        gameEngine.get_takeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.get_takeButton().setDisable(Boolean.TRUE);
                gameEngine.get_leaveButton().setDisable(Boolean.TRUE);
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                //System.out.println("Player wants to take chip");
                gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pchip));
                pchip.remove();
                gameEngine.next_turn(pchip.get_clover());
                if(gameEngine.check_if_game_over()) {
                    game_over(gameEngine);
                }
            }
        });
        gameEngine.get_leaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.get_takeButton().setDisable(Boolean.TRUE);
                gameEngine.get_leaveButton().setDisable(Boolean.TRUE);
                gameEngine.get_takeButton().setVisible(Boolean.FALSE);
                gameEngine.get_leaveButton().setVisible(Boolean.FALSE);
                //System.out.println("Player does not want to take chip");
                // If you are just uncovering a chip, you cannot get its clover bonus. Therefore argument is FALSE
                gameEngine.next_turn(Boolean.FALSE);
                if(gameEngine.check_if_game_over()) {
                    game_over(gameEngine);
                }
            }
        });
        //gameEngine.get_gameboard().get_gameboard_chips_group().getChildren().addAll(take, leave);

    }

    private static void game_over(GameEngine gameEngine) {
        // Enter Game over scene here!
        System.out.println("Game Over!");
        gameEngine.determine_winner();
    }
}
