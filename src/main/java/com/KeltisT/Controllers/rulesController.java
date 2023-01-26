package com.KeltisT.Controllers;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//still figuring out how to fix the rules text
public class rulesController {

    public rulesController() throws IOException {
        Path filename  = Path.of("src/main/resources/Rules.txt");
        String rulestext = Files.readString(filename);
        Text text = new Text(rulestext);
        text.setFill(Color.RED);
        text.setStroke(Color.YELLOW);
        text.setStrokeWidth(0.5);
        text.setFont(Font.font("Papyrus", FontWeight.NORMAL, FontPosture.REGULAR, 12));

    }
}
