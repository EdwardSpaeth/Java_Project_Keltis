package com.KeltisT.Controllers;

import com.KeltisT.Game.GameTimer;
import com.KeltisT.Players.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controller for the winning Scene.
 */

public class winningSceneController {

    private final soundController Sounds = new soundController();
    @FXML
    public ImageView spotOneBorder, spotTwoBorder, spotThreeBorder, spotFourBorder;
    @FXML
    public ImageView spotOneImage, spotTwoImage, spotThreeImage, spotFourImage;
    @FXML
    public Text spotOneName, spotTwoName, spotThreeName, spotFourName;
    @FXML
    public Text spotOnePoints, spotTwoPoints, spotThreePoints, spotFourPoints;
    private static ArrayList<Player> players_in_order;


    /**
     * @param players_in_order_input gives correct order of the players after a game.
     */

    public void add_players_in_order(ArrayList<Player> players_in_order_input) {
        players_in_order = players_in_order_input;
        set_images(players_in_order);
    }
    /**
     * @param players_in_order gives the image for the correct order of the players after a game.
     */
    public void set_images(ArrayList<Player> players_in_order) {
        ArrayList<ImageView> border_spots = new ArrayList<>(Arrays.asList(spotOneBorder, spotTwoBorder));
        ArrayList<ImageView> image_spots = new ArrayList<>(Arrays.asList(spotOneImage, spotTwoImage));
        ArrayList<Text> playerNames = new ArrayList<>(Arrays.asList(spotOneName, spotTwoName, spotThreeName, spotFourName));
        ArrayList<Text> playerPoints = new ArrayList<>(Arrays.asList(spotOnePoints, spotTwoPoints, spotThreePoints, spotFourPoints));
        if (players_in_order.size() >= 3) {
            border_spots.add(spotThreeBorder);
            image_spots.add(spotThreeImage);
        }
        else {
            spotThreeBorder.setVisible(false);
            spotThreeImage.setImage(new Image ("N_thirdPlayerIMG.png"));
            spotThreeName.setText("Player 3");
            spotThreePoints.setText("");
        }
        if (players_in_order.size() >= 4) {
            border_spots.add(spotFourBorder);
            image_spots.add(spotFourImage);
        }
        else {
            spotFourBorder.setVisible(false);
            spotFourImage.setImage(new Image ("N_fourthPlayerIMG.png"));
            spotFourName.setText("Player 4");
            spotFourPoints.setText("");
        }
        ArrayList<Image> borders = new ArrayList<>();
        borders.add(new Image("GoldenBorder.png"));
        borders.add(new Image("SilverBorder.png"));
        borders.add(new Image("BronzeBorder.png"));
        borders.add(new Image("IronBorder.png"));
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("firstPlayerIMG.png"));
        images.add(new Image("secondPlayerIMG.png"));
        images.add(new Image("thirdPlayerIMG.png"));
        images.add(new Image("fourthPlayerIMG.png"));
        for (int i = 0; i < winningSceneController.players_in_order.size(); i++) {
            border_spots.get(i).setImage(borders.get(winningSceneController.players_in_order.get(i).get_rank()-1));
            image_spots.get(i).setImage(images.get(winningSceneController.players_in_order.get(i).get_order()));
            playerNames.get(i).setText(winningSceneController.players_in_order.get(i).get_name());
            playerPoints.get(i).setText(winningSceneController.players_in_order.get(i).get_points() + " Points");
        }
    }

    /**
     * This function is for the exit button.
     * It ends our program.
     */

    @FXML
    void ExitButton() {
        Sounds.clickSound();
        GameTimer.closeTimer();
        Platform.exit();

    }


}
