package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import com.telesrv.utils.MessageFormatter;
import com.telesrv.config.ConfigManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramListener {

    private final BotManager botManager;

    public TelegramListener(BotManager botManager) {
        this.botManager = botManager;
    }

    public void handleMessage(Message message) {
        String messageText = message.getText();
        String formattedMessage = MessageFormatter.formatFromTelegram(message.getFrom().getFirstName(), messageText);

        // Send the message to Minecraft and Discord
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
