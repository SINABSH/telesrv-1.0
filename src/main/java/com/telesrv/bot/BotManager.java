package com.telesrv.bot;

import com.telesrv.Telesrv;
import com.telesrv.config.ConfigManager;

public class BotManager {

    private MyTelegramBot telegramBot;
    private MyDiscordBot discordBot;

    public BotManager(ConfigManager configManager, Telesrv plugin) {
        this.telegramBot = new MyTelegramBot(configManager, plugin);
        this.discordBot = new MyDiscordBot(configManager, plugin);
    }

    public void startBots() {
        telegramBot.registerBot();
        discordBot.start();
    }

    public void stopBots() {
        telegramBot.stop();
        discordBot.stop();
    }

    public MyTelegramBot getTelegramBot() {
        return telegramBot;
    }

    public MyDiscordBot getDiscordBot() {
        return discordBot;
    }
}
