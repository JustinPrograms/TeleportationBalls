package com.lethalflame.teleportationball.InvEvents;

import com.lethalflame.teleportationball.Guis.addSwitchersGUI;
import com.lethalflame.teleportationball.ItemRelated.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class AddSwitcherPanelEventOpen implements Listener {

    Map<String, Long> giveCooldownx4 = new HashMap<>();
    Map<String, Long> giveCooldownx8 = new HashMap<>();
    Map<String, Long> giveCooldownx16 = new HashMap<>();
    Map<String, Long> customAmount = new HashMap<>();



    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getClickedInventory() == null) {
            return;
        }

        if (e.getClickedInventory().getHolder() instanceof addSwitchersGUI) {

            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            if (!(e.getWhoClicked().hasPermission("sb.give") || e.getWhoClicked().hasPermission("sb.admin"))) {
                player.sendMessage(ChatColor.AQUA + "Don't know how you get here but you don't have permission!");
                player.closeInventory();
                return;
            }

            if (e.getCurrentItem() == null) {
                return;
            }

            if (e.isShiftClick()){
                e.setCancelled(true);
            }




            // Adds x4 Switchers to Inventory
            if (e.getSlot() == 20) {
                if (giveCooldownx4.containsKey(player.getName())) {
                    if (giveCooldownx4.get(e.getWhoClicked().getName()) > System.currentTimeMillis()) {
                        long timeLeft = ((giveCooldownx4.get(e.getWhoClicked().getName()) - System.currentTimeMillis()) / 1000);

                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You are currently on cooldown for " + timeLeft + " seconds!");
                        return;



                    }
                }
                int amount = 4;
                for (int i = 0; i < amount; i++) {
                    player.getInventory().addItem(ItemManager.sbc);
                }
                player.sendMessage(ChatColor.AQUA + "x" + amount + " Switcher Balls added to inventory.");
                giveCooldownx4.put(player.getName(), System.currentTimeMillis() + (1 * 1000));

            }

            // Adds x8 Switchers to Inventory
            if (e.getSlot() == 22) {
                if (giveCooldownx8.containsKey(player.getName())) {
                    if (giveCooldownx8.get(e.getWhoClicked().getName()) > System.currentTimeMillis()) {
                        long timeLeft = ((giveCooldownx8.get(e.getWhoClicked().getName()) - System.currentTimeMillis()) / 1000);

                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You are currently on cooldown for " + timeLeft + " seconds!");
                        return;



                    }
                }
                int amount = 8;
                for (int i = 0; i < amount; i++) {
                    player.getInventory().addItem(ItemManager.sbc);
                }
                player.sendMessage(ChatColor.AQUA + "x" + amount + " Switcher Balls added to inventory.");
                giveCooldownx8.put(player.getName(), System.currentTimeMillis() + (1 * 1000));


            }


            // Adds x16 Switchers to Inventory
            if (e.getSlot() == 24) {
                if (giveCooldownx16.containsKey(player.getName())) {
                    if (giveCooldownx16.get(e.getWhoClicked().getName()) > System.currentTimeMillis()) {
                        long timeLeft = ((giveCooldownx16.get(e.getWhoClicked().getName()) - System.currentTimeMillis()) / 1000);

                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You are currently on cooldown for " + timeLeft + " seconds!");
                        return;



                    }
                }
                int amount = 16;
                for (int i = 0; i < amount; i++) {
                    player.getInventory().addItem(ItemManager.sbc);
                    giveCooldownx16.put(player.getName(), System.currentTimeMillis() + (1 * 1000));
                }
                player.sendMessage(ChatColor.AQUA + "x" + amount + " Switcher Balls added to inventory.");

            }


            // Adds Custom Amount of Switchers to Inventory
            if (e.getSlot() == 40) {
                player.sendMessage(ChatColor.AQUA + "Please enter a whole number:");
                customAmount.put(player.getName(), System.currentTimeMillis() + (10 * 1000));
                player.closeInventory();


            }
        }


    }


    @EventHandler
    private void onMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (customAmount.containsKey(player.getName())) {
            if (customAmount.get(player.getName()) > System.currentTimeMillis()) {
                event.setCancelled(true);
                if (event.getMessage().contains("-")) {
                    player.sendMessage(ChatColor.RED + "Error: You did not enter a positive number.");
                    player.sendMessage(ChatColor.RED + "Canceling");
                    customAmount.remove(player.getName());
                    return;
                }

                try {
                    int customAMT = Integer.parseInt(event.getMessage());
                    player.sendMessage(ChatColor.AQUA + "Added " + "x" + customAMT + " switcher balls to your inventory.");
                    for (int i = 0; i < customAMT; i++) {
                        player.getInventory().addItem(ItemManager.sbc);
                        customAmount.remove(player.getName());
                    }
                }
                catch(Exception e) {
                    player.sendMessage(ChatColor.RED + "Error: You did not enter a number.");
                    player.sendMessage(ChatColor.RED + "Canceling");
                    customAmount.remove(player.getName());
                    return;
                }
            }

        }
        return;

    }

}
