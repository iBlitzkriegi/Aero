package me.blitzerino.home.administration;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;

/**
 * Created by Blitz on 6/4/2016.
 */
public class Mute implements MessageCreateListener {
    public static ArrayList<String> muted = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("mute")){
                if(!muted.contains(message.getAuthor().getId())) {
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        if (args.length == 2) {
                            if (message.getMentions().size() == 1) {
                                User u = message.getMentions().get(0);
                                Server s = message.getChannelReceiver().getServer();
                                if (muted.contains(u.getId())) {
                                    for(Channel r : s.getChannels()){
                                        if(r.getName().equalsIgnoreCase("logchannel")){
                                            r.sendMessage(Settings.getMsgStart() + message.getAuthor().getMentionTag() + " has un-muted " + u.getMentionTag() + "!");
                                        }
                                    }
                                    message.reply(Settings.getMsgStart() + "You have successfully un-muted " + u.getMentionTag() + ", " +message.getAuthor().getMentionTag() + "!");
                                    muted.remove(u.getId());
                                }else{
                                    muted.add(u.getId());
                                    for(Channel r : s.getChannels()){
                                        if(r.getName().equalsIgnoreCase("logchannel")){
                                            r.sendMessage(Settings.getMsgStart() + message.getAuthor().getMentionTag() + " has muted " + u.getMentionTag() + "!");
                                        }
                                    }
                                    message.reply(Settings.getMsgStart() + "I have successfuly muted " + u.getMentionTag() + " for you " + message.getAuthor().getMentionTag() + "!");
                                }
                            } else {
                                message.reply(Settings.getMsgStart() + "You must specify a user!");
                            }
                        } else {
                            message.reply(Settings.getMsgStart() + "Incorrect usage, Syntax is: `mute %user%` " + message.getAuthor().getMentionTag() + "!");
                        }
                    } else {
                        message.reply(Settings.getMsgStart() + Settings.getAdminMsg());
                    }
                }else{
                    message.delete();
                }
            }
        }
    }

}
