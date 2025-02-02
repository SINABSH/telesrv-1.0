package com.telesrv.utils;

public class MessageFormatter {

    // Formats Minecraft chat messages for Discord and Telegram
    public static String formatMinecraftMessage(String playerName, String message) {
        return "[Minecraft] " + playerName + ": " + message;
    }

    // Formats Discord chat messages for Minecraft
    public static String formatDiscordMessage(String userName, String message) {
        return "[Discord] " + userName + ": " + message;
    }

    // Formats Telegram chat messages for Minecraft
    public static String formatTelegramMessage(String userName, String message) {
        return "[Telegram] " + userName + ": " + message;
    }

    // Formats player join messages for Discord and Telegram
    public static String formatPlayerJoinMessage(String playerName) {
        return playerName + " has joined the server.";
    }

    // Formats player quit messages for Discord and Telegram
    public static String formatPlayerQuitMessage(String playerName) {
        return playerName + " has left the server.";
    }

    // Formats console log messages for Discord and Telegram
    public static String formatConsoleMessage(String message) {
        return ":desktop: **" + message + "**";
    }
}
