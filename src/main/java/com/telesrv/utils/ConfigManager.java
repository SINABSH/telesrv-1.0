package com.telesrv.config;

import com.telesrv.Telesrv;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final Telesrv plugin;
    private FileConfiguration config;

    public ConfigManager(Telesrv plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        initializeConfig();
    }

    // Initialize the config file with default values
    private void initializeConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();  // Create the plugin folder if it doesn't exist
        }

        // Check if the config file exists; if not, create it with defaults
        if (!plugin.getConfig().contains("discord.token")) {
            plugin.getConfig().set("discord.token", "YOUR_DISCORD_BOT_TOKEN");
        }

        if (!plugin.getConfig().contains("telegram.token")) {
            plugin.getConfig().set("telegram.token", "YOUR_TELEGRAM_BOT_TOKEN");
        }

        if (!plugin.getConfig().contains("telegram.chatId")) {
            plugin.getConfig().set("telegram.chatId", "YOUR_TELEGRAM_CHAT_ID");
        }

        if (!plugin.getConfig().contains("discord.consoleChannelId")) {
            plugin.getConfig().set("discord.consoleChannelId", "YOUR_DISCORD_CONSOLE_CHANNEL_ID");
        }

        plugin.saveConfig();  // Save the config after adding default values
    }

    public String getDiscordToken() {
        return config.getString("discord.token");
    }

    public String getTelegramToken() {
        return config.getString("telegram.token");
    }

    public String getTelegramChatId() {
        return config.getString("telegram.chatId");
    }

    public String getDiscordConsoleChannelId() {
        return config.getString("discord.consoleChannelId");
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }
}
