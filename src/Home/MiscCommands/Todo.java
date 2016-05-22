package Home.MiscCommands;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Todo implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "todo")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("todo")){
                message.delete();
                MessageBuilder builder = new MessageBuilder();
                message.reply(Settings.getMsgStart() + "I have PM'd you everything I have on my suggestions list that will be added, " + message.getAuthor().getMentionTag() + "!");
                builder.append("```xml").appendNewLine().append("-= Current todo list -=").appendNewLine();
                for (String todo : Main.todo){
                    builder.append(Settings.getMsgStart() + "<" + todo + ">").appendNewLine();
                }
                builder.append("```");
                message.getAuthor().sendMessage(builder.build());
            }
        }
    }
}
