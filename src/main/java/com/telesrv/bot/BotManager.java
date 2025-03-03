package com.telesrv.bot;

import com.telesrv.config.ConfigManager;
import net.dv8tion.jda.api.JDA;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotManager {

    private MyTelegramBot telegramBot;
    private MyDiscordBot discordBot;
    private BotSession telegramBotSession;
    private JDA discordJDA;

    public BotManager() {
        initTelegramBot();
        initDiscordBot();
    }

    private void initTelegramBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBot = new MyTelegramBot(ConfigManager.getProperty("telegram.bot.token"), this);
            telegramBotSession = telegramBotsApi.registerBot(telegramBot);
            System.out.println("Telegram bot initialized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize Telegram bot: " + e.getMessage());
        }
    }

    private void initDiscordBot() {
        try {
            discordBot = new MyDiscordBot(this);
            discordJDA = discordBot.buildJDA(ConfigManager.getProperty("discord.bot.token"));
            discordJDA.awaitReady();
            System.out.println("Discord bot initialized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize Discord bot: " + e.getMessage());
        }
    }

    public void shutdownBots() {
        if (telegramBotSession != null) {
            telegramBotSession.stop();
            System.out.println("Telegram bot session stopped.");
        }
        if (discordJDA != null) {
            discordJDA.shutdown();
            System.out.println("Discord JDA stopped.");
        }
    }

    public MyTelegramBot getTelegramBot() {
        return telegramBot;
    }

    public MyDiscordBot getDiscordBot() {
        return discordBot;
    }
}
