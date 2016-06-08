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

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 5/27/2016.
 */
public class Botinfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "botinfo")){
            if(!muted.contains(message.getAuthor().getId())) {
                message.delete();
                MessageBuilder builder = new MessageBuilder();
                builder.append("```xl").appendNewLine();
                builder.append(Settings.getMsgStart() + "Name: " + Settings.getName()).appendNewLine();
                builder.append(Settings.getMsgStart() + "Version: " + Settings.getVersion()).appendNewLine();
                builder.append(Settings.getMsgStart() + "Github: `https://www.github.com/iblitzkriegi/DankGasai`").appendNewLine();
                builder.append(Settings.getMsgStart() + "Author: iBlitzkriegi").appendNewLine();
                builder.append(Settings.getMsgStart() + "MyProfilePicture: " + discordAPI.getYourself().getAvatarUrl()).appendNewLine();
                builder.append(Settings.getMsgStart() + "MyServer: https://discord.gg/0wbasQaCb34qBF7T").appendNewLine();
                builder.append(Settings.getMsgStart() + "FavoriteBand: Scoprions").appendNewLine();
                builder.append(Settings.getMsgStart() + "DevelopmentStream: `https://livecoding.tv/blitzkry/`").appendNewLine();
                builder.append(Settings.getMsgStart() + "ServersJoined: " + discordAPI.getServers().size()).appendNewLine();
                builder.append(Settings.getMsgStart() + "MembersMetCount: " + discordAPI.getUsers().size()).appendNewLine();
                long seconds = (new Date().getTime() - Main.startupTime) / 1000; // Gotten from @RealGatt's Gasai bot
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                String uptime = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds"; // Also from GasaiBot :L
                builder.append(Settings.getMsgStart() + "CurrentUptime: " + uptime).appendNewLine();
                builder.append(Settings.getMsgStart() + "DankGasai Was Created By Blitzkrieg And Inspired By RealGatt Using  Javacord (Created By Bastian).").appendNewLine();
                builder.append("```");
                message.reply(Settings.getMsgStart() + "I have PM'd you my info " + message.getAuthor().getMentionTag() + "!");
                message.getAuthor().sendMessage(builder.build());
            }

        }
    }
}
