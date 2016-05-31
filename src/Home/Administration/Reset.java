package Home.Administration;

import Home.Main;
import Home.Memes.Memecatch;
import Home.Settings;
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
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "reset")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("reset")) {
                message.delete();
                if (Main.admins.contains(message.getAuthor().getId())) {
                    try {
                        discordAPI.updateAvatar(ImageIO.read(new File("pp.png")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(discordAPI.getYourself().getName() != Settings.getName()) {
                        discordAPI.updateUsername(Settings.getName());
                    }

                }
            }else{
                message.reply("You are not one of my admins! Sorry " + message.getAuthor().getMentionTag() + "!");
                message.reply(message.getAuthor().getId());
            }
        }
    }
}
