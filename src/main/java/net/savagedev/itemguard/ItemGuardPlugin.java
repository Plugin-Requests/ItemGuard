package net.savagedev.itemguard;

import net.savagedev.itemguard.commands.ItemGuardCommand;
import net.savagedev.itemguard.listeners.ItemDestroyListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemGuardPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new ItemDestroyListener(this), this);
        this.getCommand("itemguard").setExecutor(new ItemGuardCommand(this));
    }
}
