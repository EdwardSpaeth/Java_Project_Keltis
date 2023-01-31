
package com.KeltisT.Chips;

import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class PhysicalChip extends Chip {
    private Rectangle rectangle;
    private Text text;
    private int x;
    private int y;

    private int WIDTH;

    private int HEIGHT;

    //private Chip chip;

    public PhysicalChip(int val, int col) {
        super(val, col);
    }

    public void set_ui_elements(Boolean no_shadow) {
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        rectangle.setFill(Color.GREY);
        rectangle.setCursor(Cursor.HAND);

        //von der Vorlesung Java FX dropshadow idee
        if (!no_shadow) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(6.0f);
            dropShadow.setOffsetY(6.0f);
            dropShadow.setColor(Color.BLACK);
            rectangle.setEffect(dropShadow);
        }

        text = new Text(x+WIDTH/3, y+HEIGHT/3, "?");
        text.setCursor(Cursor.HAND);
        text.setFont(Font.font("Papyrus", FontWeight.BOLD, FontPosture.ITALIC, 20));
        text.setTextAlignment(TextAlignment.CENTER);
    }
    public void set_cords(int x_input, int y_input, int WIDTH_input, int HEIGHT_input) {
        x = x_input;
        y = y_input;
        WIDTH = WIDTH_input;
        HEIGHT = HEIGHT_input;
    }

    public void update_ui_elements() {
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(WIDTH);
        rectangle.setHeight(HEIGHT);
    }

    public Rectangle get_rectangle(){
        return rectangle;
    }

    public Text get_text(){
        return text;
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public void uncover () {
        // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
        // "brown" = "sienna", yellow = "gold", "pink" = "hotpink", "green" = "mediumseagreen", "blue" = "skyblue"
        switch (get_color()) {
            case 0 -> rectangle.setFill(Color.SIENNA);
            case 1 -> rectangle.setFill(Color.GOLD);
            case 2 -> rectangle.setFill(Color.HOTPINK);
            case 3 -> rectangle.setFill(Color.MEDIUMSEAGREEN);
            case 4 -> rectangle.setFill(Color.SKYBLUE);
        }

        text.setText(Integer.toString(get_value()));
        set_is_hidden_to_false();
    }
}


