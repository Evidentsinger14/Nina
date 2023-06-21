package dev.ev1dent.nina;

import dev.ev1dent.nina.events.MessageEventListener;
import dev.ev1dent.nina.events.ReadyEventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class NinaBrains {
    public static void main(String[] args) {
        final Dotenv config;
        config = Dotenv.configure().load();
        
        final String BOT_TOKEN = config.get("BOT_TOKEN");
        JDABuilder jdaBuilder = JDABuilder.createDefault(BOT_TOKEN);

        JDA jda = jdaBuilder
                // Start Intents
                .enableIntents(
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MESSAGE_TYPING,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.MESSAGE_CONTENT
                )
                // Start add event listeners
                .addEventListeners(
                        new ReadyEventListener(),
                        new MessageEventListener()
                )
                // Build code into a bot.
                .build();

        jda.updateCommands().queue();
        jda.upsertCommand("ping", "ping pong").setGuildOnly(true).queue();

    }
}
