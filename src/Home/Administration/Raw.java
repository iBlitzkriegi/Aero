package Home.Administration;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Blitz on 5/16/2016.
 */
public class Raw implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "raw")) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args.length == 2){
                BufferedImage image = null;
                try {
                    URL url = new URL(args[1]);
                    message.reply("tesst");
                    image = ImageIO.read(url);
                    message.reply("tesst");
                    File file = new File(args[1]);
                    message.reply("tesst");
                    ImageIO.write(image, "jpg", file);
                    message.reply("tesst");
                    message.replyFile(file);
                    message.reply("tesst");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Unable to retrieve Image!");
                    message.reply("Couldnt retrieve image try again!");
                }

            }
        }
    }
}
