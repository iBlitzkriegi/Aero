package me.blitzerino.home.registers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static me.blitzerino.home.Main.admins;

/**
 * Created by Blitz on 6/12/2016.
 */
public class setAdmins {
    public static void setAdmins(){
        String line = null;
        String fileName = "admins.txt";
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                admins.add(line);
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
