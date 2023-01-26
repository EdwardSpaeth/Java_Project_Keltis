package com.KeltisT.Controllers;

import com.KeltisT.Window.SizeOfMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class gameController {

    @FXML
    public ImageView Player3_V, Player3_NonV, Player4_V, Player4_NonV;

    @FXML
    public Text Player3_T, Player4_T;

    @FXML
    public Label Player3_L, Player3_P, Player4_L, Player4_P, PauseLabel;
    @FXML
    public VBox MenuVBox, ExitVBox;
    private SizeOfMonitor sizeOfMonitor = new SizeOfMonitor();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double HEIGHT = sizeOfMonitor.getSizeOfMonitor()[0];
    private double WIDTH = sizeOfMonitor.getSizeOfMonitor()[1];

    // Settings for Player 3 and Player 4
    public void setPlayer_3_4(boolean third, boolean fourth) {

        Player3_V.setVisible(third);
        Player3_NonV.setVisible(!third);
        if(!third){
            Player3_T.setFill(Color.LIGHTGRAY);
            Player3_T.setStroke(Color.LIGHTGRAY);
            Player3_P.setTextFill(Color.LIGHTGRAY);
            Player3_L.setStyle("-fx-border-color: lightgray;");
        }

        Player4_V.setVisible(fourth);
        Player4_NonV.setVisible(!fourth);
        if(!fourth){
            Player4_T.setFill(Color.LIGHTGRAY);
            Player4_T.setStroke(Color.LIGHTGRAY);
            Player4_P.setTextFill(Color.LIGHTGRAY);
            Player4_L.setStyle("-fx-border-color: lightgray;");
        }

    }

    // Key Events

    // P Key
    public void Pause(){
        if (!PauseLabel.isVisible()) {
            PauseLabel.setVisible(true);
        }
        else if (PauseLabel.isVisible()){
            PauseLabel.setVisible(false);
        }
    }

    // R Key
    public void Rules(){
        System.out.println("Rules");
    }

    // A Key
    public void Audio(){
        System.out.println("Audio");
    }

    // M Key
    public void Menu() {
        if (MenuVBox.isVisible()) {
            MenuVBox.setVisible(false);
            MenuVBox.setDisable(true);
        }
        else {
            MenuVBox.setVisible(true);
            MenuVBox.setDisable(false);
        }
    }

    // Escape Key
    public void Exit(){
        if (ExitVBox.isVisible()) {
            ExitVBox.setVisible(false);
            MenuVBox.setDisable(true);
        }
        else {
            ExitVBox.setVisible(true);
            ExitVBox.setDisable(false);
        }
    }


    // Key Controls
    public void getKeyControls(Scene scene) {


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int i = 0;

                switch (event.getCode()){

                    // Pause
                    case P:
                        Pause();
                        break;

                    // Rules
                    case R:
                        Rules();
                        break;

                    // Audio
                    case A:
                        Audio();
                        break;

                    // Menu
                    case M:
                        Menu();
                        break;

                    // Quit
                    case ESCAPE:
                        Exit();
                        break;
                }

            }
        });
    }

    // Yes Button for Menu
    public void yesFunction(ActionEvent event) throws IOException {

            root = FXMLLoader.load(getClass().getResource("/Fxml/start.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, WIDTH, HEIGHT);
            System.out.println(WIDTH + " and " + HEIGHT);
            stage.setScene(scene);
            stage.show();

    }

    // No Button for Menu
    public void noFunction(ActionEvent event){
        MenuVBox.setVisible(false);
        MenuVBox.setDisable(false);
    }

    // Yes Button for Exit
    public void yesFunction_E(ActionEvent event) throws IOException {
        Platform.exit();
    }

    // No Button for Menu
    public void noFunction_E(ActionEvent event){
        ExitVBox.setVisible(false);
        ExitVBox.setDisable(false);
    }



}
