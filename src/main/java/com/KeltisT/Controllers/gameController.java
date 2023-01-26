package com.KeltisT.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class gameController {

    @FXML
    public ImageView Player3_V, Player3_NonV, Player4_V, Player4_NonV;

    @FXML
    public Text Player3_T, Player4_T;

    @FXML
    public Label Player3_L, Player3_P, Player4_L, Player4_P;

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

}
