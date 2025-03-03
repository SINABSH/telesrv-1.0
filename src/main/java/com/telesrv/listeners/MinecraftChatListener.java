package com.telesrv.listeners;

import com.telesrv.Telesrv;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftChatListener implements Listener {

    private final Telesrv plugin;

    public MinecraftChatListener(Telesrv plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = "[MC] " + event.getPlayer().getName() + ": " + event.getMessage();
        plugin.getBotManager().getTelegramBot().sendToConsoleChannel(message);
        plugin.getBotManager().getDiscordBot().sendMessage(plugin.getConfigManager().getProperty("discord.chat.channel"), message);
    }
}
