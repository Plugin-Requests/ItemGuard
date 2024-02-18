package net.savagedev.itemguard.listeners;

import net.savagedev.itemguard.ItemGuardPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class ItemDestroyListener implements Listener {
    private final ItemGuardPlugin plugin;

    public ItemDestroyListener(ItemGuardPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST) // Players' items are important...
    public void on(EntityDamageEvent event) {
        final DamageCause cause = event.getCause();

        if (cause != DamageCause.LAVA) {
            return;
        }

        final Entity damagedEntity = event.getEntity();

        if (damagedEntity.getType() != EntityType.DROPPED_ITEM) {
            return;
        }

        final ItemStack damagedItem = ((Item) damagedEntity).getItemStack();

        if (this.plugin.getConfig().getStringList("lava-guard").stream().anyMatch(damagedItem.getType().name()::equalsIgnoreCase)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)// VERY important...
    public void on(EntityDamageByBlockEvent event) {
        final Block damager = event.getDamager();

        if (damager == null || damager.getType() != Material.CACTUS) {
            return;
        }

        final Entity damagedEntity = event.getEntity();

        if (damagedEntity.getType() != EntityType.DROPPED_ITEM) {
            return;
        }

        final ItemStack damagedItem = ((Item) damagedEntity).getItemStack();

        if (this.plugin.getConfig().getStringList("cactus-guard").stream().anyMatch(damagedItem.getType().name()::equalsIgnoreCase)) {
            event.setCancelled(true);
        }
    }
}
