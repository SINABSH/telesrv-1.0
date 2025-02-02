package com.telesrv.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTelegramBot extends TelegramLongPollingBot {
    private final String token;
    private final String botUsername;

    public MyTelegramBot(String token) {
        this.token = token;
        this.botUsername = "YourBotUsername";  // Set this to your bot's username
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            // Here you can handle received messages and send them to the Minecraft server
            System.out.println("Received message from Telegram: " + messageText);
        }
    }

    public void sendMessage(String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);

        try {
            execute(message);  // Send the message to Telegram
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendToConsoleChannel(String message) {
        // Replace with actual logic to send logs to Telegram (e.g., a specific group/chat)
        sendMessage("YOUR_TELEGRAM_CHAT_ID", ":desktop: **" + message + "**");
    }
}
