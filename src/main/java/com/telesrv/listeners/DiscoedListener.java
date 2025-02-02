package com.telesrv.listeners;

import com.telesrv.bot.BotManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordListener extends ListenerAdapter {
    private final BotManager botManager;

    public DiscordListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return; // Ignore messages from bots
        }

        String message = event.getMessage().getContentDisplay();
        String sender = event.getAuthor().getName();

        // Send the received Discord message to the Minecraft server
        String formattedMessage = "[Discord] " + sender + ": " + message;
        botManager.getPlugin().getServer().broadcastMessage(formattedMessage);
    }
}
