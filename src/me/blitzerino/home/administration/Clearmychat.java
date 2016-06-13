package me.blitzerino.home.administration;

import com.google.common.collect.Lists;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Blitz on 6/11/2016.
 */
public class Clearmychat implements MessageCreateListener {
    private static ArrayList<String> dundelete = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("clearmychat")) {
                if (Main.admins.contains(message.getAuthor().getId())) {
                    if (args.length == 2) {
                        int foundAmt = 0;
                        int amt = Integer.parseInt(args[1]);
                        message.reply(Settings.getMsgStart() + "Attempting to clear " + amt + " of my messages from the chat " + message.getAuthor().getMentionTag() + "!");
                        dundelete.add(Settings.getMsgStart() + "Attempting to clear " + amt + " of my messages from the chat " + message.getAuthor().getMentionTag() + "!");
                        try {
                            for (Message m : Lists.reverse(message.getChannelReceiver().getMessageHistory(1000).get().getMessagesSorted())) {
                                message.getChannelReceiver().type();
                                if (foundAmt < amt) {
                                    if (m.getAuthor() == discordAPI.getYourself()) {
                                        if(!dundelete.contains(m)) {
                                            m.delete();
                                            foundAmt++;
                                            Main.delay();
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        dundelete.clear();
                        message.reply(Settings.getMsgStart() + "Successfully deleted " + amt + " of my messages " + message.getAuthor().getMentionTag() + "!");


                    }else{
                        message.reply(Settings.getMsgStart() + "Incorrect usage " + message.getAuthor().getMentionTag() + "! Syntax is: `-clearmychat %integer%`!");
                    }
                }else {
                    message.reply(Settings.getAdminMsg());
                }
            }
        }
    }
}
