package com.example.keltisproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class PlayerConfig {
    public static ArrayList<String> get_player_config() {
        File file = new File("config.txt");
        Scanner sc;
        ArrayList<String> player_names = null;
        try {
            sc = new Scanner(file);
            player_names = new ArrayList<>();
            while (sc.hasNextLine()) {
                player_names.add(sc.nextLine());
            }
            // Close Scanner
            sc.close();
        } catch (FileNotFoundException e) {
            // Initialize a logger to display exceptions in terminal.
            Logger logger = Logger.getLogger(PlayerConfig.class.getName());
            logger.warning("Scanner has failed to find & read a the file");
            //logger.error("File <> was not found:", e);
        }
        return player_names;
    }

    public static void set_player_config(ArrayList<String> names)  {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("config.txt"));
            // Write into file and close BufferedWriter
            String result = "";
            for (String name : names){
                //result += name + '\n';
                result = result.concat(name + '\n');
            }
            result = result.substring(0, result.length()-1);
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            // Initialize a logger to display exceptions in terminal.
            Logger logger = Logger.getLogger(PlayerConfig.class.getName());
            logger.warning("Writer has failed to find the file to be written into!");
            try {
                // Only close writer if it is not equal to null
                assert writer != null;
                writer.close();
            } catch (IOException runtime_e) {
                logger.warning("Writer has failed to close!");
            }
            throw new RuntimeException(e);
        }
    }
}
