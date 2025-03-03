package com.telesrv.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import com.telesrv.config.ConfigManager;
import com.telesrv.Telesrv;

import javax.security.auth.login.LoginException;

public class MyDiscordBot extends ListenerAdapter {

    private ConfigManager configManager;
    private Telesrv plugin;

    private net.dv8tion.jda.api.JDA jda;

    public MyDiscordBot(ConfigManager configManager, Telesrv plugin) {
        this.configManager = configManager;
        this.plugin = plugin;
    }

    public void start() {
        try {
            jda = JDABuilder.createDefault(configManager.getProperty("discord.bot.token"))
                    .addEventListeners(this)
                    .build();
            plugin.getLogger().info("Discord Bot Started.");
        } catch (LoginException e) {
            plugin.getLogger().severe("Failed to start Discord Bot!");
        }
    }

    public void stop() {
        if (jda != null) {
            jda.shutdown();
            plugin.getLogger().info("Discord Bot Stopped.");
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        MessageChannel channel = event.getChannel();
        String message = event.getMessage().getContentRaw();
        plugin.getLogger().info("Discord Message: " + message);

        if (message.equalsIgnoreCase("/ping")) {
            channel.sendMessage("🏓 Pong from Telesrv!").queue();
        }
    }

    public void sendMessage(String channelId, String message) {
        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel != null) {
            channel.sendMessage(message).queue();
        } else {
            plugin.getLogger().severe("Discord channel not found!");
        }
    }
}
