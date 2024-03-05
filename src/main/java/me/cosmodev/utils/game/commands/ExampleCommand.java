package me.cosmodev.utils.game.commands;

import me.cosmodev.Plugin;
import me.cosmodev.utils.Command;
import me.cosmodev.utils.game.messages.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ExampleCommand extends Command {
    public ExampleCommand(){
        super("example", Plugin.getInstance(), false);
    }
    @Override
    public void execute(CommandSender sender, String[] args, String label) {
        Player player = (Player) sender;

        ChatUtils.sendMessage(player, "§aExample message");

        if(player.getName().equals("Server")){
            return;
        }

        ChatUtils.sendMessage(player, "§cВаш ник не Server");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
