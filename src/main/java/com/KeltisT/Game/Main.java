package com.KeltisT.Game;

import com.KeltisT.Chips.PhysicalChip;
import com.KeltisT.Controllers.gameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    // DOES NOT WORK YET
    public static void start_game(GameEngine gameEngine) {

        System.out.println("com.KeltisT.Main has started!");

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
        gameEngine.determine_winner();
    }

    private static void chip_has_been_selected(PhysicalChip pchip, GameEngine gameEngine) {
        ArrayList<String> color_names = new ArrayList<>(Arrays.asList("brown", "yellow", "pink", "green", "blue"));
        if (pchip.get_is_hidden()) {
            pchip.uncover();
            System.out.println("Player " + gameEngine.get_curr_player().get_name() + " has uncovered chip with color=" + color_names.get(pchip.get_color()) + " and value=" + pchip.get_value());
            take_chip_or_not(gameEngine, pchip);
        } else {
            // TRANSFER CHIP
            take_chip_or_not(gameEngine, pchip);
        }
    }

    //Boolean take_chip_boolean = new Boolean(Boolean.FALSE);
    private static void take_chip_or_not(GameEngine gameEngine, PhysicalChip pchip) {
        Button take = new Button("Take Chip");
        take.setLayoutX(500);
        take.setLayoutY(0);
        Button leave = new Button("Leave Chip");
        leave.setLayoutX(500);
        leave.setLayoutY(100);
        take.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                take.setDisable(Boolean.TRUE);
                leave.setDisable(Boolean.TRUE);
                take.setVisible(Boolean.FALSE);
                leave.setVisible(Boolean.FALSE);
                System.out.println("Player wants to take chip");
                gameEngine.get_curr_player().get_stacks().get(pchip.get_color()).insert(gameEngine.get_gameboard().transfer_chip_ownership(pchip));
                pchip.set_cords(0, 0, 100, 100);
                pchip.update_ui_elements();
                gameEngine.next_turn(pchip.get_clover());
                if(gameEngine.check_if_game_over()) {
                    game_over();
                }
            }
        });
        leave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                take.setDisable(Boolean.TRUE);
                leave.setDisable(Boolean.TRUE);
                take.setVisible(Boolean.FALSE);
                leave.setVisible(Boolean.FALSE);
                System.out.println("Player does not want to take chip");
                // If you are just uncovering a chip, you cannot get its clover bonus. Therefore argument is FALSE
                gameEngine.next_turn(Boolean.FALSE);
            }
        });
        gameEngine.get_gameboard().get_gameboard_chips_group().getChildren().addAll(take, leave);

    }

    private static void game_over() {
        // Enter Game over scene here!
    }
}
