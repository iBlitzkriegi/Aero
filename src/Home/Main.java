package Home;

import Home.Administration.*;
import Home.Memes.*;
import Home.MiscCommands.*;
import Home.Registers.setAdmins;
import Home.Registers.setClasses;
import Home.Registers.setCommands;
import Home.Searching.Google;
import Home.Searching.SKU;
import Home.Searching.Youtube;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;

import java.util.*;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Main {
    public static ArrayList<String> commands = new ArrayList<>();
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> todo = new ArrayList<>();
    public static ArrayList<String> admins = new ArrayList<>();
    public static long startupTime;
    private static String token;
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("You must include a token after the code! Like this: java -jar DankGasai.jar EnterTokenHere");
            System.exit(-1);
        }
        token = args[0];
        final DiscordAPI api = Javacord.getApi(token, true);
        setAdmins.setAdmins();
        api.registerListener(new Version());
        api.registerListener(new Shutdown());
        api.registerListener(new Bye());
        api.registerListener(new Help());
        api.registerListener(new Youtube());
        Memecatch memecatch = new Memecatch();
        memecatch.cacheImages();
        api.registerListener(new Memecatch());
        api.registerListener(new Todo());
        api.registerListener(new OhWhale());
        api.registerListener(new Uptime());
        api.registerListener(new Raw());
        api.registerListener(new SKU());
        api.registerListener(new Triggered());
        api.registerListener(new Banter());
        api.registerListener(new Facepalm());
        api.registerListener(new Salty());
        api.registerListener(new Juststop());
        api.registerListener(new Questionmark());
        api.registerListener(new Suggest());
        api.registerListener(new Google());
        api.registerListener(new Gatt());
        api.registerListener(new Botinfo());
        api.registerListener(new Thosepeople());
        api.registerListener(new Reset());
        api.registerListener(new Snatch());
        api.registerListener(new RepliesListener());
        api.connectBlocking();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Date date = new Date();
            startupTime = date.getTime();
            setClasses.SetClasses();
            setCommands.setCommands();
            System.out.println("Successfully logged in!");
            delay();
            System.out.println("Setting game to " + Settings.getGame());
            api.setGame(Settings.getGame());
            delay();
            System.out.println("Done! Setting name to " + Settings.getName());
            delay();
            if (api.getYourself().getName().equals(Settings.getName())) {
            } else {
                api.updateUsername(Settings.getName());
            }
            System.out.println("Name set! Moving on.. Parsing all classes now.");
            delay();
            System.out.println("=- Parsing all found classes to Main class =-");
            delay();
            for (String b : classes) {
                System.out.println(b + ".java has been successfully parsed.");
                delay();
                }
            System.out.println("Done! Found " + classes.size() + " classes!");
            delay();
            System.out.println("Counting up commands... ");
            delay();
            System.out.println("Done! Found " + commands.size() + " commands");
            delay();
            System.out.println("Bot fully loaded! Type " + Settings.getCommandStart() + "help ingame to get started!");

        }
    public static void delay(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
