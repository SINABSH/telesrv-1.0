package com.telesrv.bot;

import com.telesrv.listeners.DiscordListener;
import com.telesrv.config.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class MyDiscordBot {

    private JDA jda;
    private final BotManager botManager;

    public MyDiscordBot(BotManager botManager) {
        this.botManager = botManager;
    }

    public JDA buildJDA(String token) {
        try {
            jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .disableCache(CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY)
                .addEventListeners(new DiscordListener(botManager))
                .build();
            jda.awaitReady();
            System.out.println("Discord bot ready!");
        } catch (Exception e) {
            System.err.println("Error starting Discord bot: " + e.getMessage());
        }
        return jda;
    }

    public void sendMessageToChannel(String channelId, String message) {
        jda.getTextChannelById(channelId).sendMessage(message).queue();
    }

    public void shutdown() {
        if (jda != null) {
            jda.shutdown();
        }
    }
}
