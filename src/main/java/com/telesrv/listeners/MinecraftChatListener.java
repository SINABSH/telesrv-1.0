package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import com.telesrv.utils.MessageFormatter;
import com.telesrv.config.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftChatListener implements Listener {

    private final BotManager botManager;

    public MinecraftChatListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();
        String formattedMessage = MessageFormatter.formatFromMinecraft(playerName, message);

        // Send the chat message to Telegram and Discord
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
