package UserIntro;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import IntroDB.IntroDatabase;

public class UserIntro {

    private String state = "ASK_YN";  

    private String userId;
    private String name;
    private String hobbies;
    private String intoRightNow;
    private String favFood;
    private String funFact;
    private String birthday;

    public void handleIntro(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentDisplay();

        userId = event.getAuthor().getId(); // stores the user ID 

        // Intro Questions
        switch (state) {
            case "ASK_YN":
                if (msg.equalsIgnoreCase(".Y")) {
                    event.getChannel().sendMessage("Okay, what is your name?").queue();
                    state = "ASK_NAME";
                } else if (msg.equalsIgnoreCase(".N")) {
                    event.getChannel().sendMessage("Alright!").queue();
                    state = "DONE";
                }
                break;

            case "ASK_NAME":
                this.name = msg;
                event.getChannel().sendMessage("Nice to meet you, " + name + "! What are your hobbies?").queue();
                state = "ASK_HOBBIES";
                break;

            case "ASK_HOBBIES":
                this.hobbies = msg;
                event.getChannel().sendMessage("Cool! What are you into right now?").queue();
                state = "ASK_INTO";
                break;

            case "ASK_INTO":
                this.intoRightNow = msg;
                event.getChannel().sendMessage("Got it! What's your favorite food?").queue();
                state = "ASK_FOOD";
                break;

            case "ASK_FOOD":
                this.favFood = msg;
                event.getChannel().sendMessage("Yum! Tell me a fun fact about yourself.").queue();
                state = "ASK_FACT";
                break;

            case "ASK_FACT":
                this.funFact = msg;
                event.getChannel().sendMessage("Nice! When’s your birthday?").queue();
                state = "ASK_BDAY";
                break;

            case "ASK_BDAY":
                this.birthday = msg;
                event.getChannel().sendMessage("Thanks! Your intro is complete.").queue();
                state = "DONE";

                // Save to the database 
              IntroDatabase db = new IntroDatabase();

               db.saveIntro(event.getAuthor().getId(), name, hobbies, intoRightNow, favFood, funFact, birthday);

                break;

            case "DONE":
                event.getChannel().sendMessage("Type `.myIntro` to see your info card!").queue();
                break;
        }
    }

   

    public boolean isDone() {
        return state.equals("DONE");
    }
}
