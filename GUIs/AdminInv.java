package com.lethalflame.teleportationball.Guis;

import com.lethalflame.teleportationball.TeleportationBall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminInv implements InventoryHolder {

    private final Inventory AdminInv;
    private final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);

    public AdminInv() {
        AdminInv = Bukkit.createInventory(this, 9, "Switcher Ball Plugin (ADMIN)");
        init();
    }

    private void init(){
        ArrayList<String> configSettings = new ArrayList<>();
        configSettings.add(ChatColor.YELLOW + "Range: " + ChatColor.AQUA + plugin.getConfig().getDouble("sb_range"));
        configSettings.add(ChatColor.YELLOW + "Cooldown: " + ChatColor.AQUA + plugin.getConfig().getBoolean("sb_cooldownEnabled"));
        configSettings.add(ChatColor.YELLOW + "Cooldown: " + ChatColor.AQUA + plugin.getConfig().getDouble("sb_cooldown") + " seconds");

        ArrayList<String> authorInfo = new ArrayList<>();
        authorInfo.add(ChatColor.YELLOW + "Plugin: " + ChatColor.AQUA + plugin.getDescription().getName());
        authorInfo.add(ChatColor.YELLOW + "Author: " + ChatColor.AQUA + plugin.getDescription().getAuthors());
        authorInfo.add(ChatColor.YELLOW + "Verison: " + ChatColor.AQUA + plugin.getDescription().getVersion());


        ItemStack item;
        for (int i = 0; i < 8;  i++) {
            item = createItem("", Material.STAINED_GLASS_PANE, Collections.singletonList(""));
            AdminInv.setItem(i, item);
        }

        item = createItem("§r§bConfig", Material.SIGN, configSettings);
        AdminInv.setItem(4, item);

        item = createItem("§r§bInformation", Material.DIAMOND_BLOCK, authorInfo);
        AdminInv.setItem(8, item);

        item = createItem("§r§bInformation", Material.REDSTONE_BLOCK, Collections.singletonList("Click for admin GUI"));
        AdminInv.setItem(0, item);

    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return AdminInv;
    }
}
