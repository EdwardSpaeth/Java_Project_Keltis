package com.keltis.game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game {

    @FXML
    Label testLabel;

    public String[] newNames = {"", "", "", ""};

    public void newNames(String name1, String name2, String name3, String name4){
        newNames[0] = name1;
        newNames[1] = name2;
        newNames[2] = name3;
        newNames[3] = name4;
        testLabel.setText(name1 + " " + name2 + " " + name3 + " " + name4);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("playerNames.txt"));
            writer.write(name1);
            writer.write("\n" + name2);
            writer.write("\n" + name3);
            writer.write("\n" + name4);

            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
