package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
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
        String message = playerName + " has joined the server.";

        // Send the player join message to the Discord console channel
        botManager.getDiscordBot().sendToConsoleChannel(message);
        // Send the player join message to the Telegram chat
        botManager.getTelegramBot().sendToConsoleChannel(message);
    }
}
