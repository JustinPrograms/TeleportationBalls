package com.lethalflame.teleportationball.Events;

import com.lethalflame.teleportationball.ItemRelated.ItemManager;
import com.lethalflame.teleportationball.TeleportationBall;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ProjectileLaunchEvent implements Listener {
    private final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);

    Map<String, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onProjectileLaunch(org.bukkit.event.entity.ProjectileLaunchEvent e) {
        if (!(e.getEntity() instanceof Snowball)) return;
        if (!(e.getEntity().getShooter() instanceof Player)) return;

        Player thrower = (Player) e.getEntity().getShooter();
        ItemStack item = thrower.getItemInHand();
        String playername = ((Player) e.getEntity().getShooter()).getName();

        if (!item.hasItemMeta()) return;

        ItemMeta itemMeta = thrower.getItemInHand().getItemMeta();


        if (!(thrower.hasPermission("sb.bypass")) || (!(thrower.hasPermission("sb.admin")))) {
            if (plugin.getConfig().getBoolean("sb_cooldownEnabled", true)) {

                if (cooldowns.containsKey(playername)) {
                    if (cooldowns.get(playername) > System.currentTimeMillis()) {
                        long timeLeft = ((cooldowns.get(playername) - System.currentTimeMillis()) / 1000);

                        if (timeLeft > 0) {
                            thrower.sendMessage(ChatColor.LIGHT_PURPLE + "You are currently on cooldown for " + timeLeft + " seconds!");
                            e.setCancelled(true);
                            thrower.getInventory().addItem(ItemManager.sbc);
                            return;
                        }


                    }
                }
            }
        }


        String switcherDisplayName = ItemManager.sbc.getItemMeta().getDisplayName();
        if (!itemMeta.hasDisplayName()) return;
        if (!itemMeta.getDisplayName().equals(switcherDisplayName)) return;
        e.getEntity().setCustomNameVisible(false);
        e.getEntity().setCustomName(switcherDisplayName);

        cooldowns.put(playername, (long) (System.currentTimeMillis() + (plugin.getConfig().getDouble("sb_cooldown") * 1000)));

    }
}
