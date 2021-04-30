package com.lethalflame.teleportationball.InvEvents;

import com.lethalflame.teleportationball.Guis.AdminGUI;
import com.lethalflame.teleportationball.Guis.AdminInv;
import com.lethalflame.teleportationball.Guis.ConfigInv;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigEventOpen implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getClickedInventory() == null) {
            return;
        }


        // Normal Config GUI
        if (e.getClickedInventory().getHolder() instanceof ConfigInv) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            if (e.getCurrentItem() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getSlot() == 4) {
                e.setCancelled(true);
                player.closeInventory();
                return;

            }
            if (e.getSlot() == 8) {
                e.setCancelled(true);
                player.closeInventory();
            }
        }


        // Admin Config GUI
        if (e.getClickedInventory().getHolder() instanceof AdminInv) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getSlot() == 4) {
                player.closeInventory();
                return;

            }
            if (e.getSlot() == 8) {
                player.closeInventory();
                return;
            }
            if (e.getSlot() == 0) {
                // Opens Admin Panel
                AdminGUI gui = new AdminGUI();
                player.openInventory(gui.getInventory());

                player.sendMessage(ChatColor.DARK_RED + "§oOpened!");
            }

        }



    }

}
