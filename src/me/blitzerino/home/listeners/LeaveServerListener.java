package me.blitzerino.home.listeners;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.listener.server.ServerLeaveListener;
import de.btobastian.javacord.listener.server.ServerMemberRemoveListener;
import me.blitzerino.home.administration.Kick;

/**
 * Created by Blitz on 6/13/2016.
 */
public class LeaveServerListener implements ServerMemberRemoveListener {
    @Override
    public void onServerMemberRemove(DiscordAPI api, User u, Server s) {
        for(Channel r : s.getChannels()){
            if(r.getName().equalsIgnoreCase("logchannel")){
                if(Kick.rawr != true) {
                    r.sendMessage(u.getMentionTag() + " has left the server! ._. byebye.");
                }
            }
        }
    }
}
