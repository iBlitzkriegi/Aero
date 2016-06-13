package me.blitzerino.home.registers;

import me.blitzerino.home.Main;

/**
 * Created by Blitz on 5/31/2016.
 */
public class setCommands {
    public static void setCommands(){
        Main.commands.add("~ < Misc-Commands > ~");
        Main.commands.add("< joinserver > - Get my invite link.");
        Main.commands.add("< getinfo [%user%] > - Get some info about a user.");
        Main.commands.add("< serverinfo > - Get some information about a server.");
        Main.commands.add("< version > - Get my current version.");
        Main.commands.add("< botinfo > - Get some fresh af info bout' me.");
        Main.commands.add("< thosepeople <user> > - Send the SKU format to a specific user!");
        Main.commands.add("< Gatt > - Who tf?");
        Main.commands.add("< help > - Help menu, you obviously know that now..");
        Main.commands.add("< roll %integer% > - Roll a random number between 0 and %integer%");
        Main.commands.add("< uptime > - See how long I've been awake..");
        Main.commands.add("< todo > - See what I've got on my todo list..");
        Main.commands.add("< yt [%user$] <Thing to search> >- Search Youtube for something, can include a user.");
        Main.commands.add("< skuf [%user%] <Thing to search> > - Search the SkUnity forums, can include a user. ");
        Main.commands.add("< skud [%user%] <Thing to search> > - Search the SkUnity docs, can include a user. ");
        Main.commands.add("< google [$user%] <Thing to search> > - Search Google for something, can include a user.");
        Main.commands.add("< suggest <thing to add> > - Suggest something to be added to my code.");
        Main.commands.add("~ <Administration> ~");
        Main.commands.add("<setpfp %BufferedImage> - Set my PFP to a buffered image.");
        Main.commands.add("<clearmychat %integer> - Clear a certain number of my messages from chat!");
        Main.commands.add("<clearchat %integer> - Clear a certain number of messages from chat!");
        Main.commands.add("<addimg %DirectImgUrl% %Extension% %Name%> - Add a meme to me!");
        Main.commands.add("<share [%MemeName%]> - Share a meme! If you dont include one I'll list them for you :)");
        Main.commands.add("<addadmin> - Add a bot administrator.");
        Main.commands.add("<setgame> - Set the game I'm currently playing.");
        Main.commands.add("<kick %user%> - Kick a user from the server.");
        Main.commands.add("<leaveserver> - Make me leave la servah.");
        Main.commands.add("<shutdown> - Shut the bot down. duh.");
        Main.commands.add("< Snatch $user$ > - Have DankG snatch someones profile picture!");
        Main.commands.add("<restart> - Restarts me...");
        Main.commands.add("<snatch $user$> - Make a users profile picture, my profile picture!");
        Main.commands.add("<reset> - Reset my PFP and name.");
    }
}
