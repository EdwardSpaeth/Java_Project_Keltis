package com.keltis.edward;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerConfig {
    public static ArrayList<String> get_player_config(int amount_of_players) {
        File file = new File("PlayerNames.txt");
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
            System.out.println("Scanner has failed to find & read a the file");
        }
        int start_index = 5;
        switch (amount_of_players) {
            case 2 -> start_index = 0;
            case 3 -> start_index = 2;
            case 4 -> start_index = 5;
        }
        ArrayList<String> result = new ArrayList<>();
        for (int i = start_index; i < start_index + amount_of_players; i++){
            result.add(player_names.get(i));
        }
        return result;
    }

    public static void set_player_config(ArrayList<String> names)  {
        BufferedWriter writer = null;
        String result = "";
        for (int i = 2; i <= 4; i++) {
            if (names.size() != i) {
                for (String name : get_player_config(i)) {
                    result = result.concat(name + '\n');
                }
            }
            else {
                for (String name : names){
                    result = result.concat(name + '\n');
                }
            }
        }
        result = result.substring(0, result.length()-1);
        System.out.println(result);
        try {
            writer = new BufferedWriter(new FileWriter("PlayerNames.txt"));
            // Write into file and close BufferedWriter
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("Writer has failed to find the file to be written into!");
            try {
                // Only close writer if it is not equal to null
                assert writer != null;
                writer.close();
            } catch (IOException runtime_e) {
                System.out.println("Writer has failed to close!");
            }
            throw new RuntimeException(e);
        }
    }
}