package com.keltis.edward;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    // DOES NOT WORK YET
    public static void start_game(GameEngine gameengine) {

        System.out.println("Main has started!");

        //PhysicalChip pchip = gameengine.get_gameboard().get_chips().get(0);
        ArrayList<String> color_names = new ArrayList<>(Arrays.asList("brown", "yellow", "pink", "green", "blue"));
        for (PhysicalChip pchip : gameengine.get_gameboard().get_chips()) {
            EventHandler<MouseEvent> myhandler;
            pchip.get_rectangle().setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (pchip.get_is_hidden()) {
                        pchip.uncover();
                        System.out.println("Player " + gameengine.get_curr_player().get_name() + " has uncovered chip with color=" + color_names.get(pchip.get_color()) + " and value=" + pchip.get_value());
                        gameengine.get_curr_player().get_stacks().get(pchip.get_color()).insert(gameengine.get_gameboard().transfer_chip_ownership(pchip));
                        pchip.set_cords(0, 0, 100, 100);
                        pchip.update_ui_elements();
                    } else {
                        // TRANSFER CHIP
                        pchip.get_rectangle().setFill(Color.GREY);
                    }
                    gameengine.next_turn(Boolean.FALSE);
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
        gameengine.determine_winner();
    }
}
