package com.thebot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEventListener extends ListenerAdapter{
    //@Override

    public String prefix = ".";

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }

        /*
    @Override // why does it have to be here? also i didnt know what it was for, but it was if it was underlined red for guild message
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(bot.prefix + "hi")) { // the BotStartup in the beginning is referencing to the class
            //event.getMessage().reply("This bot is working!").queue(); // the event method is used for input from the user i believe, the queue is to make sure the message in the "" sends
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("no go away").queue(); // use this one so that the bot won't reply to the message
        }
        if (args[0].equalsIgnoreCase(prefix + "yo")) { // didnt have to reference the class since i also created the prefix here as well
            //event.getMessage().reply("This bot is working!").queue(); // the event method is used for input from the user i believe, the queue is to make sure the message in the "" sends
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("no").queue(); // use this one so that the bot won't reply to the message
        }

        if (args[0].equalsIgnoreCase(prefix + "info"))
        {
            EmbedBuilder Info = new EmbedBuilder();

            Info.setTitle("Stud Introduction");
            Info.setDescription("I am a bot who is a work in progress...");
            Info.addField("Creator","IDK",false);
            Info.setColor(0xf45642);
            Info.setFooter("Created by Myst", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendMessage(Info.build()).queue();
            Info.clear();
        }

*/
    }

}
