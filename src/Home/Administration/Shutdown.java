package Home.Administration;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;
public class Shutdown implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "shutdown")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("shutdown")){
                if(message.getAuthor().getId().equals("98208218022428672")){
                    Main.delay();
                    message.reply(Settings.getMsgStart() + "If....If you say so master..");
                    Main.delay();
                    MessageBuilder builder = new MessageBuilder();
                    builder.append("```").appendNewLine();
                    builder.append(Settings.getMsgStart() + "Dank Gasai shutting down :(").appendNewLine();
                    builder.append("```");
                    Main.adminLogChannel.sendMessage(builder.build());
                    Main.delay();
                    System.exit(-1);


                }
            }
        }
    }
}
