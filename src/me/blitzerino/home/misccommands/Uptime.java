package me.blitzerino.home.misccommands;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.Date;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 5/16/2016.
 */
public class Uptime implements MessageCreateListener{
    private long getTimeRunning(){
        Date date = new Date();
        return (date.getTime() - Main.startupTime);
    }
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "uptime")){
            if(!muted.contains(message.getAuthor().getId())) {
                String[] args = message.getContent().split(" ");
                args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
                message.delete();
                long seconds = getTimeRunning() / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
                message.reply(Settings.getMsgStart() + "I've been awake for " + time + "... Kill me please " + message.getAuthor().getMentionTag());
            }
        }
    }
}
