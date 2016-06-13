package me.blitzerino.home.editbot;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by Blitz on 5/30/2016.
 */
public class Reset implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI api, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "reset")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("reset")) {
                message.delete();
                if (Main.admins.contains(message.getAuthor().getId())) {
                    try {
                        api.updateAvatar(ImageIO.read(new File("pp.png")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(api.getYourself().getName() != Settings.getName()) {
                        api.updateUsername(Settings.getName());
                    }else if(api.getYourself().getGame() != Settings.getCommandStart()){
                        api.setGame(Settings.getCommandStart());
                    }

                }
            }else{
                message.reply("You are not one of my admins! Sorry " + message.getAuthor().getMentionTag() + "!");
            }
        }
    }
}
