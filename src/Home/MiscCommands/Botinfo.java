package Home.MiscCommands;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;

/**
 * Created by Blitz on 5/27/2016.
 */
public class Botinfo implements MessageCreateListener {
    private static ArrayList<String> botinfo = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            MessageBuilder builder = new MessageBuilder();
            builder.append("```").appendNewLine();
            setBotinfo();
            if(args[0].equalsIgnoreCase("botinfo")){
                message.delete();
                for(String s : botinfo){
                    if(s != ""){
                        builder.append(s).appendNewLine();
                    }
                }
                builder.append("```");
                message.reply("I have PM'd you my info " + message.getAuthor().getMentionTag() + "!");
                message.getAuthor().sendMessage(builder.build());
            }
        }
    }
    private static void setBotinfo(){
        botinfo.add("Github: `https://www.github.com/iblitzkriegi/DankGasai`");
        botinfo.add("Name: " + Settings.getName());
        botinfo.add("Version: " + Settings.getVersion());
        botinfo.add("Written in: DankGasai was written in a Java Discord library called Javacord");
        botinfo.add("Author: Blitzerino");
        botinfo.add("MyServer: https://discord.gg/0wbasQaCb34qBF7T");
        botinfo.add("Favorite song: Uhm. None?");
        botinfo.add("Development Stream locations?: `https://livecoding.tv/blitzkry/`");

    }
}
