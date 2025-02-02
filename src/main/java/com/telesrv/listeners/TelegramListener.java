package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public class TelegramListener {
    private final BotManager botManager;

    public TelegramListener(BotManager botManager) {
        this.botManager = botManager;
    }

    // This method will handle the updates received from Telegram (messages or commands)
    public void onTelegramMessageReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = message.getText();

            // Check if the message is a command or chat message
            if (text != null && text.startsWith("/")) {
                handleCommand(text, message);
            } else {
                // Forward the chat message to Minecraft
                String playerName = message.getFrom().getUserName();
                String formattedMessage = "[Telegram] " + playerName + ": " + text;
                botManager.getPlugin().getServer().broadcastMessage(formattedMessage);
            }
        }
    }

    // Handle commands from Telegram (if needed)
    private void handleCommand(String command, Message message) {
        String response = "Unknown command";
        if (command.equals("/help")) {
            response = "List of available commands:\n/help - Show this message";
        }

        // Send a response back to Telegram
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);

        try {
            botManager.getTelegramBot().execute(sendMessage);  // Send the message back to Telegram
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Optionally, send a photo to Telegram chat
    public void sendPhotoToTelegram(String chatId, String photoUrl, String caption) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(photoUrl);
        sendPhoto.setCaption(caption);

        try {
            botManager.getTelegramBot().execute(sendPhoto);  // Send the photo to Telegram
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
