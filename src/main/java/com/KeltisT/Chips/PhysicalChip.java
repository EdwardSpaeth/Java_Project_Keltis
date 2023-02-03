
package com.KeltisT.Chips;

import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PhysicalChip extends Chip {
    private AnchorPane Physical_Chip;
    private Rectangle rectangle;
    private Text text, text2;
    private int x;
    private int y;

    private int WIDTH;

    private int HEIGHT;
    Image img = new Image("icon.png");
    Image cloverIMG = new Image("clover.png");
    Image wishStoneIMG = new Image("wonderStone.png");
    ImageView ChipIMG = new ImageView(img);
    Background background = new Background(
            new BackgroundImage(
                    new Image("coveredChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1.0, 1.0, true, true,false, false)
            )
    );
    Background BlueBackground = new Background(
            new BackgroundImage(
                    new Image("blueChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true,false, false
                    )
            )
    );
    Background BrownBackground = new Background(
            new BackgroundImage(
                    new Image("brownChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true,false, false
                    )
            )
    );
    Background PinkBackground = new Background(
            new BackgroundImage(
                    new Image("pinkChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true,false, false
                    )
            )
    );
    Background YellowBackground = new Background(
            new BackgroundImage(
                    new Image("yellowChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true,false, false
                    )
            )
    );
    Background GreenBackground = new Background(
            new BackgroundImage(
                    new Image("greenChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true,false, false
                    )
            )
    );

    public PhysicalChip(int val, int col) {
        super(val, col);
    }

    public void set_ui_elements(Boolean no_shadow) {
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        rectangle.setFill(Color.GREY);
        rectangle.setCursor(Cursor.HAND);

        Physical_Chip = new AnchorPane();
        Physical_Chip.setLayoutX(x);
        Physical_Chip.setLayoutY(y);
        Physical_Chip.setPrefSize(WIDTH, HEIGHT);
        Physical_Chip.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Physical_Chip.setBackground(background);
        Physical_Chip.setCursor(Cursor.HAND);

        //von der Vorlesung Java FX dropshadow idee
        if (!no_shadow) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(6.0f);
            dropShadow.setOffsetY(6.0f);
            dropShadow.setColor(Color.BLACK);
            rectangle.setEffect(dropShadow);
            Physical_Chip.setEffect(dropShadow);
        }

        text = new Text(x+WIDTH/6, y+HEIGHT/2, "?");
        text.setCursor(Cursor.HAND);
        text.setFont(Font.font("Papyrus", 30));
        text.setTextAlignment(TextAlignment.CENTER);


        ChipIMG.setFitHeight(HEIGHT/(5/2));
        ChipIMG.setFitWidth(WIDTH/(7/2));
        ChipIMG.setVisible(false);
        AnchorPane.setBottomAnchor(ChipIMG, 10.0);
        AnchorPane.setRightAnchor(ChipIMG, 10.0);

        text2 = new Text(0, 0, "?");
        text2.setCursor(Cursor.HAND);
        text2.setFont(Font.font("Papyrus", 30));
        text2.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setTopAnchor(text2, 0.0);
        AnchorPane.setLeftAnchor(text2, 15.0);

        Physical_Chip.getChildren().addAll(text2, ChipIMG);
    }
    public void set_cords(int x_input, int y_input, int WIDTH_input, int HEIGHT_input) {
        x = x_input;
        y = y_input;
        WIDTH = WIDTH_input;
        HEIGHT = HEIGHT_input;
    }

    public void remove() {
        rectangle.setVisible(false);
        Physical_Chip.setVisible(false);
        text.setVisible(false);
    }

    public AnchorPane getPhysical_Chip(){
        return Physical_Chip;
    }
    public Rectangle get_rectangle(){
        return rectangle;
    }

    public Text get_text(){
        return text;
    }
    public Text get_text2(){
        return text2;
    }
    public ImageView getChipIMG(){
        return ChipIMG;
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
            case 0 -> {
                rectangle.setFill(Color.SIENNA);
                Physical_Chip.setBackground(BrownBackground);
            }
            case 1 -> {
                rectangle.setFill(Color.GOLD);
                Physical_Chip.setBackground(YellowBackground);
            }
            case 2 -> {
                rectangle.setFill(Color.HOTPINK);
                Physical_Chip.setBackground(PinkBackground);
            }
            case 3 -> {
                rectangle.setFill(Color.MEDIUMSEAGREEN);
                Physical_Chip.setBackground(GreenBackground);
            }
            case 4 -> {
                rectangle.setFill(Color.SKYBLUE);
                Physical_Chip.setBackground(BlueBackground);
            }
        }
        if(get_clover()){
            ChipIMG.setImage(cloverIMG);
            ChipIMG.setVisible(true);
        }
        if(get_wish()){
            ChipIMG.setImage(wishStoneIMG);
            ChipIMG.setVisible(true);
        }
        text.setText(Integer.toString(get_value()));
        set_is_hidden_to_false();
        text2.setText(Integer.toString(get_value()));
        set_is_hidden_to_false();
    }

    public void set_dummy(int value, int color, Boolean clover, Boolean wish, int bonus_points) {
        switch (color) {
            case 0 -> rectangle.setFill(Color.SIENNA);
            case 1 -> rectangle.setFill(Color.GOLD);
            case 2 -> rectangle.setFill(Color.HOTPINK);
            case 3 -> rectangle.setFill(Color.MEDIUMSEAGREEN);
            case 4 -> rectangle.setFill(Color.SKYBLUE);
        }
        text.setStyle("-fx-font: 14 Papyrus;");
        text.setText(Integer.toString(value));
        set_value(value);
        set_color(color);
        rectangle.setVisible(true);
        text.setVisible(true);
    }
}


