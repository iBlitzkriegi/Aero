package me.blitzerino.home.editbot;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
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
                        String game = message.getContent().replaceFirst("setgame", "").replaceFirst(Settings.getCommandStart(), "").trim();
                        discordAPI.setGame(game);
                        Main.delay();
                        message.reply(Settings.getMsgStart() + ":white_check_mark:Success " + message.getAuthor().getMentionTag() + "! My game has successfully been set to `" + game + "`!");
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
