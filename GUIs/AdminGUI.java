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

public class AdminGUI implements InventoryHolder {

    private final Inventory AdminGUI;
    private final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);

    public AdminGUI() {
        AdminGUI = Bukkit.createInventory(this, 9, "Admin GUI");
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

        ArrayList<String> addSwitcher = new ArrayList<>();
        addSwitcher.add(ChatColor.YELLOW + "Left Click to add x1 to your inventory");
        addSwitcher.add(ChatColor.YELLOW + "Right Click to add more to your inventory");



        ItemStack item;

        for (int i = 0; i < 9;  i++) {
            item = createItem("", Material.STAINED_GLASS_PANE, Collections.singletonList(""));
            AdminGUI.setItem(i, item);
        }

        item = createItem("§r§bConfig", Material.SIGN, configSettings);
        AdminGUI.setItem(7, item);

        item = createItem("§r§bInformation", Material.DIAMOND_BLOCK, authorInfo);
        AdminGUI.setItem(8, item);

        item = createItem("§r§bAdd Switchers", Material.SNOW_BALL, addSwitcher);
        AdminGUI.setItem(0, item);


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
        return AdminGUI;
    }
}

