package Home.Administration;

import Home.Main;
import Home.Settings;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
/**
 * Created by Blitz on 6/1/2016.
 */
public class Restart implements MessageCreateListener{
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(message.getContent().startsWith(Settings.getCommandStart() + "restart")){
            String[] args = message.getContent().split(" ");
            args[0] = args[0].replaceFirst(Settings.getCommandStart(), "");
            if(args[0].equalsIgnoreCase("restart")) {
                message.delete();
                if(Main.admins.contains(message.getAuthor().getId())){
                    message.reply("Rebooting in progress.");
                    Main.delay();
                    Main.setShutdownNatural(false);
                    Main.delay();
                    System.exit(0);
                }else{
                    message.reply(Settings.getAdminMsg());
                }

            }
        }
    }
}
