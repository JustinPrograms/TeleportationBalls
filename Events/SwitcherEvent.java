package com.lethalflame.teleportationball.Events;

import com.lethalflame.teleportationball.ItemRelated.ItemManager;
import com.lethalflame.teleportationball.TeleportationBall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;


public class SwitcherEvent implements Listener {
    private final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);




    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball) {
            Snowball s = (Snowball) e.getDamager();


            if (!s.getName().equals(ItemManager.sbc.getItemMeta().getDisplayName())) {
                Bukkit.getConsoleSender().sendMessage(" Not working");
                return;
            }


            if (s.getShooter() instanceof Player && e.getEntity() instanceof Player) {
                Player p = (Player) s.getShooter();
                Player p2 = (Player) e.getEntity();


                e.setCancelled(true);

                Location loc1 = p.getLocation();
                Vector direction1 = p.getLocation().getDirection();
                Location loc2 = p2.getLocation();
                Vector direction2 = p2.getLocation().getDirection();

                double x1 = p.getLocation().getX();
                double y1 = p.getLocation().getY();
                double z1 = p.getLocation().getZ();

                double x2 = p2.getLocation().getX();
                double y2 = p2.getLocation().getY();
                double z2 = p2.getLocation().getZ();

                double xtotal = x2 - x1;
                double xsq = xtotal * xtotal;

                double ytotal = y2 - y1;
                double ysq = ytotal * ytotal;

                double ztotal = z2 - z1;
                double zsq = ztotal * ztotal;

                double totalxyz = xsq + ysq + zsq;
                double distance = Math.sqrt(totalxyz);

                if (distance <= plugin.getConfig().getDouble("sb_range")) {

                    p.teleport(loc2.setDirection(direction2));
                    p2.teleport(loc1.setDirection(direction1));

                    p2.playSound(p2.getLocation(), Sound.SILVERFISH_HIT, 1, 1);

                    p.sendMessage(ChatColor.AQUA + "You have switched places with " + p2.getDisplayName() + "!");
                    p2.sendMessage(ChatColor.AQUA + "You have switched places with " + p.getDisplayName() + "!");
                } else if (distance > plugin.getConfig().getDouble("sb_range")) {
                    p.sendMessage(ChatColor.AQUA + "You are out of range!");
                }


            }


        }
    }


}

