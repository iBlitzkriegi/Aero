package me.blitzerino.home.misccommands;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 5/27/2016.
 */
public class Gatt implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "gatt")) {
            if (!muted.contains(message.getAuthor().getId())) {
                message.delete();
                message.reply("Um, who in the hell is that scrub " + message.getAuthor().getMentionTag() + "?");
            }
        }
    }
}
