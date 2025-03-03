package com.telesrv.listeners;

import com.telesrv.Telesrv;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final Telesrv plugin;

    public PlayerQuitListener(Telesrv plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String message = "❌ " + event.getPlayer().getName() + " left the server!";
        plugin.getBotManager().getTelegramBot().sendToConsoleChannel(message);
        plugin.getBotManager().getDiscordBot().sendMessage(plugin.getConfigManager().getProperty("discord.console.channel"), message);
    }
}
