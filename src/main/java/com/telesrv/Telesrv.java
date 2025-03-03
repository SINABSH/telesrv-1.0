package com.telesrv;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.telesrv.bot.BotManager;
import com.telesrv.config.ConfigManager;

public class Telesrv extends JavaPlugin {

    private BotManager botManager;
    private ConfigManager configManager;

    @Override
public void onEnable() {
    getLogger().info("Telesrv Plugin is starting...");

    botManager = new BotManager(configManager, this);
    botManager.startBots();

    // Register Event Listeners
    getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    getServer().getPluginManager().registerEvents(new MinecraftChatListener(this), this);
    getServer().getPluginManager().registerEvents(new ConsoleListener(this), this);

    @Override
    public void onDisable() {
        getLogger().info("Telesrv Plugin is stopping...");
        botManager.stopBots();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public BotManager getBotManager() {
        return botManager;
    }
}
