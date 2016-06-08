package Home.Memes;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 6/7/2016.
 */
public class Share implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("shareimg")){
                if(!muted.contains(message.getAuthor().getId())) {
                    if(args.length == 2){
                        message.getChannelReceiver().sendFile(Memecatch.imageCache.get(args[1]));
                    }else{
                        MessageBuilder builder = new MessageBuilder();
                        builder.append("Here you go " + message.getAuthor().getMentionTag() + "! Just type `" + Settings.getCommandStart() + "share ImgName` to get one of these images!").appendNewLine().append("```xl").appendNewLine().append("{*}---=CurrentImages=---{*}").appendNewLine();
                        for(String b :Memecatch.images){
                            if(b!=""){
                                builder.append(Settings.getMsgStart() + b).appendNewLine();
                            }
                        }
                        builder.append("```");
                        message.reply(builder.build());
                    }
                }
            }
        }
    }
}
