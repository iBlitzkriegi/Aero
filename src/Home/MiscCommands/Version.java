package Home.MiscCommands;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 5/31/2016.
 */
public class Version implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart() + "version")) {
            if (!muted.contains(message.getAuthor().getId())) {
                message.reply("My current version is `" + Settings.getVersion() + "` " + message.getAuthor().getMentionTag());
            }
        }
    }
}
