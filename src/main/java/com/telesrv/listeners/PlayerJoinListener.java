package com.telesrv.listeners;

import com.telesrv.Telesrv;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Telesrv plugin;

    public PlayerJoinListener(Telesrv plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String message = "✅ " + event.getPlayer().getName() + " joined the server!";
        plugin.getBotManager().getTelegramBot().sendToConsoleChannel(message);
        plugin.getBotManager().getDiscordBot().sendMessage(plugin.getConfigManager().getProperty("discord.console.channel"), message);
    }
}
