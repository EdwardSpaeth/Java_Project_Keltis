package com.KeltisT.Controllers;

import com.KeltisT.Game.GameEngine;
import com.KeltisT.Game.GameTimer;
import com.KeltisT.Game.Main;
import com.KeltisT.Players.Player;
import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

//test
public class gameController {
    @FXML
    public VBox Player1, Player2, Player3, Player4;
    @FXML
    public Label Player1_P, Player2_P, Player3_P, Player4_P;
    @FXML
    public Label Player3_L, Player4_L;
    @FXML
    public Label PauseLabel, TimerLabel;
    @FXML
    public AnchorPane PausePane;
    public Button takeButton, leaveButton;
    public HBox chipButtons;
    public ImageView currentFirst, currentSecond, currentThird, currentFourth;
    public VBox toggleAudio;
    public Button MuteButton;
    Path filename = Path.of("src/main/resources/Rules.txt");
    String rulesText;

    {
        try {
            rulesText = Files.readString(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Stage RuleStage = new Stage();
    @FXML
    public ImageView Player3_V, Player3_NonV, Player4_V, Player4_NonV;
    @FXML
    public Text Player1_T, Player2_T, Player3_T, Player4_T;
    @FXML
    public Text TimerText;
    @FXML
    public VBox ExitVBox;
    public AnchorPane blocker;
    @FXML
    public StackPane chipsStackPane, player1_chips, player2_chips, player3_chips, player4_chips;
    private final SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private final double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private final double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];
    private soundController Sounds = new soundController();
    private Boolean MenuOrExit, toggleMute = false;

    // Settings for Player 3 and Player 4
    public void setPlayer_3_4(int player_amount) {

        boolean third = false;
        boolean fourth = false;
        if (player_amount >= 3) {
            third = true;
        }
        if (player_amount >= 4) {
            fourth = true;
        }


        Player3_V.setVisible(third);
        Player3_NonV.setVisible(!third);
        if (!third) {
            Player3_T.setFill(Color.LIGHTGRAY);
            Player3_T.setStroke(Color.LIGHTGRAY);
            Player3_P.setTextFill(Color.LIGHTGRAY);
            Player3_L.setStyle("-fx-border-color: lightgray;");
        }

        Player4_V.setVisible(fourth);
        Player4_NonV.setVisible(!fourth);
        if (!fourth) {
            Player4_T.setFill(Color.LIGHTGRAY);
            Player4_T.setStroke(Color.LIGHTGRAY);
            Player4_P.setTextFill(Color.LIGHTGRAY);
            Player4_L.setStyle("-fx-border-color: lightgray;");
        }

        ArrayList<String> player_names = com.KeltisT.Players.PlayerConfig.get_player_config(player_amount);

        Player1_T.setText(player_names.get(0));
        Player2_T.setText(player_names.get(1));
        if (player_amount >= 3) {
            Player3_T.setText(player_names.get(2));
        }
        if (player_amount >= 4) {
            Player4_T.setText(player_names.get(3));
        }
    }

    // Key Events

    // R Key
    public void Rules() throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Fxml/rulesInGame.fxml"));
        Path filename = Path.of("src/main/resources/Rules.txt");
        String rulesText = Files.readString(filename);
        Text text = new Text(rulesText);
        text.setFill(Color.RED);
        text.setStroke(Color.YELLOW);
        text.setStrokeWidth(0.5);
        text.setFont(Font.font("Papyrus", FontPosture.REGULAR, 15.5));
        text.setTextAlignment(TextAlignment.LEFT);
        StackPane stack = new StackPane(text);
        stack.setAlignment(Pos.CENTER);
        stack.setMinSize(WIDTH - 370, HEIGHT + 70);
        pane.getChildren().add(stack);
        Scene rulesScene = new Scene(pane);

        RuleStage.setTitle("Rules of Keltis");
        RuleStage.setScene(rulesScene);
        Image icon = new Image("icon.png");
        RuleStage.getIcons().add(icon);
        RuleStage.show();

    }

    // A Key
    public void Audio() {
        if(toggleAudio.isVisible()){
            toggleAudio.setVisible(false);
        }
        else {
            toggleAudio.setVisible(true);
        }
    }

    // P Key
    Boolean was_already_blocked;
    public void Pause() {
        // PAUSE
        if (!PausePane.isVisible()) {
            PausePane.setVisible(true);
            GameTimer.pauseTimer(true);
            was_already_blocked = blocker.isVisible();
            blocker.setVisible(true);
            // UNPAUSE
        } else if (PausePane.isVisible()) {
            PausePane.setVisible(false);
            GameTimer.pauseTimer(false);
            blocker.setVisible(was_already_blocked);
        }
    }

    // M and Escape Key
    public void MenuExit(Boolean iMenuOrExit) {
        if (!ExitVBox.isVisible()) {
            ExitVBox.setVisible(true);
        } else {
            ExitVBox.setVisible(false);
        }
        MenuOrExit = iMenuOrExit;
    }

    // Yes Button
    public void yesFunction(ActionEvent event) throws IOException {
        Sounds.clickSound();
        GameTimer.pauseTimer(false);
        GameTimer.closeTimer();

        // Exit
        if(MenuOrExit) {
            Platform.exit();
        }

        // Menu
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(scene);
            stage.show();
        }
    }

