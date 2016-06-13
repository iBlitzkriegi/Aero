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
public class Setname implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("setname")){
                if(Main.admins.contains(message.getAuthor().getId())) {
                    MessageBuilder builder = new MessageBuilder();
                    for (int i = 0; i < args.length; i++) {
                        builder.append(args[i]);
                    }
                    discordAPI.updateUsername(builder.build().replaceFirst("setname", ""));
                    Main.delay();
                    if(discordAPI.getYourself().getName() == builder.build().replaceFirst("setname", "")){
                        message.reply(":white_check_mark:Success! My name is now `" + discordAPI.getYourself().getName() + "` " + message.getAuthor().getMentionTag() + "!");
                    }else{
                        message.reply(":x:I did my best " + message.getAuthor().getMentionTag() + "...But I think I went over the rate limit..Failed ._.");
                    }
                }else {
                    message.reply(Settings.getAdminMsg());
                }
            }
        }
    }
}
