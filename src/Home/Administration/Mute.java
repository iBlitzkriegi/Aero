package Home.Administration;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;

/**
 * Created by Blitz on 6/4/2016.
 */
public class Mute implements MessageCreateListener {
    public static ArrayList<String> muted = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("mute")){
                if(!muted.contains(message.getAuthor().getId())) {
                    if (Main.admins.contains(message.getAuthor().getId())) {
                        if (args.length == 2) {
                            if (message.getMentions().size() == 1) {
                                User u = message.getMentions().get(0);
                                if (muted.contains(u.getId())) {
                                    message.reply("You have succesfully unmuted " + u.getMentionTag() + ", " +message.getAuthor().getMentionTag());
                                    muted.remove(u.getId());
                                }else{
                                    muted.add(u.getId());
                                    message.reply("I have successfuly muted " + u.getMentionTag() + " for you " + message.getAuthor().getMentionTag() + "!");
                                }
                            } else {
                                message.reply("You must specify a user!");
                            }
                        } else {
                            message.reply("Incorrect usage, Syntax is: `mute %user%` " + message.getAuthor().getMentionTag() + "!");
                        }
                    } else {
                        message.reply(Settings.getAdminMsg());
                    }
                }else{
                    message.delete();
                }
            }
        }
    }

}
