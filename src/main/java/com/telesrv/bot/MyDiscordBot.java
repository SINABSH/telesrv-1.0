package com.telesrv.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;

public class MyDiscordBot extends ListenerAdapter {
    private final String token;
    private TextChannel consoleChannel;

    public MyDiscordBot(String token) {
        this.token = token;
    }

    public void start() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners(this);
        builder.build().awaitReady();

        consoleChannel = builder.build().getTextChannelById("YOUR_CONSOLE_CHANNEL_ID");
    }

    public void sendMessage(String message) {
        if (consoleChannel != null) {
            consoleChannel.sendMessage(message).queue();
        }
    }

    public void sendToConsoleChannel(String message) {
        if (consoleChannel != null) {
            consoleChannel.sendMessage(":desktop: **" + message + "**").queue();
        }
    }
}
