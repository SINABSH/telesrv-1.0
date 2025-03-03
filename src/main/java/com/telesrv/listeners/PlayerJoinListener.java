package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import com.telesrv.utils.MessageFormatter;
import com.telesrv.config.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final BotManager botManager;

    public PlayerJoinListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        String formattedMessage = MessageFormatter.formatPlayerJoinMessage(playerName);

        // Send the join message to Telegram and Discord
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
