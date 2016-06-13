package me.blitzerino.home.editbot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import me.blitzerino.home.memes.Memecatch;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 6/12/2016.
 */
public class Setpfp implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("setpfp")) {
                if(!muted.contains(message.getAuthor().getId())){
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        if(args.length == 2) {
                            try {
                                discordAPI.updateAvatar(ImageIO.read(new File(args[1])));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            message.reply("Incorrect usage " + message.getAuthor().getMentionTag() + "! Syntax:`-setpfp %BufferedImage%`!");
                        }
                    }else{
                        message.reply(Settings.getAdminMsg());
                    }
                }
            }
        }
    }
}
