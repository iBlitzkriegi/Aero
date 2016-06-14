package me.blitzerino.home.administration;

import de.btobastian.javacord.entities.message.MessageBuilder;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.io.*;

import static me.blitzerino.home.Main.admins;


/**
 * Created by Blitz on 6/4/2016.
 */
public class AddAdmin implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("addadmin")) {
                if (args.length == 2) {
                    if (message.getMentions().size() == 1) {
                        User u = message.getMentions().get(0);
                        if (admins.contains(message.getAuthor().getId())) {
                            if (!admins.contains(u.getId())) {
                                File file = new File("admins.txt");
                                FileWriter fw = null;
                                try {
                                    fw = new FileWriter(file, true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                BufferedWriter bw = new BufferedWriter(fw);
                                try {
                                    bw.write(message.getMentions().get(0).getId());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bw.newLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bw.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bw.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                message.reply(Settings.getMsgStart() + "Congratulations " + message.getMentions().get(0).getMentionTag() + " you have been granted Administrator by " + message.getAuthor().getMentionTag() + "!");
                            } else {
                                message.reply(Settings.getMsgStart() + "Woah there " + message.getAuthor().getMentionTag() + "! " + u.getMentionTag() + " is already a admin!");
                            }

                        } else {
                            message.reply(Settings.getMsgStart() + Settings.getAdminMsg());
                        }
                    } else {
                        message.reply(Settings.getMsgStart() + "You must specify a user " + message.getAuthor().getMentionTag() + "!");
                    }
                } else {
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(Settings.getMsgStart() + "Not sure " + message.getAuthor().getMentionTag() + "? Here is a list of all the current admin's ID's.").appendNewLine();
                    for(String j : Main.admins){
                        if(j!=""){
                            builder.append("<@" + j + ">").appendNewLine();
                        }
                    }
                    message.getAuthor().sendMessage(builder.build());
                    message.reply("I could tell you weren't sure " + message.getAuthor().getMentionTag() + "! I sent you my current admin list.");
                }
            }
        }
    }
}
