package me.blitzerino.home;

import de.btobastian.javacord.ImplDiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.listener.server.ServerJoinListener;
import me.blitzerino.home.administration.*;
import me.blitzerino.home.editbot.Reset;
import me.blitzerino.home.editbot.Setgame;
import me.blitzerino.home.editbot.Setname;
import me.blitzerino.home.editbot.Setpfp;
import me.blitzerino.home.listeners.*;
import me.blitzerino.home.memes.AddImages;
import me.blitzerino.home.memes.Share;
import me.blitzerino.home.registers.*;
import me.blitzerino.home.searching.Google;
import me.blitzerino.home.searching.SKU;
import me.blitzerino.home.searching.Youtube;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import me.blitzerino.home.administration.Shutdown;
import me.blitzerino.home.memes.Memecatch;
import me.blitzerino.home.misccommands.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Main {
    public static ArrayList<String> commands = new ArrayList<>();
    public static ArrayList<String> commands2 = new ArrayList<>();
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> admins = new ArrayList<>();
    public static ArrayList<String> suggestions = new ArrayList<>();
    public static ArrayList<String> todo = new ArrayList<>();
    public static ArrayList<String> format = new ArrayList<>();
    private static boolean shutdownNatural = false;
    public static boolean isShutdownNatural(){
        return shutdownNatural;
    }
    public static void setShutdownNatural(boolean shutdownNatural){
        Main.shutdownNatural = shutdownNatural;
    }
    public static long startupTime;
    private static String token;
    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("You must include a token after the code and startup.sh name!");
            delay();
            setShutdownNatural(true);
            delay();
            System.exit(-1);
        }
        token = args[0];
        final DiscordAPI api = Javacord.getApi(token, true);
        api.setIdle(true);
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                try{
                    if(!isShutdownNatural()){
                        api.setGame("Restarting!");
                        api.setIdle(true);
                        ((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().sendClose(1000);
                        ((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().disconnect();
                        Runtime.getRuntime().exec("./startaero.sh");

                    }else {
                        api.setIdle(true);
                        System.out.println("DANK GASAI GOING DOWN!");
                        ((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().sendClose(1000);
                        ((ImplDiscordAPI) api).getSocketAdapter().getWebSocket().disconnect();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        setCommands.setCommands();
        setSuggestions.setSuggestions();
        setAdmins.setAdmins();
        setTodo.setTodo();
        Main.delay();
        api.setGame("Registering classes");
        setClasses.SetClasses();
        setCommands.setCommands();
        api.registerListener(new LeaveServerListener());
        api.registerListener(new JoinServerListener());
        api.registerListener(new Setpfp());
        api.registerListener(new Clearmychat());
        api.registerListener(new Clearchat());
        api.registerListener(new Share());
        api.registerListener(new AddImages());
        api.registerListener(new MutedListener());
        api.registerListener(new Setname());
        api.registerListener(new Mute());
        api.registerListener(new Joinserver());
        api.registerListener(new Setgame());
        api.registerListener(new AddAdmin());
        api.registerListener(new Kick());
        api.registerListener(new Getinfo());
        api.registerListener(new LeaveServer());
        api.registerListener(new Version());
        api.registerListener(new Serverinfo());
        api.registerListener(new Shutdown());
        api.registerListener(new Help());
        api.registerListener(new Youtube());
        Memecatch memecatch = new Memecatch();
        memecatch.cacheImages();
        api.registerListener(new Memecatch());
        api.registerListener(new Todo());
        api.registerListener(new Uptime());
        api.registerListener(new SKU());
        api.registerListener(new Suggest());
        api.registerListener(new Google());
        api.registerListener(new Gatt());
        api.registerListener(new Botinfo());
        api.registerListener(new Thosepeople());
        api.registerListener(new Reset());
        api.registerListener(new RepliesListener());
        api.registerListener(new Restart());
        api.registerListener(new CommandsListener());
        api.registerListener(new Roll());
        Main.delay();
        api.setGame("Caching images.");
        api.connectBlocking();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.delay();
            api.setGame("Printing messages");
            Date date = new Date();
            startupTime = date.getTime();
            System.out.println("Successfully logged in!");
            delay();
            System.out.println("Setting game to " + Settings.getGame());
            delay();
            System.out.println("Done! Setting name to " + Settings.getName());
            delay();
            if (!api.getYourself().getName().equals(Settings.getName())) {
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
            Main.delay();
            api.setGame("Finishing up");
            Main.delay();
            Main.delay();
            Main.delay();
            if(api.getYourself().getAvatarUrl().toString() != "https://discordapp.com/api/users/140293967328706560/avatars/dc4cd474ba86d1106ad8a3dae6e1c8e9.jpg") {
                api.updateAvatar(ImageIO.read(new File("pfp.jpg")));
            }
            Main.delay();
            api.setGame(Settings.getGame());
            api.setIdle(false);
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
