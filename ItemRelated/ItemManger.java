package com.lethalflame.teleportationball.ItemRelated;

import com.lethalflame.teleportationball.TeleportationBall;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack sbc;
    public static void init() {
        createSwitcher();
    }


    private static void createSwitcher() {
        final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);


        ItemStack item = new ItemStack(Material.SNOW_BALL, 1);
        ItemMeta meta = item.getItemMeta();
        double doubleRange = plugin.getConfig().getDouble("sb_range");
        int range = (int) doubleRange;
        int cooldown = (int) plugin.getConfig().getDouble("sb_cooldown");

        List<String> defaultLore = new ArrayList<>();
        defaultLore.add("ยง7Swap position  with any");
        defaultLore.add("ยง7enemy within a " + range + " radius!");

        List<String> customLore = new ArrayList<>();


        if (!(plugin.getConfig().getString("sb_lore1") == null && !plugin.getConfig().getBoolean("sb_defaultLore"))) {
            String customloreBefore = (plugin.getConfig().getString("sb_lore1"));
            String customloreReplaceRange = customloreBefore.replace("{range}", String.valueOf(range));
            String customloreReplacefinal1 = customloreReplaceRange.replace("{cooldown}", String.valueOf(cooldown));

            customLore.add(customloreReplacefinal1);
        }

        if (!(plugin.getConfig().getString("sb_lore2") == null && !plugin.getConfig().getBoolean("sb_defaultLore"))) {
            String customloreBefore = (plugin.getConfig().getString("sb_lore2"));
            String customloreReplaceRange = customloreBefore.replace("{range}", String.valueOf(range));
            String customloreReplacefinal1 = customloreReplaceRange.replace("{cooldown}", String.valueOf(cooldown));

            customLore.add(customloreReplacefinal1);
        }
        if (!(plugin.getConfig().getString("sb_lore3") == null && !plugin.getConfig().getBoolean("sb_defaultLore"))) {
            String customloreBefore = (plugin.getConfig().getString("sb_lore3"));
            String customloreReplaceRange = customloreBefore.replace("{range}", String.valueOf(range));
            String customloreReplacefinal1 = customloreReplaceRange.replace("{cooldown}", String.valueOf(cooldown));

            customLore.add(customloreReplacefinal1);
        }


        if (!(plugin.getConfig().getString("sb_name") == null && !plugin.getConfig().getBoolean("sb_defaultLore"))) {
            meta.setDisplayName(plugin.getConfig().getString("sb_name"));
        }

        if (plugin.getConfig().getBoolean("sb_defaultLore")) {
            meta.setLore(defaultLore);
        }

        if (!plugin.getConfig().getBoolean("sb_defaultLore")) {
            meta.setLore(customLore);
        }


        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        item.setItemMeta(meta);
        sbc = item;


    }

}
