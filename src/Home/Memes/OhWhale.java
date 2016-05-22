package Home.Memes;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 5/16/2016.
 */
public class OhWhale implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "ohwhale")){
            message.delete();
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            Channel c = message.getChannelReceiver();
            c.sendFile(Memecatch.imageCache.get("ohwhale"), "Oh whale " + message.getAuthor().getMentionTag() + "!");
        }
    }
}
