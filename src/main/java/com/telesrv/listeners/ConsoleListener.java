package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import com.telesrv.config.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleListener implements Listener {

    private final BotManager botManager;

    public ConsoleListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        String command = event.getCommand();

        // Send the console command to Telegram and Discord
        botManager.getTelegramBot().sendMessageToTelegram(
            Long.parseLong(ConfigManager.getProperty("telegram.chat.id")), 
            "Console: " + command
        );
        botManager.getDiscordBot().sendMessageToChannel(
            ConfigManager.getProperty("discord.channel.id"),
            "Console: " + command
        );
    }
}
