package me.blitzerino.home.misccommands;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.Main;
import me.blitzerino.home.administration.Mute;

/**
 * Created by Blitz on 5/27/2016.
 */
public class Thosepeople implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("thosepeople")) {
                message.delete();
                if (!Mute.muted.contains(message.getAuthor().getId())) {
                    if (message.getMentions().size() == 1) {
                        if (args.length == 2) {
                            laFormat();
                            User u = message.getMentions().get(0);
                            MessageBuilder builder = new MessageBuilder();
                            builder.append("```").appendNewLine();
                            for (String s : Main.format) {
                                if (s != "") {
                                    builder.append(s).appendNewLine().appendNewLine();
                                }
                            }
                            builder.append("```");
                            builder.append(message.getAuthor().getName() + " thought you needed this format!");
                            u.sendMessage(builder.build());
                            message.reply("I have successfully sent that user the format " + message.getAuthor().getMentionTag() + "!");
                            Main.format.clear();

                        } else {
                            message.reply("IDIOT! You gotta include a user!");
                        }
                    } else {
                        message.reply("Must included a user");
                    }
                }
            }
        }
    }
    public static void laFormat(){
        Main.format.add("Skript version: ");
        Main.format.add("Skript author: ");
        Main.format.add("Full code: ");
        Main.format.add("Minecraft version: ");
        Main.format.add("Have you tried searching the forums?: ");
        Main.format.add("Have you tried searching the docs?: ");
        Main.format.add("What methods have you tried?: ");
    }

}
