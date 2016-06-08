package Home.MiscCommands;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static Home.Administration.Mute.muted;

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
