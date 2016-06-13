package me.blitzerino.home.administration;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 6/3/2016.
 */
public class Joinserver implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("joinserver")) {
                if (!muted.contains(message.getAuthor().getId())) {
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(Settings.getMsgStart() + "Have a moderator use my invite link " + message.getAuthor().getMentionTag() + "!").appendNewLine().append("https://discordapp.com/oauth2/authorize?client_id=174348223908020235&scope=bot&permissions=66186303");
                    message.reply(builder.build());
                }
            }

        }
    }
}
