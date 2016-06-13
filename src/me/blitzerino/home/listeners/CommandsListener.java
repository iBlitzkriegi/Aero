package me.blitzerino.home.listeners;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 6/2/2016.
 */
public class CommandsListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            for( Server s : discordAPI.getServers()){
                if(s.getId().equals("169226587995111425")){
                    for(Channel r : s.getChannels()){
                        if(r.getId().equals("169305090979528705")){
                            r.sendMessage(message.getAuthor().getName() + "(" + message.getChannelReceiver().getServer().getName() + ")" + "[" + message.getChannelReceiver().getName() + "]" + ">" + message.getContent());
                        }
                    }
                }
            }
        }else if(message.getContent().startsWith(Settings.getCommandStart() + "shutdown")){
            for( Server s : discordAPI.getServers()){
                if(s.getId().equals("169226587995111425")){
                    for(Channel r : s.getChannels()){
                        if(r.getId().equals("169305090979528705")){
                            MessageBuilder builder = new MessageBuilder();
                            builder.append("```").appendNewLine().append("Dank Gasai shutting down :(").appendNewLine().append("```");
                            r.sendMessage(builder.build());
                        }
                    }
                }
            }
        }
    }
}
