package me.blitzerino.home.administration;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.awt.image.BufferedImage;

import java.util.concurrent.ExecutionException;

/**
 * Created by Blitz on 5/31/2016.
 */
public class Snatch implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
            if (message.getContent().startsWith(Settings.getCommandStart() + "snatch")) {
                message.delete();
                if (!Mute.muted.contains(message.getAuthor().getId())) {
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        if (message.getMentions().size() == 1) {
                            message.reply("Debug");
                            copyAvatar(message.getMentions().get(0));
                            message.reply(message.getMentions().get(0).getMentionTag() + "'s profile picture is now my profile picture.");
                        }
                    } else {
                        message.reply(Settings.getAdminMsg());
                    }
                }
            }
        }

    public void copyAvatar(User user) {
        try {
            BufferedImage avatar = user.getAvatar().get();
            DiscordAPI api = Javacord.getApi();
            Exception ex = api.updateAvatar(avatar).get();
            if (ex != null) {
                ex.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
