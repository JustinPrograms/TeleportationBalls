package com.lethalflame.teleportationball.Commands;

import com.lethalflame.teleportationball.Guis.AdminInv;
import com.lethalflame.teleportationball.Guis.ConfigInv;
import com.lethalflame.teleportationball.Guis.addSwitchersGUI;
import com.lethalflame.teleportationball.TeleportationBall;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class SwitcherCommand implements CommandExecutor {
    private final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String pluginName = plugin.getName();




        ArrayList<String> cmdList = new ArrayList<>();
        cmdList.add("help");
        cmdList.add("setrange");
        cmdList.add("setcooldown");
        cmdList.add("give");
        cmdList.add("config");
        cmdList.add("reload");









        if (args.length == 0) {
            if (!(sender.hasPermission("sb.help")) || (!(sender.hasPermission("sb.admin")))) {
                sender.sendMessage(ChatColor.RED + "Missing permission:" + " sb.help");
                return true;
            }
            Player player = (Player) sender;

            sendHelpMessage((Player) sender);
            player.sendMessage("Sent");


            return true;

        }


        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can run this command!");
                return true;
            }
            if (!(cmdList.contains(args[0]))) {
                sendHelpMessage((Player) sender);
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!(sender.hasPermission("sb.reload")) || (!(sender.hasPermission("sb.admin")))) {
                    sender.sendMessage(ChatColor.RED + "Missing permission:" + " sb.reload");
                    return true;
                }
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config Reloaded");

                Plugin pl = Bukkit.getPluginManager().getPlugin(pluginName);

                Bukkit.getPluginManager().disablePlugin(pl);
                Bukkit.getPluginManager().enablePlugin(pl);
                sender.sendMessage(ChatColor.GREEN + "Plugin Reloaded");
                return true;

            }

            if (args[0].equalsIgnoreCase("give")) {
                if (!(sender.hasPermission("sb.give")) || (!(sender.hasPermission("sb.admin")))) {
                    sender.sendMessage(ChatColor.RED + "Missing permission:" + " sb.give");
                    return true;
                }
                addSwitchersGUI gui = new addSwitchersGUI();
                ((Player) sender).openInventory(gui.getInventory());

            }

            if (args[0].equalsIgnoreCase("config")) {
                if (!(sender.hasPermission("sb.admin"))) {
                    Player player = (Player) sender;
                    ConfigInv gui = new ConfigInv();
                    player.openInventory(gui.getInventory());
                    player.sendMessage(ChatColor.AQUA + "§oOpened!");
                }
                else{
                    Player player = (Player) sender;
                    AdminInv gui = new AdminInv();
                    player.openInventory(gui.getInventory());
                    player.sendMessage(ChatColor.AQUA + "§oOpened!");
                }
            }

            if (args[0].equalsIgnoreCase("help")) {
                if (!(sender.hasPermission("sb.help")) || (!(sender.hasPermission("sb.admin")))) {
                    sender.sendMessage(ChatColor.RED + "Missing permission:" + " sb.help");
                    return true;
                }

                sendHelpMessage((Player) sender);

                return true;



            }
        }


        if (args.length == 2) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can run this command!");
                return true;
            }
            if (args[0].equalsIgnoreCase("setrange")) {
                if (!(sender.hasPermission("sb.admin"))) {
                    sender.sendMessage(ChatColor.RED + "Missing permission:" + " sb.admin");
                    return true;
                }
                String newrangestr = args[1];
                try {
                    double newrange = Double.parseDouble(newrangestr);
                    plugin.getConfig().set("sb_range", newrange);
                    plugin.saveConfig();

                    Plugin pl = Bukkit.getPluginManager().getPlugin(pluginName);

                    Bukkit.getPluginManager().disablePlugin(pl);
                    Bukkit.getPluginManager().enablePlugin(pl);

                    sender.sendMessage(ChatColor.GOLD + "New Switcher Ball range set to " + newrangestr);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "You must have a number!");
                    return true;
                }

            }

            if (args[0].equalsIgnoreCase("setcooldown")) {
                if (!(sender.hasPermission("sb.admin"))) {
                    sender.sendMessage(ChatColor.RED + "Missing permission:" + " sb.admin");
                    return true;
                }
                String newCooldownSTR = args[1];
                try {
                    double newCooldown = Double.parseDouble(newCooldownSTR);
                    plugin.getConfig().set("sb_cooldown", newCooldown);
                    plugin.saveConfig();

                    Plugin pl = Bukkit.getPluginManager().getPlugin(pluginName);

                    Bukkit.getPluginManager().disablePlugin(pl);
                    Bukkit.getPluginManager().enablePlugin(pl);

                    sender.sendMessage(ChatColor.GOLD + "New Switcher Ball cooldown set to " + newCooldown);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "You must have a number!");
                    return true;
                }

            }

        }


        return true;

    }

    private void sendHelpMessage(Player player) {
        final Plugin plugin = TeleportationBall.getPlugin(TeleportationBall.class);
        final String pluginVersion = TeleportationBall.getPlugin(TeleportationBall.class).getDescription().getVersion();
        final var pluginAuthor = TeleportationBall.getPlugin(TeleportationBall.class).getDescription().getAuthors();

        TextComponent topHelpMsg = new TextComponent("\n" + ChatColor.GOLD + "Switcher Ball Help: Version: " + ChatColor.WHITE + " - " + ChatColor.AQUA + pluginVersion + ChatColor.GOLD + " By: " + ChatColor.AQUA + pluginAuthor);
        topHelpMsg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/switcher-balls.89993/"));
        topHelpMsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to view resource").color(ChatColor.AQUA).create()));

        TextComponent helpMsg1 = new TextComponent("\n" +  ChatColor.GOLD + "§l/sb setrange {range}§r" + ChatColor.WHITE + " - " + ChatColor.AQUA + "Set how far a switcher can be thrown.");
        helpMsg1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sb setrange "));
        helpMsg1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/sb setrange").color(ChatColor.AQUA).create()));

        TextComponent helpMsg2 = new TextComponent(ChatColor.GOLD + "/sb setcooldown {cooldown}§r" + ChatColor.WHITE + " - " + ChatColor.AQUA + "Set the cooldown of the switcher");
        helpMsg2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sb setcooldown "));
        helpMsg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/sb setcooldown").color(ChatColor.AQUA).create()));

        TextComponent helpMsg3 = new TextComponent(ChatColor.GOLD + "§l/sb give§r" + ChatColor.WHITE + " - " + ChatColor.AQUA + "Opens add switch gui.");
        helpMsg3.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sb give "));
        helpMsg3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/sb give").color(ChatColor.AQUA).create()));

        TextComponent helpMsg4 = new TextComponent(ChatColor.GOLD + "§l/sb config§r" + ChatColor.WHITE + " - " + ChatColor.AQUA + "Shows config settings.");
        helpMsg4.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sb config"));
        helpMsg4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/sb config").color(ChatColor.AQUA).create()));

        TextComponent helpMsg5 = new TextComponent(ChatColor.GOLD + "§l/sb reload§r" + ChatColor.WHITE + " - " + ChatColor.AQUA + "Reloads plugin & config.");
        helpMsg5.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sb reload"));
        helpMsg5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/sb reload").color(ChatColor.AQUA).create()));

        player.spigot().sendMessage(topHelpMsg);
        player.spigot().sendMessage(helpMsg1);
        player.spigot().sendMessage(helpMsg2);
        player.spigot().sendMessage(helpMsg3);
        player.spigot().sendMessage(helpMsg4);
        player.spigot().sendMessage(helpMsg5);
        player.sendMessage(ChatColor.AQUA + "\nPage 1/1");


        return;
    }


}
