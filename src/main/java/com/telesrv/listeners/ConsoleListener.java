package com.telesrv.listeners;

import com.telesrv.Telesrv;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleListener implements Listener {

    private final Telesrv plugin;

    public ConsoleListener(Telesrv plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        String message = "[Console] " + event.getCommand();
        plugin.getBotManager().getTelegramBot().sendToConsoleChannel(message);
        plugin.getBotManager().getDiscordBot().sendMessage(plugin.getConfigManager().getProperty("discord.console.channel"), message);
    }
}
