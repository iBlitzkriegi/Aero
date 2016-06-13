package me.blitzerino.home.misccommands;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.blitzerino.home.administration.Mute;
import me.blitzerino.home.registers.setTodo;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Todo implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("todo")) {
                if (!Mute.muted.contains(message.getAuthor().getId())) {
                    if (args.length == 1) {
                        MessageBuilder builder = new MessageBuilder();
                        builder.append("Here is my todo list " + message.getAuthor().getMentionTag() + "!").appendNewLine();
                        builder.append("```").appendNewLine().append("-=Current Todo List=-").appendNewLine();
                        for (String s : Main.todo) {
                            if (s != null) {
                                builder.append(s).appendNewLine();
                            }
                        }
                        builder.append("```");
                        message.reply(builder.build());
                    } else if (args.length > 1) {
                        if (Main.admins.contains(message.getAuthor().getId())) {
                            String rawr = message.getContent().replaceFirst("todo", "");
                            File file = new File("todo.txt");
                            FileWriter fw = null;
                            try {
                                fw = new FileWriter(file, true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            BufferedWriter bw = new BufferedWriter(fw);
                            try {
                                bw.write(rawr);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                bw.newLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                bw.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                bw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            message.reply("Congratulations " + message.getAuthor().getMentionTag() + ", You have successfully added `" + rawr.replaceFirst("- ", "") + "` to my todo list!");
                        } else {
                            message.reply(Settings.getAdminMsg());
                        }
                    }
                }
            }
        }
    }
}