package com.KeltisT.Controllers;

import com.KeltisT.Game.GameTimer;
import com.KeltisT.Players.Player;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class winningSceneController {

    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
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

    @FXML
    // Next Button
    public void AgainButton() throws IOException {
        /*Sounds.clickSound();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/gameT.fxml"));
        root = loader.load();

        ArrayList<String> chosen_player_names = new ArrayList<>();
        chosen_player_names.add(firstPlayer.getText());
        chosen_player_names.add(secondPlayer.getText());
        if (amount >= 3) {
            chosen_player_names.add(thirdPlayer.getText());
        }
        if (amount >= 4) {
            chosen_player_names.add(fourthPlayer.getText());
        }
        PlayerConfig.set_player_config(chosen_player_names);

        gameController GameController = loader.getController();
        GameController.setPlayer_3_4(amount);
        GameController.setChipField(amount);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root, WIDTH, HEIGHT);

        GameController.getKeyControls(scene);

        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("l"));
        stage.setFullScreen(true);
        stage.show();
*/
    }
    public void add_players_in_order(ArrayList<Player> players_in_order_input) {
        players_in_order = players_in_order_input;
        set_images(players_in_order);
    }
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
        System.out.println(winningSceneController.players_in_order.size());
        System.out.println("players in order" + winningSceneController.players_in_order.size());
        for (int i = 0; i < winningSceneController.players_in_order.size(); i++) {
        //for (int i = 0; i < 3; i++) {
            System.out.println("I ist: " + i);
            border_spots.get(i).setImage(borders.get(winningSceneController.players_in_order.get(i).get_rank()-1));
            image_spots.get(i).setImage(images.get(winningSceneController.players_in_order.get(i).get_order()));
            playerNames.get(i).setText(winningSceneController.players_in_order.get(i).get_name());
            playerPoints.get(i).setText(winningSceneController.players_in_order.get(i).get_points() + " Points");
        }
    }

    @FXML
    public void MenuButton(ActionEvent event) throws IOException{
        Sounds.clickSound();
        GameTimer.closeTimer();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/start.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
        for (Player p : players_in_order) {
            System.out.println(p.get_name());
        }

    }

    @FXML
    void ExitButton() {
        Sounds.clickSound();
        GameTimer.closeTimer();
        Platform.exit();

    }


}
