package com.telesrv.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.telesrv.config.ConfigManager;
import com.telesrv.Telesrv;

public class MyTelegramBot extends TelegramLongPollingBot {

    private ConfigManager configManager;
    private Telesrv plugin;

    public MyTelegramBot(ConfigManager configManager, Telesrv plugin) {
        this.configManager = configManager;
        this.plugin = plugin;
    }

    @Override
    public String getBotUsername() {
        return configManager.getProperty("telegram.bot.username");
    }

    @Override
    public String getBotToken() {
        return configManager.getProperty("telegram.bot.token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            plugin.getLogger().info("Telegram Message: " + message);

            if (message.equalsIgnoreCase("/ping")) {
                sendMessage(update.getMessage().getChatId(), "🏓 Pong from Telesrv!");
            }
        }
    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            plugin.getLogger().severe("Failed to send Telegram message!");
        }
    }

    public void stop() {
        plugin.getLogger().info("Telegram Bot Stopped.");
    }
}
