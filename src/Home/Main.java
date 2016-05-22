package Home;

import Home.Administration.Raw;
import Home.Administration.Shutdown;
import Home.Administration.Uptime;
import Home.Memes.Bye;
import Home.Memes.Memecatch;
import Home.Memes.OhWhale;
import Home.MiscCommands.Help;
import Home.MiscCommands.Todo;
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

import javax.imageio.ImageIO;
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
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("You must include a token! Duh!");
            System.exit(-1);
        }
        token = args[0];
        final DiscordAPI api = Javacord.getApi(token, true);
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
        for (Server s : api.getServers()) {
            System.out.println("Server = " + s.getName());
            if (s.getName().equalsIgnoreCase("DankKasai LogChannel")) {
                for (Channel c : s.getChannels()) {
                    if (c.getName().equalsIgnoreCase("Logchannel")) {
                        adminLogChannel = c;
                        MessageBuilder builder = new MessageBuilder();
                        builder.append("```").appendNewLine();
                        builder.append(Settings.getMsgStart() + "Dank Gasai version " + Settings.getVersion() + " has been successfully enabled! Type " + Settings.getCommandStart() + "help to get started!").appendNewLine();
                        builder.append("```");
                        c.sendMessage(builder.build());
                    }
                }

            }

            Date date = new Date();
            startupTime = date.getTime();
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
            api.updateAvatar(ImageIO.read(new File("pp.png")));

            // Memes \\
            commands.add("~ <Memes> ~");
            commands.add("<bye> - Cya!");
            commands.add("<ohwhale> - Oh..WHALE!");
            commands.add("~ < Misc-Commands > ~");
            commands.add("< Snatch > - Have DankG snatch someones profile picture!");
            commands.add("< help > - Help menu, you obviously know that now..");
            commands.add("< uptime > - See how long I've been awake..");
            commands.add("< todo > - See what I've got on my todo list..");
            commands.add("< skuf [user] <Thing to search> > - Search the SkUnity forums, can include a user. ");
            commands.add("< skud [user] <Thing to search> > - Search the SkUnity docs, can include a user. ");
            commands.add("~ <Administration> ~");
            commands.add("<shutdown> - Shut the bot down. duh.");
            classes.add("Help");
            classes.add("Main");
            classes.add("Settings");
            classes.add("Shutdown");
            classes.add("bye");
            classes.add("memecatch");
            classes.add("todo");
            classes.add("yt");
            classes.add("Ohwhale");
            classes.add("uptime");
            classes.add("skuf");
            System.out.println("Successfully logged in!");
            delay();
            System.out.println("Setting game to " + Settings.getGame());
            api.setGame(Settings.getGame());
            delay();
            System.out.println("Done! Setting name to " + Settings.getName());
            delay();
            if (api.getYourself().getName().equals(Settings.getName())) {
            }else{
                api.updateUsername(Settings.getName());
            }
            System.out.println("Name set! Moving on.. Parsing all classes now.");
            delay();
            System.out.println("=- Parsing all found classes to Main class =-");
            delay();
            System.out.println("help.java successfully parsed.");
            delay();
            System.out.println("settings.java successfully parsed.");
            delay();
            System.out.println("shutdown.java successfully parsed.");
            delay();
            System.out.println("bye.java successfully parsed.");
            delay();
            System.out.println("ohwhale.java successfully parsed.");
            delay();
            System.out.println("uptime.java successfully parsed.");
            delay();
            System.out.println("memes.catch successfully parsed and images cached!");
            delay();
            System.out.println("main class successfully parsed.");
            delay();
            System.out.println("sku.java successfully parsed.");
            System.out.println("Done! Found " + classes.size() + " classes!");
            delay();
            System.out.println("Counting up commands... ");
            delay();
            System.out.println("Done! Found " + commands.size() + " commands");
            delay();
            System.out.println("Bot fully loaded! Tyoe " + Settings.getCommandStart() + "help ingame to get started!");

            // Todo stuff \\
            todo.add((todo.size() + 1) + ". " + "Add triggered meme");
            todo.add((todo.size() + 1) + ". " + "Remake mentions system to compare strings to member names");
            todo.add((todo.size() + 1) + ". " + "Add SKUF searching");
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
    }
    public static void delay(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
