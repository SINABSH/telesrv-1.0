package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
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

        // Format the message to send it to Telegram and Discord
        String formattedMessage = "[Minecraft] " + playerName + ": " + message;

        // Send the message to the Discord console channel
        botManager.getDiscordBot().sendToConsoleChannel(formattedMessage);
        // Send the message to the Telegram chat
        botManager.getTelegramBot().sendToConsoleChannel(formattedMessage);
    }
}
