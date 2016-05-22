package Home.Searching;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 5/21/2016.
 */
public class SKU implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "skuf")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart() + "skuf", "");
            message.delete();
            if(message.getMentions().size() == 0){
                String question = "";
                args[0] = "";
                for(String s : args){
                    if(s != ""){
                        question = question + s + "%20";
                    }
                }
                String rawr = question.substring(0, question.length() - 3) + "";
                String url = "https://forums.skunity.com/search?q=+" + rawr;
                MessageBuilder builder = new MessageBuilder();
                builder.append(Settings.getMsgStart() + message.getAuthor().getMentionTag() + " here you go you lazy son' bitch..").appendNewLine().append(url);
                message.reply(builder.build());

            }else if(message.getMentions().size() == 1){
                String question = "";
                User u = message.getMentions().get(0);
                args[0] = "";
                args[1] = "";
                for(String s : args){
                    if(s != ""){
                        question = question + s + "%20";
                    }
                }
                String rawr = question.substring(0, question.length() - 3) + "";
                String url = "https://forums.skunity.com/search?q=+" + rawr;
                MessageBuilder builder = new MessageBuilder();
                builder.append(Settings.getMsgStart() + "Hey " + u.getMentionTag() + "! " + message.getAuthor().getMentionTag() + " thinks yo bitch ass needs this!").appendNewLine().append(url);
                message.reply(builder.build());

            }
        }else if(message.getContent().startsWith(Settings.getCommandStart() + "skud")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart() + "skud", "");
            message.delete();
            if(message.getMentions().size() == 0){
                String question = "";
                args[0] = "";
                for(String s : args){
                    if(s != ""){
                        question = question + s + "+";
                    }
                }
                String rawr = question.substring(0, question.length() - 1) + "";
                String url = "https://skunity.com/search?search=" + rawr;
                MessageBuilder builder = new MessageBuilder();
                builder.append(Settings.getMsgStart() + message.getAuthor().getMentionTag() + " here you go you lazy son' bitch..").appendNewLine().append(url);
                message.reply(builder.build());

            }else if(message.getMentions().size() == 1){
                String question = "";
                User u = message.getMentions().get(0);
                args[0] = "";
                args[1] = "";
                for(String s : args){
                    if(s != ""){
                        question = question + s + "+";
                    }
                }
                String rawr = question.substring(0, question.length() - 1) + "";
                String url = "https://skunity.com/search?search=" + rawr;
                MessageBuilder builder = new MessageBuilder();
                builder.append(Settings.getMsgStart() + "Hey " + u.getMentionTag() + "! " + message.getAuthor().getMentionTag() + " thinks yo bitch ass needs this!").appendNewLine().append(url);
                message.reply(builder.build());

            }
        }
    }
}
