
package com.KeltisT.Chips;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * A subclass of Chip, which in addition to its inherited information, also contains UI elements such as coordinates and images.
 */
public class PhysicalChip extends Chip {
    private AnchorPane Physical_Chip;
    private Text text;
    private int x;
    private int y;
    private double WIDTH;
    private double HEIGHT;
    Image img = new Image("icon.png");
    Image cloverIMG = new Image("clover.png");
    Image wishStoneIMG = new Image("wishStone.png");
    Image bonusOneIMG = new Image("BP1.png");
    Image bonusTwoIMG = new Image("BP2.png");
    Image bonusThreeIMG = new Image("BP3.png");
    ImageView ChipIMG = new ImageView(img);

    public Background background = new Background(
            new BackgroundImage(
                    new Image("coveredChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1.0, 1.0, true, true, false, false)
            )
    );
    public Background BlueBackground = new Background(
            new BackgroundImage(
                    new Image("blueChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true, false, false
                    )
            )
    );
    public Background BrownBackground = new Background(
            new BackgroundImage(
                    new Image("brownChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true, false, false
                    )
            )
    );
    public Background PinkBackground = new Background(
            new BackgroundImage(
                    new Image("pinkChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true, false, false
                    )
            )
    );
    public Background YellowBackground = new Background(
            new BackgroundImage(
                    new Image("yellowChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true, false, false
                    )
            )
    );
    public Background GreenBackground = new Background(
            new BackgroundImage(
                    new Image("greenChips.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0, 1.0, true, true, false, false
                    )
            )
    );

    /**
     * The constructor only calls the constructor of the superclass.
     * UI elements are set later.
     * @param val Value ranging from 0 to 10
     * @param col Integer-coded color from 0 to 4.
     */
    public PhysicalChip(int val, int col) {
        super(val, col);
    }

    /**
     * Here the UI elements such as the images, are set.
     */
    public void set_ui_elements() {
        Physical_Chip = new AnchorPane();
        Physical_Chip.setLayoutX(x);
        Physical_Chip.setLayoutY(y);
        Physical_Chip.setPrefSize(WIDTH, HEIGHT);
        Physical_Chip.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Physical_Chip.setBackground(background);
        Physical_Chip.setCursor(Cursor.HAND);

        ChipIMG.setFitHeight(HEIGHT / (2.5));
        ChipIMG.setFitWidth(WIDTH / (3.5));
        ChipIMG.setVisible(false);
        AnchorPane.setBottomAnchor(ChipIMG, 10.0);
        AnchorPane.setRightAnchor(ChipIMG, 8.0);

        text = new Text(0, 0, "");
        text.setCursor(Cursor.HAND);
        text.setFont(Font.font("Papyrus", 30));
        text.setTextAlignment(TextAlignment.LEFT);
        AnchorPane.setTopAnchor(text, 0.0);
        AnchorPane.setLeftAnchor(text, 18.0);

        Physical_Chip.getChildren().addAll(text, ChipIMG);
    }

    /**
     * Used for assigning coordinates to a Chip.
     * @param x_input x coordinate
     * @param y_input y coordinate
     * @param WIDTH_input specifies the width
     * @param HEIGHT_input specifies the height
     */
    public void set_cords(int x_input, int y_input, int WIDTH_input, int HEIGHT_input) {
        x = x_input;
        y = y_input;
        WIDTH = WIDTH_input;
        HEIGHT = HEIGHT_input;
    }

    /**
     * Visually removes a chip from the middle of the board. Makes UI invisible
     */
    public void remove() {
        Physical_Chip.setVisible(false);
        text.setVisible(false);
    }

    /**
     * Getter for the Physical_Chip AnchorPane, which contains UI elements
     * @return the AnchorPane holding it all together
     */
    public AnchorPane getPhysical_Chip() {
        return Physical_Chip;
    }

    /**
     * Function to uncover a chip and update its UI accordingly
     */
    public void uncover() {
        // "brown"=0, "yellow"=1, "pink"=2, "green"=3, "blue"=4
        switch (get_color()) {
            case 0 -> Physical_Chip.setBackground(BrownBackground);
            case 1 -> Physical_Chip.setBackground(YellowBackground);
            case 2 -> Physical_Chip.setBackground(PinkBackground);
            case 3 -> Physical_Chip.setBackground(GreenBackground);
            case 4 -> Physical_Chip.setBackground(BlueBackground);
        }
        if (get_clover()) {
            ChipIMG.setImage(cloverIMG);
            ChipIMG.setVisible(true);
        } else if (get_wish()) {
            ChipIMG.setImage(wishStoneIMG);
            ChipIMG.setVisible(true);
        } else if (get_bonus() > 0) {
            switch (get_bonus()) {
                case 1 -> {
                    ChipIMG.setImage(bonusOneIMG);
                    ChipIMG.setVisible(true);
                }
                case 2 -> {
                    ChipIMG.setImage(bonusTwoIMG);
                    ChipIMG.setVisible(true);
                }
                case 3 -> {
                    ChipIMG.setImage(bonusThreeIMG);
                    ChipIMG.setVisible(true);
                }
            }
        }
        text.setText(Integer.toString(get_value()));
        set_is_hidden_to_false();
    }

    /**
     * Applied to the invisible chips in the players' stacks.
     * Changes the chips information depending on input arguments and makes it visible.
     * @param value_input specifies the value
     * @param color_input specifies the color
     * @param clover_input specifies whether it has a clover
     * @param wish_input specifies whether it has a wish stone
     * @param bonus_points_input specifies how many bonus points it grants
     */
    public void set_dummy(int value_input, int color_input, Boolean clover_input, Boolean wish_input, int bonus_points_input) {
        set_value(value_input);
        set_color(color_input);
        if (clover_input) {
            set_clover();
        }
        if (wish_input) {
            set_wish();
        }
        set_bonus(bonus_points_input);

        switch (color_input) {
            case 0 -> Physical_Chip.setBackground(BrownBackground);
            case 1 -> Physical_Chip.setBackground(YellowBackground);
            case 2 -> Physical_Chip.setBackground(PinkBackground);
            case 3 -> Physical_Chip.setBackground(GreenBackground);
            case 4 -> Physical_Chip.setBackground(BlueBackground);
        }
        if (clover_input) {
            ChipIMG.setImage(cloverIMG);
            ChipIMG.setVisible(true);
        }
        else if (wish_input) {
            ChipIMG.setImage(wishStoneIMG);
            ChipIMG.setVisible(true);
        }
        else if (bonus_points_input > 0) {
            switch (bonus_points_input) {
                case 1 -> {
                    ChipIMG.setImage(bonusOneIMG);
                    ChipIMG.setVisible(true);
                }
                case 2 -> {
                    ChipIMG.setImage(bonusTwoIMG);
                    ChipIMG.setVisible(true);
                }
                case 3 -> {
                    ChipIMG.setImage(bonusThreeIMG);
                    ChipIMG.setVisible(true);
                }
            }
        }
        else {
            ChipIMG.setVisible(false);
        }
        text.setText(Integer.toString(value_input));
        set_is_hidden_to_false();
        getPhysical_Chip().setVisible(true);
        Physical_Chip.setCursor(Cursor.DEFAULT);
    }

    /**
     * Getter for the text of the chip.
     * @return text of the chip.
     */
    public Text getText() {
        return text;
    }
}


