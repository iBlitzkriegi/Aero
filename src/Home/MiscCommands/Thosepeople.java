package Home.MiscCommands;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;

/**
 * Created by Blitz on 5/27/2016.
 */
public class Thosepeople implements MessageCreateListener {
    private static ArrayList<String> format = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("thosepeople")){
                message.delete();
                if(message.getMentions().size() == 1){
                    if(args.length == 2){
                        laformat();
                        User u = message.getMentions().get(0);
                        MessageBuilder builder = new MessageBuilder();
                        builder.append("```").appendNewLine();
                        for(String s : format){
                            if(s != ""){
                                builder.append(s).appendNewLine().appendNewLine();
                            }
                        }
                        builder.append("```");
                        builder.append(message.getAuthor().getName() + " thought you needed this format!");
                        u.sendMessage(builder.build());

                    }else{
                        message.reply("IDIOT! You gotta include a user!");
                    }
                }else{
                    message.reply("Must included a user");
                }
            }
        }
    }
    public static void laformat(){
        format.add("Skript version: ");
        format.add("Skript author: ");
        format.add("Full code: ");
        format.add("Minecraft version: ");
        format.add("Have you tried searching the forums?: ");
        format.add("Have you tried searching the docs?: ");
        format.add("What methods have you tried?: ");

    }
}
