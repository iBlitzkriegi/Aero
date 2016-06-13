package me.blitzerino.home.misccommands;

import de.btobastian.javacord.entities.message.MessageBuilder;
import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import de.btobastian.javacord.DiscordAPI;

import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static me.blitzerino.home.administration.Mute.muted;

/**
 * Created by Blitz on 5/25/2016.
 */
public class Suggest implements MessageCreateListener {
    private static ArrayList<String> rawr = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message)  {
        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if (args[0].equalsIgnoreCase("suggest")) {
                message.delete();
                if (!muted.contains(message.getAuthor().getId())) {
                    if (args.length == 1) {
                        MessageBuilder builder = new MessageBuilder();
                        builder.append("Here is a list of all the current suggestions " + message.getAuthor().getMentionTag() + "!").appendNewLine();
                        builder.append("```").appendNewLine();
                        for(String b : Main.suggestions){
                            builder.append(b).appendNewLine();
                        }
                        builder.append("```");
                        message.reply(builder.build());
                    } else if (args.length > 1) {
                        if (rawr.contains(message.getAuthor().getId())) {
                            message.reply("You get one suggestion per restart! You have already made your suggestion :)");
                        } else {
                            String text = message.getAuthor().getName() + ":" + message.getContent();
                            String finish = text.replaceFirst(Settings.getCommandStart(), "");
                            rawr.add(message.getAuthor().getId());
                            File file = new File("suggestions.txt");
                            FileWriter fw = null;
                            try {
                                fw = new FileWriter(file, true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            BufferedWriter bw = new BufferedWriter(fw);
                            try {
                                bw.write(finish.replaceFirst("suggest", ""));
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
                            String another = finish.replaceFirst(message.getAuthor().getName(), "");
                            String lastly = another.replaceFirst("suggest", "");
                            message.reply(Settings.getMsgStart() + "You have successfully suggested: `" + lastly.replaceFirst(":", "") + "` " + message.getAuthor().getMentionTag() + "!");
                        }
                    }
                }
            }
        }
    }
}
