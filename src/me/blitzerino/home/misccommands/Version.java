package me.blitzerino.home.misccommands;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.administration.Mute;

/**
 * Created by Blitz on 5/31/2016.
 */
public class Version implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart() + "version")) {
            if (!Mute.muted.contains(message.getAuthor().getId())) {
                message.reply("My current version is `" + Settings.getVersion() + "` " + message.getAuthor().getMentionTag());
            }
        }
    }
}
