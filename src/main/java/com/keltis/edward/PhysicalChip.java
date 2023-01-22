
package com.keltis.edward;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PhysicalChip {
    private Rectangle rectangle;
    private Text text;
    private int row;
    private int col;
    int width;
    int height;

    public PhysicalChip(int row_input, int col_input, int width, int height) {
        int x = col * 100;
        int y = row * 100;
        row = row_input;
        col = col_input;
        rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(Color.GREY);
        text = new Text(x+width/2, y+height/2, "?");
        text.setFont(Font.font(30));
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

    public void add_text(String s) {
        int x = col * 100;
        int y = row * 100;
        text = new Text(x+width/2, y+height/2, s);
        text.setFont(Font.font(30));
    }
}


