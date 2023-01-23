package com.keltis.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.util.ArrayList;

public class gameController {


    //test


    public ImageView Grey;

    @FXML
    private Label player_1_field;
    @FXML
    private Label player_2_field;

    @FXML
    private Label player_3_field;

    @FXML
    private Label player_4_field;


    public void newNames(ArrayList<String> names) {
        //testLabel.setText(names.get(0) + " " + names.get(1) + " " + names.get(2) + " " + names.get(3));
        com.keltis.edward.PlayerConfig.set_player_config(names);

        player_1_field.setText(names.get(0));
        player_2_field.setText(names.get(1));
        if (names.size() >= 3) {
            player_3_field.setText(names.get(2));
        }
        if (names.size() >= 4) {
            player_4_field.setText(names.get(3));
        }

    }

    public void ZoomIn(MouseEvent mouseEvent) {
        Grey.translateZProperty().set(Grey.getTranslateZ() - 1000);
    }

    public void exit_was_clicked(MouseEvent mouseEvent) {
        Platform.exit();
    }
    // Back Button - Back to Player Number

    public static Group returnroot() {
        Group root2 = new Group();

        ArrayList<com.keltis.edward.Chip> chips = com.keltis.edward.ChipGenerator.generate_chips(5,
                11, 5, 5, 5);


        int width = 50;
        int height = 50;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 11; col++) {

                //com.keltis.edward.PhysicalChip pchip = new com.keltis.edward.PhysicalChip(row, col, width, height);
                int x = col * 100;
                int y = row * 100;
                //von der Vorlesung Java FX dropshadow idee
                DropShadow dropShadow = new DropShadow();
                dropShadow.setOffsetX(6.0f);
                dropShadow.setOffsetY(6.0f);
                dropShadow.setColor(Color.BLACK);
                Rectangle rectangle = new Rectangle(x, y, width, height);
                rectangle.setFill(Color.GREY);
                rectangle.setCursor(Cursor.HAND);
                rectangle.setEffect(dropShadow);
                Text text = new Text(x + width / 4, y + height / 2, "?");
                text.setCursor(Cursor.HAND);
                text.setFont(Font.font("Papyrus", FontWeight.BOLD, FontPosture.ITALIC, 30));
                int desired_color = chips.get(row * 11 + col).get_color();

                String desired_value = Integer.toString(chips.get(row * 11 + col).get_value());
                EventHandler<MouseEvent> myhandler;
                rectangle.setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
                        switch (desired_color) {
                            case 0 -> rectangle.setFill(Color.BROWN);
                            case 1 -> rectangle.setFill(Color.YELLOW);
                            case 2 -> rectangle.setFill(Color.PINK);
                            case 3 -> rectangle.setFill(Color.GREEN);
                            case 4 -> rectangle.setFill(Color.BLUE);
                        }


                        //pchip.get_text().setText(Integer.toString(chips.get(index).get_value()));
                        //System.out.println(pchip.get_pane().getChildren());
                        //String number = Integer.toString(chips.get(index).get_value());
                        text.setText(desired_value);
                        text.setTextAlignment(TextAlignment.CENTER);
                        //pchip.get_text().setText(Integer.toString(chips.get(index).get_value()));
                    }
                });
                text.setOnMouseClicked(myhandler);
                root2.getChildren().addAll(rectangle, text);
            }
        }
        return root2;
    }
}


