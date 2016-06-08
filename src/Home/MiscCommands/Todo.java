package Home.MiscCommands;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.io.*;
import java.util.ArrayList;

import static Home.Administration.Mute.muted;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Todo implements MessageCreateListener {
    private static ArrayList<String> todo = new ArrayList<>();
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart())){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("todo")) {
                if (!muted.contains(message.getAuthor().getId())) {
                    if (args.length == 1) {
                        String line = null;
                        String fileName = "todo.txt";
                        try {
                            FileReader fileReader = new FileReader(fileName);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            while ((line = bufferedReader.readLine()) != null) {
                                todo.add(line);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        MessageBuilder builder = new MessageBuilder();
                        builder.append("```").appendNewLine().append("-= Current Todo List=-").appendNewLine();
                        for (String s : Todo.todo) {
                            if (s != null) {
                                builder.append(s).appendNewLine();
                            }
                        }
                        builder.append("```");
                        message.reply(builder.build());
                        todo.clear();
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