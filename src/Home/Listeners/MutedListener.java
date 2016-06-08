package Home.Listeners;

import Home.Administration.Mute;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 6/6/2016.
 */
public class MutedListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(Mute.muted.contains(message.getAuthor().getId())){
            message.delete();
        }
    }
}
