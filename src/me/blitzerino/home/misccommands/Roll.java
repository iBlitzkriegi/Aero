package me.blitzerino.home.misccommands;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.Random;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 6/2/2016.
 */
public class Roll implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "roll")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("roll")){
                if(!muted.contains(message.getAuthor().getId())) {
                    if (args.length == 2) {
                        Random rand = new Random();
                        message.getChannelReceiver().sendMessage(message.getAuthor().getMentionTag() + " has rolled the random number " + (rand.nextInt(Integer.valueOf(args[1]))) + " from 0 to " + args[1] + ".");
                    } else if (args.length == 1) {
                        message.reply("You must include at least one integer!");
                    } else if (args.length > 2) {
                        message.reply("You may only include one number!");
                    }
                }
            }
        }
    }
}
