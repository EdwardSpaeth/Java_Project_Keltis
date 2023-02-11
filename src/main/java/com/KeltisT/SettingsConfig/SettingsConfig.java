package com.KeltisT.SettingsConfig;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to save and load setting from a settings config file.
 */

public class SettingsConfig {

    /**
     * Reading file and load the audio settings.
     */

    public static ArrayList<String> getAudioConfig() {
        File file = new File("settingsConfig.txt");
        Scanner sc;
        ArrayList<String> audioSettings = null;
        try {
            sc = new Scanner(file);
            audioSettings = new ArrayList<>();
            while (sc.hasNextLine()) {
                audioSettings.add(sc.nextLine());
            }
            // Close Scanner
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner has failed to find & read a the file");
        }

        return audioSettings;
    }

    /**
     * Save audio settings in settings config file.
     */

    public static void setAudioConfig(ArrayList<String> lines) {
        BufferedWriter writer = null;
        String result = "";
        for (String line : lines) {
            result = result.concat(line + '\n');
        }
        result = result.substring(0, result.length() - 1);
        try {
            writer = new BufferedWriter(new FileWriter("settingsConfig.txt"));
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
