package me.blitzerino.home.administration;

import com.google.common.collect.Lists;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by Blitz on 6/12/2016.
 */
public class Clearchat implements MessageCreateListener {
    private static ArrayList<String> dundelete = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("clearchat")) {
                if (Main.admins.contains(message.getAuthor().getId())) {
                    if (args.length == 2) {
                        int foundAmt = 0;
                        int amt = Integer.parseInt(args[1]);
                        message.reply(Settings.getMsgStart() + "Attempting to delete " + amt + " messages from the chat " + message.getAuthor().getMentionTag() + "!");
                        dundelete.add(Settings.getMsgStart() + "Attempting to delete " + amt + " messages from the chat " + message.getAuthor().getMentionTag() + "!");
                        try {
                            for (Message m : Lists.reverse(message.getChannelReceiver().getMessageHistory(1000).get().getMessagesSorted())) {
                                message.getChannelReceiver().type();
                                if (foundAmt < amt) {
                                    if(!dundelete.contains(m)) {
                                        m.delete();
                                        foundAmt++;
                                        Main.delay();
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
                        message.reply(Settings.getMsgStart() + "Successfully deleted " + amt + " messages from the chat " + message.getAuthor().getMentionTag() + "! If this information is incorrect I may not have enough permissions!");

                    }

                }
            }
        }
    }
}
