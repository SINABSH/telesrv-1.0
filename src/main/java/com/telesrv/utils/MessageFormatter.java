package com.telesrv.utils;

public class MessageFormatter {

    public static String formatFromTelegram(String username, String message) {
        return "[Telegram] " + username + ": " + message;
    }

    public static String formatFromDiscord(String username, String message) {
        return "[Discord] " + username + ": " + message;
    }

    public static String formatFromMinecraft(String username, String message) {
        return "[Minecraft] " + username + ": " + message;
    }

    public static String formatPlayerJoinMessage(String username) {
        return "[Server] " + username + " has joined the game.";
    }

    public static String formatPlayerQuitMessage(String username) {
        return "[Server] " + username + " has left the game.";
    }
}
