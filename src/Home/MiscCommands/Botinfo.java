package Home.MiscCommands;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Blitz on 5/27/2016.
 */
public class Botinfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "botinfo")){
            message.delete();
            MessageBuilder builder = new MessageBuilder();
            builder.append("```").appendNewLine();
            builder.append("Name: " + Settings.getName()).appendNewLine();
            builder.append("Version: " + Settings.getVersion()).appendNewLine();
            builder.append("Github: `https://www.github.com/iblitzkriegi/DankGasai`").appendNewLine();
            builder.append("Author: iBlitzkriegi").appendNewLine();
            builder.append("My Profile Picture: " + discordAPI.getYourself().getAvatarUrl()).appendNewLine();
            builder.append("MyServer: https://discord.gg/0wbasQaCb34qBF7T").appendNewLine();
            builder.append("Favorite song: Scoprions - No one like you").appendNewLine();
            builder.append("Development Stream locations?: `https://livecoding.tv/blitzkry/`").appendNewLine();
            builder.append("Servers joined: " + discordAPI.getServers().size()).appendNewLine();
            builder.append("Members met: " + discordAPI.getUsers().size()).appendNewLine();
            long seconds = (new Date().getTime() - Main.startupTime) / 1000; // Gotten from @RealGatt's Gasai bot
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            String uptime = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds"; // Also from GasaiBot :L
            builder.append("Current uptime: " + uptime).appendNewLine();
            builder.append("DankGasai was created by iBlitzkriegi and inspired by RealGatt using  Javacord (Created by Bastian).").appendNewLine();
            builder.append("```");
            message.reply("I have PM'd you my info " + message.getAuthor().getMentionTag() + "!");
            message.getAuthor().sendMessage(builder.build());
        }
    }
}
