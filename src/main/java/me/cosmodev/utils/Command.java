package me.cosmodev.utils;

import me.cosmodev.Plugin;
import me.cosmodev.utils.game.messages.ChatUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Command implements CommandExecutor, TabCompleter {
    private static boolean isPlayerUse;
    public Command(String name, JavaPlugin plugin, boolean isPlayerUse){
        plugin.getCommand(name).setExecutor(this);
        plugin.getCommand(name).setTabCompleter(this);
        Command.isPlayerUse = isPlayerUse;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(isPlayerUse && !(commandSender instanceof Player)){
            commandSender.sendMessage("§cПисать эту команду может только игрок.");
            return true;
        }
        execute(commandSender, strings, s);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args, String label);

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return tabComplete(commandSender, strings);
    }
    
    public abstract List<String> tabComplete(CommandSender sender, String[] args);

// Example:
//    public class ExampleCommand extends Command {
//        public ExampleCommand(){
//            super("example", Plugin.getInstance(), false);
//        }
//        @Override
//        public void execute(CommandSender sender, String[] args, String label) {
//            Player player = (Player) sender;
//
//            ChatUtils.sendMessage(player, "§aExample message");
//
//            if(player.getName().equals("Server")){
//                return;
//            }
//
//            ChatUtils.sendMessage(player, "§cВаш ник не Server");
//        }
//
//        @Override
//        public List<String> tabComplete(CommandSender sender, String[] args) {
//            return null;
//        }
//    }
//    Register: new ExampleCommand();

}
