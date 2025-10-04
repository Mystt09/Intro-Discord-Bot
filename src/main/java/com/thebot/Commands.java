
package com.thebot;

import UserIntro.UserIntro;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
    //@Override
	
	public String prefix = ".";
	
	 private Map<String, UserIntro> activeIntros = new HashMap<>();
	@Override // why does it have to be here? also i didnt know what it was for, but it was if it was underlined red for guild message
	public void onMessageReceived(MessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentDisplay().split(" ");
		String userId = event.getAuthor().getId();

		if (event.getAuthor().isBot()) return;

		if (args[0].equalsIgnoreCase(prefix + "hi")) { // the BotStartup in the beginning is referencing to the class
			//event.getMessage().reply("This bot is working!").queue(); // the event method is used for input from the user i believe, the queue is to make sure the message in the "" sends
			//event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("no go away").queue(); // use this one so that the bot won't reply to the message
		}
		if (args[0].equalsIgnoreCase(prefix + "yo")) { // didnt have to reference the class since i also created the prefix here as well 
			//event.getMessage().reply("This bot is working!").queue(); // the event method is used for input from the user i believe, the queue is to make sure the message in the "" sends
			//event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("no").queue(); // use this one so that the bot won't reply to the message
		}
		if (args[0].equalsIgnoreCase(prefix + "intro")) { // didnt have to reference the class since i also created the prefix here as well

			
			event.getMessage().reply("Would you like to make an intro? [Y/N]").queue(); // the event method is used for input from the user i believe, the queue is to make sure the message in the "" sends
			//event.getChannel().sendTyping().queue();

			activeIntros.put(userId, new UserIntro());

            return;

		}

			if (activeIntros.containsKey(userId)) {
            UserIntro handler = activeIntros.get(userId);
            handler.handleIntro(event);
            if (handler.isDone()) {
                activeIntros.remove(userId); // cleanup when done
            }
            return;
        }

if (args[0].equalsIgnoreCase(prefix + "myIntro")) {
    // Fetch intro from DB
    IntroDB.IntroDatabase db = new IntroDB.IntroDatabase();
    try {
        var rs = db.getIntro(userId);
        if (rs != null && rs.next()) {
            String name = rs.getString("name");
            String hobbies = rs.getString("hobbies");
            String intoRightNow = rs.getString("intoRightNow");
            String favFood = rs.getString("favFood");
            String funFact = rs.getString("funFact");
            String birthday = rs.getString("birthday");

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("User Intro for " + name);
            embed.addField("Hobbies", hobbies, false);
            embed.addField("Into Right Now", intoRightNow, false);
            embed.addField("Favorite Food", favFood, false);
            embed.addField("Fun Fact", funFact, false);
            embed.addField("Birthday", birthday, false);
            embed.setColor(0x42f5b3);
            embed.setFooter("Requested by " + event.getAuthor().getName(),
                            event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            embed.clear();
        } else {
            event.getChannel().sendMessage("No intro found! Use `.intro` to create one.").queue();
        }
    } catch (Exception e) {
        e.printStackTrace();
        event.getChannel().sendMessage("Oops, something went wrong fetching your intro.").queue();
    }
    return;
}

	
		
		if (args[0].equalsIgnoreCase(prefix + "Bot Info"))
		{
			EmbedBuilder Info = new EmbedBuilder();

			//MessageEmbed userCard = new MessageEmbed();
			
			Info.setTitle("Stud Introduction");
			Info.setDescription("I am a bot who is a work in progress...");
			Info.addField("Creator","IDK",false);
			Info.setColor(0xf45642);
			Info.setFooter("Created by Myst", event.getMember().getUser().getAvatarUrl());

			event.getChannel().sendMessageEmbeds(Info.build()).queue();



			Info.clear();
		}
		
		
	}

}
