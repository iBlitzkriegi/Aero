package me.blitzerino.home.listeners;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.listener.server.ServerMemberAddListener;

/**
 * Created by Blitz on 6/13/2016.
 */
public class JoinServerListener implements ServerMemberAddListener {
    @Override
    public void onServerMemberAdd(DiscordAPI api, User u, Server s) {
        for(Channel r : s.getChannels()){
            if(r.getName().equalsIgnoreCase("logchannel")){
                r.sendMessage(u.getMentionTag() + " has joined the server! Welcome " + u.getName() + "!");
            }
        }
    }
}
