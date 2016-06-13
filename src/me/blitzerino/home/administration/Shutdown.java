package me.blitzerino.home.administration;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static me.blitzerino.home.administration.Mute.muted;
import static me.blitzerino.home.Main.delay;
import static me.blitzerino.home.Main.*;
public class Shutdown implements MessageCreateListener {
    @Override
    public void onMessageCreate(final DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "shutdown")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("shutdown")) {
                if (!muted.contains(message.getAuthor().getId())) {
                    message.delete();
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        message.reply(Settings.getMsgStart() + "If....If you say so master.. " + message.getAuthor().getMentionTag() + ".. Shutting down.");
                        delay();
                        setShutdownNatural(true);
                        delay();
                        System.exit(0);
                    } else {
                        message.reply(Settings.getAdminMsg());
                    }
                }
            }
        }
    }
}
