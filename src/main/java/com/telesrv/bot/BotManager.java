package com.telesrv.bot;

import com.telesrv.Telesrv;
import net.dv8tion.jda.api.entities.TextChannel;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotManager {
    private final Telesrv plugin;
    private MyTelegramBot telegramBot;
    private MyDiscordBot discordBot;

    public BotManager(Telesrv plugin) {
        this.plugin = plugin;
    }

    public void startBots() {
        // Start the Telegram bot
        String telegramToken = plugin.getConfig().getString("telegram.token");
        telegramBot = new MyTelegramBot(telegramToken);
        try {
            telegramBot.start();
        } catch (TelegramApiException e) {
            plugin.getLogger().severe("Failed to start Telegram bot: " + e.getMessage());
        }

        // Start the Discord bot
        String discordToken = plugin.getConfig().getString("discord.token");
        discordBot = new MyDiscordBot(discordToken);
        try {
            discordBot.start();
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to start Discord bot: " + e.getMessage());
        }
    }

    public void stopBots() {
        if (telegramBot != null) {
            telegramBot.stop();
        }
        if (discordBot != null) {
            discordBot.stop();
        }
    }

    public MyTelegramBot getTelegramBot() {
        return telegramBot;
    }

    public MyDiscordBot getDiscordBot() {
        return discordBot;
    }

    public Telesrv getPlugin() {
        return plugin;
    }
}
