package dev.ev1dent.nina.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class MessageEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if(event.getAuthor().isBot()) return;

        String serverMessage = (event.getMessage().getContentRaw());
        Message message = event.getMessage();

        // listen for some example messages
        if(serverMessage.contains("XQII: I fell ShIt oN By The RanDy")
                || serverMessage.contains("<@808566443024056382>")
                || serverMessage.contains("XQII: YOU'RE THE WORST 1k star I've ever vsed")){

            // what role should we give these mfs
            Role muted = Objects.requireNonNull(event.getGuild()).getRolesByName("Muted", true).get(0);
           Member member = event.getMember();
            try{

                // Embed sent to admin channel
                EmbedBuilder theseFuckingRandysBro = new EmbedBuilder()
                        .setTitle("these fucking randys bro....", null)
                        .setColor(Color.RED)
                        .setDescription("Someone posted an XQII spam message, or mentioned them. " +
                                "I went ahead and muted them, and deleted the message for you. 😁");

                // add the muted role to the user
                event.getGuild().addRoleToMember(member, muted).queue();
                TextChannel admin = event.getGuild().getTextChannelById(775966134104883239L);

                admin.sendMessageEmbeds(theseFuckingRandysBro.build()).queue();
                message.delete().queue();

            } catch (Exception e){
                System.out.print(e.getMessage());
            }
        }

    }
}
