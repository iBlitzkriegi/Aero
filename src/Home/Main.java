package Home;

import Home.Administration.Raw;
import Home.Administration.Shutdown;
import Home.Administration.Uptime;
import Home.Memes.*;
import Home.MiscCommands.Help;
import Home.MiscCommands.Suggest;
import Home.MiscCommands.Todo;
import Home.Searching.Google;
import Home.Searching.SKU;
import Home.Searching.Youtube;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Main {
    public static ArrayList<String> commands = new ArrayList<>();
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> todo = new ArrayList<>();
    public static Channel adminLogChannel;
    public static long startupTime;
    private static String token;
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("You must include a token!");
            System.exit(-1);
        }
        token = args[0];
        final DiscordAPI api = Javacord.getApi(token, true);
        File file = new File("suggestions.txt");
        if (file != null) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        api.connectBlocking();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        api.registerListener(new MessageCreateListener() {
            @Override
            public void onMessageCreate(DiscordAPI discordAPI, Message message) {
                if (message.getContent().startsWith(Settings.getCommandStart())) {
                    adminLogChannel.sendMessage(message.getAuthor().getName() + "(" + message.getChannelReceiver().getServer().getName() + ")" + "[" + message.getChannelReceiver().getName() + "]" + ">" + message.getContent());
                }
                if (message.getContent().startsWith(Settings.getCommandStart() + "snatch")) {
                    message.delete();
                    if (message.getMentions().size() == 1) {
                        copyAvatar(message.getMentions().get(0));
                        message.reply(message.getMentions().get(0).getMentionTag() + "'s profile picture is now my profile picture.");
                    }
                } else if (message.isPrivateMessage() && (message.getAuthor() != discordAPI.getYourself())) {
                    List<String> replies = new ArrayList<>();
                    replies.add("You are dumb kid");
                    replies.add("I know you are but what am I!");
                    replies.add("I know you are but what am I!");
                    replies.add("Could you restate the question please?");
                    replies.add("Hmmm, Have we met before?");
                    replies.add("That's what your mom said last night");
                    replies.add("Oh yeah. Well you're a poopy pants");
                    replies.add("Kys, I mean.... Clean your shoes..");
                    replies.add("R000000D");
                    replies.add("I don't understand");

                    Random random = new Random();
                    String reply = replies.get(random.nextInt(replies.size()));
                    message.reply(reply);
                }
            }

            public void copyAvatar(User user) {
                try {
                    BufferedImage avatar = user.getAvatar().get();
                    Exception ex = api.updateAvatar(avatar).get();
                    if (ex != null) {
                        ex.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
            Date date = new Date();
            startupTime = date.getTime();
            SetClasses();
            setCommands();
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

            // Todo stuff \\
            todo.add((todo.size() + 1) + ". " + "Add Google searching");
            todo.add((todo.size() + 1) + ". " + "Add botinfo command");
            todo.add((todo.size() + 1) + ". " + "Add CopyCat commands and edit bot info");
            todo.add((todo.size() + 1) + ". " + "Add Restart Command");
            todo.add((todo.size() + 1) + ". " + "Add Memes GaLore");
            todo.add((todo.size() + 1) + "." + " Add leave server command");
            todo.add((todo.size() + 1) + ". " + "Add get info command");
            todo.add((todo.size() + 1) + ". " + "Add role command");
            todo.add((todo.size() + 1) + ". " + "Add flip command");
            todo.add((todo.size() + 1) + ". " + "Add clearchat command");
        }
    public static void delay(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void SetClasses(){
        classes.add("help");
        classes.add("main");
        classes.add("settings");
        classes.add("shutdown");
        classes.add("bye");
        classes.add("memecatch");
        classes.add("todo");
        classes.add("yt");
        classes.add("Ohwhale");
        classes.add("uptime");
        classes.add("skuf");
        classes.add("Triggered");
        classes.add("banter");
        classes.add("facepalm");
        classes.add("salty");
        classes.add("Questionmark");
        classes.add("juststop");
        classes.add("suggest");
        classes.add("google");
    }
    public static void setCommands(){
        commands.add("~ <Memes> ~");
        commands.add("<bye> - Cya!");
        commands.add("<ohwhale> - Oh..WHALE!");
        commands.add("<triggered> - Papa's triggered!");
        commands.add("<banter> - I'm lovin this banter..");
        commands.add("<facepalm> - Facepalm..");
        commands.add("<salty> - Y so salty doh.");
        commands.add("<juststop> - Just..Stop");
        commands.add("<???> - ???");
        commands.add("~ < Misc-Commands > ~");
        commands.add("< Snatch > - Have DankG snatch someones profile picture!");
        commands.add("< help > - Help menu, you obviously know that now..");
        commands.add("< uptime > - See how long I've been awake..");
        commands.add("< todo > - See what I've got on my todo list..");
        commands.add("< yt [%user$] <Thing to search> >- Search Youtube for something, can include a user.");
        commands.add("< skuf [%user%] <Thing to search> > - Search the SkUnity forums, can include a user. ");
        commands.add("< skud [%user%] <Thing to search> > - Search the SkUnity docs, can include a user. ");
        commands.add("< google [$user%] <Thing to search> > - Search Google for something, can include a user.");
        commands.add("< suggest <thing to add> > - Suggest something to be added to my code.");
        commands.add("~ <Administration> ~");
        commands.add("<shutdown> - Shut the bot down. duh.");
    }

}
