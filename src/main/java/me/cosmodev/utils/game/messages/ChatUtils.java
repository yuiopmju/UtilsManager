package me.cosmodev.utils.game.messages;

import me.cosmodev.Plugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ChatUtils {

    // Форматирование
    public static String format(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    // Простая отправка сообщений

    public static void sendMessage(Player player, String text){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public static void broadcastMessage(String text){
        for(Player player: Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
        }
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int slay, int fadeOut){
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), fadeIn, slay, fadeOut);
    }

    public static void sendTitle(Player player, String title, String subtitle){
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), 1,1, 1);
    }

    public static void broadcastTitle(String title, String subtitle, int fadeIn, int slay, int fadeOut){
        for(Player player: Bukkit.getOnlinePlayers()){
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), fadeIn, slay, fadeOut);
        }
    }

    public static void broadcastTitle(String title, String subtitle){
        for(Player player: Bukkit.getOnlinePlayers()){
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), 1, 1,1);
        }
    }

    public static void sendActionBar(Player player, String text, int sec) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
            }
        }.runTaskLater(Plugin.getInstance(), sec * 20L);
    }

    public static void broadcastActionBar(String text, int sec){
        for(Player player: Bukkit.getOnlinePlayers()){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));

            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
                }
            }.runTaskLater(Plugin.getInstance(), sec * 20L);
        }
    }

    // Отправка сообщений из конфигов

    public static void sendMessageFromConfig(Player player, String path){
        String text = Plugin.getInstance().getConfig().getString(path);
        if (text != null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
        }
    }

    public static void broadcastMessageFromConfig(String path){
        String text = Plugin.getInstance().getConfig().getString(path);
        if (text != null) {
            for(Player player: Bukkit.getOnlinePlayers()){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
            }
        }
    }

    public static void sendTitleFromConfig(Player player, String pathTitle, String pathSubTitle, int fadeIn, int slay, int fadeOut){
        String titlee = Plugin.getInstance().getConfig().getString(pathTitle);
        String subtitlee = Plugin.getInstance().getConfig().getString(pathSubTitle);
        if (titlee != null && subtitlee != null) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', titlee), ChatColor.translateAlternateColorCodes('&', subtitlee), fadeIn, slay, fadeOut);
        }
    }

    public static void sendTitleFromConfig(Player player, String pathTitle, String pathSubTitle){
        String titlee = Plugin.getInstance().getConfig().getString(pathTitle);
        String subtitlee = Plugin.getInstance().getConfig().getString(pathSubTitle);
        if (titlee != null && subtitlee != null) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', titlee), ChatColor.translateAlternateColorCodes('&', subtitlee), 1,1, 1);
        }
    }

    public static void broadcastTitleFromConfig(String pathTitle, String pathSubTitle){
        String titlee = Plugin.getInstance().getConfig().getString(pathTitle);
        String subtitlee = Plugin.getInstance().getConfig().getString(pathSubTitle);
        if (titlee != null && subtitlee != null) {
            for (Player player: Bukkit.getOnlinePlayers()){
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', titlee), ChatColor.translateAlternateColorCodes('&', subtitlee), 1, 1, 1);
            }
        }
    }

    public static void broadcastTitleFromConfig(String pathTitle, String pathSubTitle, int fadeIn, int stay, int fadeOut){
        String titlee = Plugin.getInstance().getConfig().getString(pathTitle);
        String subtitlee = Plugin.getInstance().getConfig().getString(pathSubTitle);
        if (titlee != null && subtitlee != null) {
            for (Player player: Bukkit.getOnlinePlayers()){
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', titlee), ChatColor.translateAlternateColorCodes('&', subtitlee), fadeIn, stay, fadeOut);
            }
        }
    }

    public static void sendActionBarFromConfig(Player player, String path, int sec){
        String text = Plugin.getInstance().getConfig().getString(path);
        if (text != null) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));

            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
                }
            }.runTaskLater(Plugin.getInstance(), sec * 20L);
        }
    }

    public static void broadcastActionBarFromConfig(String path, int sec){
        String text = Plugin.getInstance().getConfig().getString(path);
        if (text != null) {
            for(Player player: Bukkit.getOnlinePlayers()){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));

                BukkitTask task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
                    }
                }.runTaskLater(Plugin.getInstance(), sec * 20L);
            }
        }
    }
}
