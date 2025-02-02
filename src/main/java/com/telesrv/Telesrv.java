package com.telesrv;

import com.telesrv.bot.BotManager;
import com.telesrv.config.ConfigManager;
import com.telesrv.listeners.ConsoleListener;
import com.telesrv.listeners.DiscordListener;
import com.telesrv.listeners.MinecraftChatListener;
import com.telesrv.listeners.PlayerJoinListener;
import com.telesrv.listeners.PlayerQuitListener;
import com.telesrv.listeners.TelegramListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Telesrv extends JavaPlugin {
    private BotManager botManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Initialize config manager
        configManager = new ConfigManager(this);

        // Initialize Bot Manager
        botManager = new BotManager(configManager, this);

        // Register event listeners
        getServer().getPluginManager().registerEvents(new MinecraftChatListener(botManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(botManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(botManager), this);
        getServer().getPluginManager().registerEvents(new ConsoleListener(botManager), this);

        // Set up Discord and Telegram listeners
        getServer().getScheduler().runTask(this, () -> {
            getServer().getPluginManager().registerEvents(new DiscordListener(botManager), this);
            getServer().getPluginManager().registerEvents(new TelegramListener(botManager), this);
        });

        // Log that the plugin has been enabled
        getLogger().info("Telesrv plugin enabled!");
    }

    @Override
    public void onDisable() {
        // Clean up any resources when the plugin is disabled
        getLogger().info("Telesrv plugin disabled!");
    }

    public BotManager getBotManager() {
        return botManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
