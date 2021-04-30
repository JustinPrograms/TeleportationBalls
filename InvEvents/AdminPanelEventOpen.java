package com.lethalflame.teleportationball.InvEvents;

import com.lethalflame.teleportationball.Guis.AdminGUI;
import com.lethalflame.teleportationball.Guis.addSwitchersGUI;
import com.lethalflame.teleportationball.ItemRelated.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class AdminPanelEventOpen implements Listener {

    Map<String, Long> giveCooldownx1 = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent e) {



        if (e.getClickedInventory() == null) {
            return;
        }


        if (e.getClickedInventory().getHolder() instanceof AdminGUI) {
            e.setCancelled(true);





            Player player = (Player) e.getWhoClicked();

            if (e.getCurrentItem() == null) {
                return;
            }



            if (e.getSlot() == 0) {
                String playername = e.getWhoClicked().getName();

                if (e.getWhoClicked().hasPermission("sb.admin")) {
                    if (e.isLeftClick()) {
                        if (giveCooldownx1.containsKey(player.getName())) {
                            if (giveCooldownx1.get(playername) > System.currentTimeMillis()) {
                                long timeLeft = ((giveCooldownx1.get(playername) - System.currentTimeMillis()) / 1000);

                                player.sendMessage(ChatColor.LIGHT_PURPLE + "You are currently on cooldown for " + timeLeft + " seconds!");
                                return;



                            }
                        }


                        player.getInventory().addItem(ItemManager.sbc);

                        giveCooldownx1.put(playername, System.currentTimeMillis() + (1 * 1000));

                        player.sendMessage(ChatColor.AQUA + "Added x1 Switcher Ball to your inventory!");
                        return;







                    }
                    if (e.isRightClick()) {

                        addSwitchersGUI gui = new addSwitchersGUI();
                        player.openInventory(gui.getInventory());
                    }
                    if (!(e.isRightClick() || e.isLeftClick())) {
                        player.closeInventory();
                        return;
                    }
                    if (e.isShiftClick()) {
                        player.closeInventory();
                        return;
                    }
                }
            }


            if (e.isRightClick()) {
                addSwitchersGUI gui = new addSwitchersGUI();
                player.openInventory(gui.getInventory());
            }
            if (!(e.isRightClick() || e.isLeftClick())) {
                player.closeInventory();
                return;
            }
            if (e.isShiftClick()) {
                player.closeInventory();
                return;
            }


        }


    }

}
