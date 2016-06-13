package me.blitzerino.home.administration;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 6/4/2016.
 */
public class Kick implements MessageCreateListener {
    public static boolean rawr = false;
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {

        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("kick")) {
                if (!Mute.muted.contains(message.getAuthor().getId())) {
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        if (args.length == 2) {
                            if (message.getMentions().size() == 1) {
                                User u = message.getMentions().get(0);
                                Server s = message.getChannelReceiver().getServer();
                                rawr = true;
                                s.kickUser(u.getId());
                                for(Channel r : s.getChannels()){
                                    if(r.getName().equalsIgnoreCase("logchannel")){
                                        r.sendMessage(Settings.getMsgStart() + message.getAuthor().getMentionTag() + " has kicked " + u.getMentionTag() + " from the server!");
                                    }
                                }


                                message.reply(Settings.getMsgStart() + "I have done my best to kick " + u.getMentionTag() + " for you " + message.getAuthor().getMentionTag() + "!");
                            } else {
                                message.reply(Settings.getMsgStart() + "You must include a user to kick silly!");
                            }
                        } else {
                            message.reply(Settings.getMsgStart() + "You must include a user to kick silly!");
                        }
                    }
                }
            }
        }
    }
}
