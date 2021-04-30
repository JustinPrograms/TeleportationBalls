package com.lethalflame.teleportationball;

import com.lethalflame.teleportationball.Commands.SwitcherCommand;
import com.lethalflame.teleportationball.Events.ProjectileLaunchEvent;
import com.lethalflame.teleportationball.Events.SwitcherEvent;
import com.lethalflame.teleportationball.InvEvents.AddSwitcherPanelEventOpen;
import com.lethalflame.teleportationball.InvEvents.AdminPanelEventOpen;
import com.lethalflame.teleportationball.InvEvents.ConfigEventOpen;
import com.lethalflame.teleportationball.ItemRelated.ItemManager;
import com.lethalflame.teleportationball.ItemRelated.ItemPickUp;
import org.bukkit.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

public class TeleportationBall extends JavaPlugin {


    @Override
    public void onEnable() {

        // Loads Default Config
        this.saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[Teleportation Ball]: Config Loaded");

        // ItemRelated
        ItemManager.init();
        getServer().getPluginManager().registerEvents(new ItemPickUp(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[Teleportation Ball]: Items Loaded");

        // Commands
        getCommand("sb").setExecutor(new SwitcherCommand());
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[Teleportation Ball]: Commands Loaded");

        // Events
        getServer().getPluginManager().registerEvents(new SwitcherEvent(), this);
        getServer().getPluginManager().registerEvents(new ProjectileLaunchEvent(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[Teleportation Ball]: Stage 1 Events Loaded");


        // InvEvents
        getServer().getPluginManager().registerEvents(new ConfigEventOpen(), this);
        getServer().getPluginManager().registerEvents(new AdminPanelEventOpen(), this);
        getServer().getPluginManager().registerEvents(new AddSwitcherPanelEventOpen(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[Teleportation Ball]: Stages 2 Events Loaded");

        // Enabled Message
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Teleportation Ball]: Plugin is enabled! - Beta");

    }


    @Override


    // Disabled Message
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Teleportation Ball]: Plugin is disabled! - Beta");
    }

}
