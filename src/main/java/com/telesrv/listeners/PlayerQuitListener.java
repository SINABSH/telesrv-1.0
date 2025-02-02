package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
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
        String message = playerName + " has left the server.";

        // Send the player quit message to the Discord console channel
        botManager.getDiscordBot().sendToConsoleChannel(message);
        // Send the player quit message to the Telegram chat
        botManager.getTelegramBot().sendToConsoleChannel(message);
    }
}
