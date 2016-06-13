package me.blitzerino.home.searching;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.administration.Mute;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Youtube implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart() + "yt")) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("yt"))
                message.delete();
            if (!Mute.muted.contains(message.getAuthor().getId())) {
                if (message.getMentions().size() == 1) {
                    String question = "";
                    User u = message.getMentions().get(0);
                    args[0] = "";
                    args[1] = "";
                    for (String s : args) {
                        if (s != "") {
                            question = question + s + "%20";
                        }
                    }
                    String rawr = question.substring(0, question.length() - 3) + "";
                    String url = "https://www.youtube.com/results?search_query=" + rawr;
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(Settings.getMsgStart()).appendUser(u).append("! ").appendUser(message.getAuthor()).append(" thinks your dumbass needs this!").appendNewLine().append(url);
                    message.reply(builder.build());
                } else {
                    String question = "";
                    args[0] = "";
                    for (String s : args) {
                        if (s != "") {
                            question = question + s + "%20";
                        }
                    }
                    String rawr = question.substring(0, question.length() - 3) + "";
                    String url = "https://www.youtube.com/results?search_query=" + rawr;
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(Settings.getMsgStart() + message.getAuthor().getMentionTag() + " here you go you lazy ass.. ").appendNewLine().append(url);
                    message.reply(builder.build());


                }

            }
        }
    }
}
