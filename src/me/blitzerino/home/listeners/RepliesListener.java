package me.blitzerino.home.listeners;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Blitz on 5/31/2016.
 */
public class RepliesListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.isPrivateMessage() && (message.getAuthor() != discordAPI.getYourself())) {
            List<String> replies = new ArrayList<>();
            replies.add("You are dumb kid");
            replies.add("I know you are but what am I!");
            replies.add("Could you restate the question please?");
            replies.add("Hmmm, Have we met before?");
            replies.add("That's what your mom said last night");
            replies.add("Oh yeah. Well you're a poopy pants");
            replies.add("Kys, I mean.... Clean your shoes..");
            replies.add("R000000D");
            replies.add("I don't understand");
            replies.add("Hmm i like the sound of that... i wonder where you got it from :p");

            Random random = new Random();
            String reply = replies.get(random.nextInt(replies.size()));
            message.reply(reply);
        }
    }
}
