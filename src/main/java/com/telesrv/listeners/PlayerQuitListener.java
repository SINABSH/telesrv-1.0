package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import com.telesrv.utils.MessageFormatter;
import com.telesrv.config.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final BotManager botManager;

    public PlayerQuitListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        String formattedMessage = MessageFormatter.formatPlayerQuitMessage(playerName);

        // Send the quit message to Telegram and Discord
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
