package com.lethalflame.teleportationball.ItemRelated;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemPickUp implements Listener {

    @EventHandler
    public void OnItemPickup(PlayerPickupItemEvent e) {
        if (e.getItem().getItemStack().getType() == Material.SNOW_BALL) {
            ItemStack item = e.getItem().getItemStack();
            ItemMeta itemMeta = item.getItemMeta();

            if (!item.hasItemMeta()) return;

            if (itemMeta.getItemFlags().contains(ItemFlag.HIDE_POTION_EFFECTS)) {
                if (itemMeta.getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)) {
                    if (itemMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS)) {

                        ItemMeta newMeta = ItemManager.sbc.getItemMeta();

                        item.setItemMeta(newMeta);
                    }
                }


            }


        }

    }
}
