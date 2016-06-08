package Home.EditBot;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 6/2/2016.
 */
public class Setgame implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("setgame")) {
                if(Main.admins.contains(message.getAuthor().getId())) {
                    if (args.length > 1) {
                        MessageBuilder builder = new MessageBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i] + " ");
                        }
                        discordAPI.setGame(builder.build().toString().replaceFirst("setgame", "").trim());
                        message.reply(message.getAuthor().getMentionTag() + " has successfully set my game to `" + builder.build().replaceFirst("setgame", "").trim() + "`!");
                    } else {
                        message.reply("You need to specify a game..");
                    }
                }else{
                    message.reply(Settings.getAdminMsg());
                }
            }
        }
    }
}
