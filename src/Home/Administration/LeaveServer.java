package Home.Administration;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 6/2/2016.
 */
public class LeaveServer implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "leaveserver")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(Main.admins.contains(message.getAuthor().getId())) {
                if (!muted.contains(message.getAuthor().getId())) {
                    Server s = message.getChannelReceiver().getServer();
                    Main.delay();
                    message.reply("I'll just be on my way then, " + message.getAuthor().getMentionTag());
                    Main.delay();
                    s.leave();
                }
            }else{
                message.reply(Settings.getAdminMsg());
            }
        }
    }
}
