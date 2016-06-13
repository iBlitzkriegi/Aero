package me.blitzerino.home.misccommands;

import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import static me.blitzerino.home.administration.Mute.muted;
import static me.blitzerino.home.Settings.getCommandStart;
/**
 * Created by Blitz on 6/2/2016.
 */
public class Getinfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(getCommandStart(), "");
            if (args[0].equalsIgnoreCase("getinfo")) {
                if (!muted.contains(message.getAuthor().getId())) {
                    if (args.length == 2) {
                        if (message.getMentions().size() == 1) {
                            String start = Settings.getMsgStart();
                            User u = message.getMentions().get(0);
                            MessageBuilder builder = new MessageBuilder();
                            builder.append("This is all the information I could find on " + u.getMentionTag() + ", " + message.getAuthor().getMentionTag() + "!").appendNewLine();
                            builder.append("```xl").appendNewLine();

                            if (u.getName() == null) {
                                builder.append("Name: NULL").appendNewLine();
                            } else {
                                builder.append(start + "Name: [" + u.getName() + "]").appendNewLine();
                            }
                            builder.append(start + "Status: " + "[" + u.getStatus().toString() + "]").appendNewLine();
                            builder.append(start + "ID: [" + u.getId() + "]").appendNewLine();
                            if (u.getAvatarUrl() == null) {
                                builder.append("ProfilePicture: NULL");

                            } else {
                                builder.append(start + "ProfilePicture: " + u.getAvatarUrl()).appendNewLine();
                            }
                            if (u.getGame() == null) {
                                builder.append(start + "Playing: NULL").appendNewLine();
                            } else {
                                builder.append(start + "Playing: [" + u.getGame() + "]").appendNewLine();
                            }
                            if (u.getDiscriminator() != null) {
                                builder.append(start + "FriendTag: [" + u.getName() + "#" + u.getDiscriminator() + "]").appendNewLine();
                            } else {
                                builder.append(start + "FriendTag: null").appendNewLine();
                            }
                            builder.append(start + "IsBot: [" + u.isBot() + "]").appendNewLine();
                            builder.append(start + "Hashcode: " + u.hashCode()).appendNewLine();
                            builder.append("```");
                            message.reply(builder.build());
                        } else {
                            message.reply("You must specify a user!");
                        }
                    } else {
                        message.reply("You must specify a user!");
                    }
                }
            }
        }
    }
}
