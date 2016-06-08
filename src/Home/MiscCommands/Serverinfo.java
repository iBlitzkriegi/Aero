package Home.MiscCommands;

import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.UserStatus;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 6/2/2016.
 */

public class Serverinfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart() + "serverinfo")) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("serverinfo")) {
                if (!muted.contains(message.getAuthor().getId())) {
                    MessageBuilder builder = new MessageBuilder();
                    Server s = message.getChannelReceiver().getServer();
                    int online = 0;
                    int idle = 0;
                    int offline = 0;
                    for (User user : s.getMembers()) {
                        if (user.getStatus() == UserStatus.ONLINE) {
                            online++;
                        } else if (user.getStatus() == UserStatus.IDLE) {
                            idle++;
                        } else if (user.getStatus() == UserStatus.OFFLINE) {
                            offline++;
                        }
                    }
                    boolean rawr;
                    if (s.getMemberCount() >= 49) {
                        rawr = true;
                    } else {
                        rawr = false;
                    }
                    builder.append(message.getAuthor().getMentionTag() + " here is all the information I could gather about this server!").appendNewLine();
                    builder.append("```xl").appendNewLine();
                    builder.append(Settings.getMsgStart() + "Id: " + s.getId()).appendNewLine();
                    builder.append(Settings.getMsgStart() + "Name: " + s.getName()).appendNewLine();
                    builder.append(Settings.getMsgStart() + "TextChannels: " + s.getChannels().size()).appendNewLine();
                    builder.append(Settings.getMsgStart() + "VoiceChannels: " + s.getVoiceChannels().size()).appendNewLine();
                    builder.append(Settings.getMsgStart() + "MemeberCount: " + s.getMemberCount()).appendNewLine();
                    builder.append(Settings.getMsgStart() + "-Online: " + online).appendNewLine();
                    builder.append(Settings.getMsgStart() + "-Idle: " + idle).appendNewLine();
                    builder.append(Settings.getMsgStart() + "-Offline: " + offline).appendNewLine();
                    builder.append(Settings.getMsgStart() + "IsLarge: " + rawr).appendNewLine();
                    builder.append(Settings.getMsgStart() + "Region: " + s.getRegion()).appendNewLine();
                    builder.append(Settings.getMsgStart() + "{*}------=Roles " + s.getRoles().size() + "=------{*}").appendNewLine();
                    for (Role r : s.getRoles()) {
                        builder.append(Settings.getMsgStart() + "Role: " + r.getName()).appendNewLine();
                        builder.append(Settings.getMsgStart() + " -Hoisted: " + r.getHoist()).appendNewLine();
                        builder.append(Settings.getMsgStart() + " -Id: " + r.getId()).appendNewLine();
                        builder.append(Settings.getMsgStart() + " -Position: " + (Integer.valueOf(r.getPosition()) + 1)).appendNewLine();
                    }
                    builder.append("```");
                    message.reply(builder.build());

                }


            }
        }
    }
}
