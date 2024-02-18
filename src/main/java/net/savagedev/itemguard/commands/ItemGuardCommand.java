package net.savagedev.itemguard.commands;

import net.savagedev.itemguard.ItemGuardPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class ItemGuardCommand implements CommandExecutor {
    private final ItemGuardPlugin plugin;

    public ItemGuardCommand(ItemGuardPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        final String action = args[0].toLowerCase(Locale.ROOT);

        if (action.equals("reload")) {
            this.plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Config reloaded.");
        }

        return true;
    }
}
