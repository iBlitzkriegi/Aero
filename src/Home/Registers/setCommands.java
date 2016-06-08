package Home.Registers;

import static Home.Main.commands;

/**
 * Created by Blitz on 5/31/2016.
 */
public class setCommands {
    public static void setCommands(){
        commands.add("~ < Misc-Commands > ~");
        commands.add("< joinserver > - Get my invite link.");
        commands.add("< getinfo [%user%] > - Get some info about a user.");
        commands.add("< serverinfo > - Get some information about a server.");
        commands.add("< version > - Get my current version.");
        commands.add("< botinfo > - Get some fresh af info bout' me.");
        commands.add("< thosepeople <user> > - Send the SKU format to a specific user!");
        commands.add("< Gatt > - Who tf?");
        commands.add("< help > - Help menu, you obviously know that now..");
        commands.add("< roll %integer% > - Roll a random number between 0 and %integer%");
        commands.add("< uptime > - See how long I've been awake..");
        commands.add("< todo > - See what I've got on my todo list..");
        commands.add("< yt [%user$] <Thing to search> >- Search Youtube for something, can include a user.");
        commands.add("< skuf [%user%] <Thing to search> > - Search the SkUnity forums, can include a user. ");
        commands.add("< skud [%user%] <Thing to search> > - Search the SkUnity docs, can include a user. ");
        commands.add("< google [$user%] <Thing to search> > - Search Google for something, can include a user.");
        commands.add("< suggest <thing to add> > - Suggest something to be added to my code.");
        commands.add("~ <Administration> ~");
        commands.add("<addimg %DirectImgUrl% %Extension% %Name%> - Add a meme to me!");
        commands.add("<share [%MemeName%]> - Share a meme! If you dont include one I'll list them for you :)");
        commands.add("<addadmin> - Add a bot administrator.");
        commands.add("<setgame> - Set the game I'm currently playing.");
        commands.add("<kick %user%> - Kick a user from the server.");
        commands.add("<leaveserver> - Make me leave la servah.");
        commands.add("<shutdown> - Shut the bot down. duh.");
        commands.add("< Snatch $user$ > - Have DankG snatch someones profile picture!");
        commands.add("<restart> - Restarts me...");
        commands.add("<snatch $user$> - Make a users profile picture, my profile picture!");
        commands.add("<reset> - Reset my PFP and name.");
    }
}
