package com.telesrv;

import com.telesrv.bot.BotManager;
import com.telesrv.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Telesrv extends JavaPlugin {

    private BotManager botManager;

    @Override
    public void onEnable() {
        // Initialize the bot manager
        botManager = new BotManager();

        // Register listeners
        getServer().getPluginManager().registerEvents(new ConsoleListener(botManager), this);
        getServer().getPluginManager().registerEvents(new MinecraftChatListener(botManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(botManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(botManager), this);

        getLogger().info("Telesrv plugin enabled!");
    }

    @Override
    public void onDisable() {
        // Shutdown bots
        if (botManager != null) {
            botManager.shutdownBots();
        }
        getLogger().info("Telesrv plugin disabled!");
    }

    public BotManager getBotManager() {
        return botManager;
    }
}
