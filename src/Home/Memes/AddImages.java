package Home.Memes;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 6/7/2016.
 */
public class AddImages implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().startsWith(Settings.getCommandStart())) {
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("addimg")) {
                if(!muted.contains(message.getAuthor().getId())) {
                    if(Main.admins.contains(message.getAuthor().getId())) {
                        if (args.length == 4) {
                            if (args[1].contains("http")) {
                                if (args[2].contains("png") || args[2].contains("jpg")) {
                                    message.getChannelReceiver().sendMessage("Attempting to add new image..." + message.getAuthor().getMentionTag() + "!");
                                    File f = new File("memes.txt");
                                    BufferedWriter br = null;
                                    try {
                                        br = new BufferedWriter(new FileWriter(f, true));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (br != null) {
                                        try {
                                            br.write("\n" + args[1] + "!" + args[2] + "!" + args[3]);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            br.close();
                                            Memecatch.cacheImage(args[1], args[2], args[3]);
                                            message.getChannelReceiver().sendMessage("Image Successfully Added " + message.getAuthor().getMentionTag() + "!");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
