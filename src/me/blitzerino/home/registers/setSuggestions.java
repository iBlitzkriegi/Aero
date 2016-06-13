package me.blitzerino.home.registers;

import me.blitzerino.home.Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Blitz on 6/12/2016.
 */
public class setSuggestions {
    public static void setSuggestions(){
        String line = null;
        String fileName = "suggestions.txt";
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                Main.suggestions.add(line);
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

}
