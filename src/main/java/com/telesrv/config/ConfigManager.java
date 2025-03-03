package com.telesrv.config;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Properties;
import java.io.InputStream;

public class ConfigManager {

    private Properties properties = new Properties();

    public ConfigManager(JavaPlugin plugin) {
        try (InputStream input = plugin.getResource("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            plugin.getLogger().severe("Could not load config.properties!");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "NOT_SET");
    }
}
