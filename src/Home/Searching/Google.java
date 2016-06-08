package Home.Searching;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 5/25/2016.
 */
public class Google implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("google")){
                message.delete();
                if(!muted.contains(message.getAuthor().getId())) {
                    if (message.getMentions().size() == 0) {
                        String question = "";
                        args[0] = "";
                        for (String s : args) {
                            if (s != "") {
                                question = question + s + "+";
                            }
                        }
                        String rawr = question.substring(0, question.length() - 1) + "";
                        String url = "https://www.google.com/search?q=" + rawr;
                        MessageBuilder builder = new MessageBuilder();
                        builder.append(message.getAuthor().getMentionTag() + " Here you go you lazy son beyotch.").appendNewLine().append(url);
                        message.reply(builder.build());
                    } else if (message.getMentions().size() == 1) {
                        String question = "";
                        args[0] = "";
                        args[1] = "";
                        User u = message.getMentions().get(0);
                        for (String s : args) {
                            if (s != "") {
                                question = question + s + "+";
                            }
                        }
                        String rawr = question.substring(0, question.length() - 1) + "";
                        String url = "https://www.google.com/search?q=" + rawr;
                        MessageBuilder builder = new MessageBuilder();
                        builder.append(u.getMentionTag() + "! " + message.getAuthor().getMentionTag() + " thinks yo dumb ass needs this...").appendNewLine().append(url);
                        message.reply(builder.build());

                    }
                }
            }
        }
    }
}
