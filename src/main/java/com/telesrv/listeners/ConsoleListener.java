package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleListener implements Listener {
    private final BotManager botManager;

    public ConsoleListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @EventHandler
    public void onConsoleLog(ServerCommandEvent event) {
        String message = "[Console] " + event.getCommand();
        // Send Minecraft console command logs to the Discord console channel
        botManager.getDiscordBot().sendToConsoleChannel(message);
        // Optionally, send the message to a Telegram channel as well
        botManager.getTelegramBot().sendToConsoleChannel(message);
    }
}
