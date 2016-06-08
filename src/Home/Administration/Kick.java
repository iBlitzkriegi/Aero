package Home.Administration;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 6/4/2016.
 */
public class Kick implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("kick")) {
                if (!muted.contains(message.getAuthor().getId())) {
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        if (args.length == 2) {
                            if (message.getMentions().size() == 1) {
                                User u = message.getMentions().get(0);
                                String name = u.getName();
                                Main.delay();
                                message.getChannelReceiver().getServer().kickUser(u);
                                Main.delay();
                                Main.delay();
                                if (message.getChannelReceiver().getServer().getMemberById(u.getId()) != null) {
                                    message.reply(message.getAuthor().getMentionTag() + ".. I did my best to try and kick " + u.getMentionTag() + " but either he has too much permission or I don't have enough ;-;");
                                } else {
                                    message.reply(message.getAuthor().getMentionTag() + " I have successfully kicked " + name + " from the server!");
                                }
                            } else {
                                message.reply("You must include a user to kick silly!");
                            }
                        } else {
                            message.reply("You must include a user to kick silly!");
                        }
                    }
                }
            }
        }
    }
}