    // No Button for Menu
    public void noFunction() {
        Sounds.clickSound();
        ExitVBox.setVisible(false);
    }

    // Key Controls
    public void getKeyControls(Scene scene) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {

                    // Pause
                    case P -> Pause();

                    // Rules
                    case R -> {
                        try {
                            Rules();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Audio
                    case A -> Audio();

                    // Menu
                    case M -> MenuExit(false);

                    // Quit
                    case ESCAPE -> MenuExit(true);

                    /* Take
                    if(takeButton.isVisible()) {
                        case ENTER ->
                    }*/
                    /* Leave
                    if(leaveButton.isVisible()) {
                        case BACK_SPACE ->
                    }*/
                }

            }
        });
    }

    public void setChipField(int amount) {
        ArrayList<Label> player_point_labels = new ArrayList<>(Arrays.asList(Player1_P, Player2_P, Player3_P, Player4_P));
        GameEngine gameengine = new GameEngine(amount, TimerText, takeButton, leaveButton, player_point_labels, blocker);
        Main.start_game(gameengine);

        //Group root2 = new Group(gameengine.get_gameboard().get_gameboard_chips_group());
        Group root2 = new Group(gameengine.get_gameboard().get_gameboard_chips_group());
        chipsStackPane.getChildren().add(root2);
        chipsStackPane.setAlignment(Pos.CENTER);

        ArrayList<Player> players = gameengine.get_players();
        Group player1_chips_group = new Group(players.get(0).get_player_chips_group());
        player1_chips.getChildren().addAll(player1_chips_group);
        player1_chips.setAlignment(Pos.CENTER_LEFT);
        Group player2_chips_group = new Group(players.get(1).get_player_chips_group());
        player2_chips.getChildren().addAll(player2_chips_group);
        player2_chips.setAlignment(Pos.CENTER_RIGHT);
        if (amount >= 3) {
            Group player3_chips_group = new Group(players.get(2).get_player_chips_group());
            player3_chips.getChildren().addAll(player3_chips_group);
            player3_chips.setAlignment(Pos.CENTER_LEFT);
        }
        if (amount >= 4) {
            Group player4_chips_group = new Group(players.get(3).get_player_chips_group());
            player4_chips.getChildren().addAll(player4_chips_group);
            player4_chips.setAlignment(Pos.CENTER_RIGHT);
        }
    }

    public void Mute(){
        if(!toggleMute) {
            Sounds.mute();
            toggleMute = true;
            MuteButton.setText("Muted");
        }
        else if(toggleMute){
            Sounds.play();
            toggleMute = false;
            MuteButton.setText("Mute");
        }
    }

}