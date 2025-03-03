package com.telesrv.bot;

import com.telesrv.config.ConfigManager;
import com.telesrv.listeners.TelegramListener;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyTelegramBot extends TelegramLongPollingBot {

    private final BotManager botManager;
    private String botToken;
    private String botUsername;

    public MyTelegramBot(String botToken, BotManager botManager) {
        this.botToken = botToken;
        this.botManager = botManager;
        this.botUsername = ConfigManager.getProperty("telegram.bot.username");
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            new TelegramListener(botManager).handleMessage(update.getMessage());
        }
    }

    public void sendMessageToTelegram(Long chatId, String messageText) {
        try {
            SendMessage sendMessage = new SendMessage(chatId.toString(), messageText);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("Error sending message to Telegram: " + e.getMessage());
        }
    }

    public void startBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
            System.out.println("Telegram bot started!");
        } catch (TelegramApiException e) {
            System.err.println("Error starting Telegram bot: " + e.getMessage());
        }
    }
}
