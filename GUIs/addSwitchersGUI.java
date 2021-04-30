package com.lethalflame.teleportationball.Guis;

import com.lethalflame.teleportationball.TeleportationBall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class addSwitchersGUI implements InventoryHolder {

    private final Inventory addSwitchersGUI;
    private final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);

    public addSwitchersGUI() {
        addSwitchersGUI = Bukkit.createInventory(this, 54, "Add Switchers To Your Inventory");
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

        ArrayList<String> add4 = new ArrayList<>();
        add4.add(ChatColor.YELLOW + "Add x4 switchers your inventory.");

        ArrayList<String> add8 = new ArrayList<>();
        add8.add(ChatColor.YELLOW + "Add x8 switchers your inventory.");

        ArrayList<String> add16 = new ArrayList<>();
        add16.add(ChatColor.YELLOW + "Add x16 switchers your inventory.");

        ArrayList<String> addCustom = new ArrayList<>();
        addCustom.add(ChatColor.YELLOW + "Add a custom amount of switchers to your inventory.");



        ItemStack item;
        for (int i = 0; i < 53;  i++) {
            item = createItem("", Material.STAINED_GLASS_PANE, Collections.singletonList(""), 1, false);
            addSwitchersGUI.setItem(i, item);
        }

        item = createItem("§r§bConfig", Material.SIGN, configSettings,1, false);
        addSwitchersGUI.setItem(52, item);

        item = createItem("§r§bInformation", Material.DIAMOND_BLOCK, authorInfo, 1, false);
        addSwitchersGUI.setItem(53, item);

        item = createItem("§r§bAdd x4", Material.SNOW_BALL, add4,4, true );
        addSwitchersGUI.setItem(20, item);

        item = createItem("§r§bAdd x8", Material.SNOW_BALL, add8, 8, true);
        addSwitchersGUI.setItem(22, item);

        item = createItem("§r§bAdd x16", Material.SNOW_BALL, add16, 16, true);
        addSwitchersGUI.setItem(24, item);

        item = createItem("§r§bCustom Amount", Material.SIGN, addCustom, 1, true);
        addSwitchersGUI.setItem(40, item);


    }

    private ItemStack createItem(String name, Material mat, List<String> lore, int amt, boolean enchanted) {
        ItemStack item = new ItemStack(mat, amt);
        ItemMeta meta = item.getItemMeta();
        if (enchanted) {
            meta.addEnchant(Enchantment.LUCK,1, false);
        }
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }


    @Override
    public Inventory getInventory() {
        return addSwitchersGUI;
    }
}
