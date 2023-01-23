
package com.keltis.edward;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class PhysicalChip {
    private Rectangle rectangle;
    private Text text;
    private int row;
    private int col;
    private int width;
    private int height;

    private Chip chip;

    public PhysicalChip(int row_input, int col_input, int width, int height, Chip c) {
        row = row_input;
        col = col_input;
        int x = col_input * 100;
        int y = row_input * 100;
        rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(Color.GREY);
        rectangle.setCursor(Cursor.HAND);
        text = new Text(x+width/2, y+height/2, "?");
        text.setCursor(Cursor.HAND);
        text.setFont(Font.font("Papyrus", FontWeight.BOLD, FontPosture.ITALIC, 30));
        text.setTextAlignment(TextAlignment.CENTER);
        chip = c;


        EventHandler<MouseEvent> myhandler;
        rectangle.setOnMouseClicked(myhandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chip.get_is_hidden()){
                    // Uncover chip
                    uncover();
                }
                else {
                    // TRANSFER CHIP
                    rectangle.setFill(Color.GREY);
                }
            }
        });
        text.setOnMouseClicked(myhandler);
    }

    public Rectangle get_rectangle(){
        return rectangle;
    }

    public Text get_text(){
        return text;
    }

    public int get_row(){
        return row;
    }

    public int get_col(){
        return col;
    }

    public Chip get_chip() {
        return chip;
    }

    public void uncover () {
        // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
        switch (chip.get_color()) {
            case 0 -> rectangle.setFill(Color.BROWN);
            case 1 -> rectangle.setFill(Color.YELLOW);
            case 2 -> rectangle.setFill(Color.PINK);
            case 3 -> rectangle.setFill(Color.GREEN);
            case 4 -> rectangle.setFill(Color.BLUE);
        }

        text.setText(Integer.toString(chip.get_value()));
        chip.uncover();
    }
}


