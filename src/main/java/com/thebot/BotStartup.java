package com.thebot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
//import Introductions.*;

import io.github.cdimascio.dotenv.Dotenv;





    public class BotStartup { // start of class

    public static String prefix = ".";

    public static void main(String[] args) throws LoginException { // main start

       // Dotenv dotenv = Dotenv.configure().load();
        //Dotenv dotenv = Dotenv.configure()
               // .directory("src/main/resources")   // make sure this is correct
                //.filename(".env")                  // or "dotenv" if you renamed it
                //.load();

        //String token = dotenv.get("DISCORD_BOT_TOKEN");
       // System.out.println("Token: " + token);  // this should not be null
          String TOK = "";
   
        //Dotenv dotenv = Dotenv.load();
        //String token = dotenv.get("DISCORD_BOT_TOKEN");
        JDABuilder jda = JDABuilder.createDefault(TOK);
        jda.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES);
       
        jda.addEventListeners(new Commands());

        jda.setActivity(Activity.playing("tic tac toe"));
        jda.setStatus(OnlineStatus.ONLINE);


        //   jda.addEventListeners(new ReadyEventListener());// enables explicit access to message.getContentDisplay()
        jda.build();

        //jda.setActivity(Activity.playing("tic tac toe"));
        //jda.setStatus(OnlineStatus.ONLINE);
        //.addEventListeners(new Commands()); //this method is referencing the Commands class
        // jda.build();
    }

} // end class

