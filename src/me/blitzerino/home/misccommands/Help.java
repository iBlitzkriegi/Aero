package me.blitzerino.home.misccommands;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.registers.setCommands;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Help implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart() + "help")) {
            if (!muted.contains(message.getAuthor().getId())) {
                String[] args = message.getContent().split(" ");
                args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
                if (args[0].equalsIgnoreCase("help")) {
                    message.delete();
                    MessageBuilder builder = new MessageBuilder();
                    MessageBuilder builder2 = new MessageBuilder();
                    message.reply(Settings.getMsgStart() + " I've PM'd you my command list, " + message.getAuthor().getMentionTag());
                    builder.append("```xml").appendNewLine();
                    builder2.append("```xml").appendNewLine();
                    Main.commands.clear();
                    Main.commands2.clear();
                    setCommands.setCommands();
                    for (String command : Main.commands) {
                        builder.append(Settings.getMsgStart() + command).appendNewLine();
                    }
                    for(String command2 : Main.commands2){
                        builder2.append(Settings.getMsgStart() + command2).appendNewLine();
                    }
                    builder.append("```");
                    builder2.append("```");
                    message.getAuthor().sendMessage(builder.build());
                    Main.delay();
                    message.getAuthor().sendMessage(builder2.build());
                }
            }
        }
    }
}