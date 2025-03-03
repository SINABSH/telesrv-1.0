package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import com.telesrv.utils.MessageFormatter;
import com.telesrv.config.ConfigManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordListener extends ListenerAdapter {

    private final BotManager botManager;

    public DiscordListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return; // Ignore messages from bots
        }

        String message = event.getMessage().getContentRaw();
        String formattedMessage = MessageFormatter.formatFromDiscord(event.getAuthor().getName(), message);

        // Send the message to Minecraft and Telegram
        botManager.getTelegramBot().sendMessageToTelegram(
            Long.parseLong(ConfigManager.getProperty("telegram.chat.id")), 
            formattedMessage
        );
        botManager.getDiscordBot().sendMessageToChannel(
            ConfigManager.getProperty("discord.channel.id"), 
            formattedMessage
        );
    }
}
