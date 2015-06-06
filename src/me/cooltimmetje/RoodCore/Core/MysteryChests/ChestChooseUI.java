package me.cooltimmetje.RoodCore.Core.MysteryChests;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * This class has been created on 24-5-2015 at 21:23 by cooltimmetje.
 */
public class ChestChooseUI implements CommandExecutor,Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("chests")) {
            int normal = DataClass.normalChests.get(p.getName());
            int epic = DataClass.epicChest.get(p.getName());
            int legend = DataClass.legendChest.get(p.getName());
                openChestChooser(p, normal, epic, legend);
        }
        return true;
    }

    public static void openChestChooser(Player p, int chests, int epic, int legend) {
        Inventory inv = Bukkit.createInventory(null, 36, "Chests");

        if(chests != 0) {
            InventoryUtils.createDisplay(Material.CHEST, chests, 0, "&eNormal Chest &8\u00BB &b&lYou Have: " + chests, "&3Click to open me.", inv, 13);
        } else {
            InventoryUtils.createDisplay(Material.BARRIER, 1, 0, "&eNormal Chest &8\u00BB &b&lYou Have: " + chests, "&3You don't have any normal chests!", inv, 13);
        }
        if(epic != 0){
            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, epic, 0, "&5Epic Chest &8\u00BB &b&lYou Have: " + epic, "&3Click to open me.", inv, 14);
        } else {
            InventoryUtils.createDisplay(Material.BARRIER, 1, 0, "&5Epic Chest &8\u00BB &b&lYou Have: " + epic, "&3You don't have any epic chests!", inv, 14);
        }
        if(legend != 0){
            InventoryUtils.createDisplay(Material.ENDER_CHEST, legend, 0, "&cLegendary Chest &8\u00BB &b&lYou Have: " + legend, "&3Click to open me.", inv, 15);
        } else {
            InventoryUtils.createDisplay(Material.BARRIER, 1, 0, "&cLegendary Chest &8\u00BB &b&lYou Have: " + legend, "&3You don't have any legendary chests!", inv, 15);
        }



        InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&aChest Shop", "&3You get chests every hour,\n" +
                "&3but if you want some more:\n" +
                "&3Buy them here!", inv, 22);
        InventoryUtils.createDisplay(Material.BARRIER, 1, 0, "&c&lCANCEL", "&3Click here to close this menu.", inv, 23);
        InventoryUtils.createDisplay(Material.WORKBENCH, 1, 0, "&aChest Crafting",
                "&3Use chests to craft higher tiered chests, \n&3this means you can get better loot!", inv, 24);

        p.openInventory(inv);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Chests"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta())
            return;

        switch (event.getCurrentItem().getType()){
            case GOLD_NUGGET:
                ChestsShop.openChest(p);
                break;
            case WORKBENCH:
                ChestCrafting.openCraft(p);
                break;
            case CHEST:
                NormalChestOpening.openNormal(p);
                PlayerUtils.removeChest(p, 1, "opened a normal chest");
                Bukkit.getServer().getLogger().info(p.getName() + " is opening a normal chest!");
                break;
            case TRAPPED_CHEST:
                EpicChestOpening.openEpic(p);
                PlayerUtils.removeEpic(p, 1, "opened a epic chest");
                Bukkit.getServer().getLogger().info(p.getName() + " is opening a epic chest!");
                break;
            case ENDER_CHEST:
                LegendChestOpening.openLegend(p);
                PlayerUtils.removeLegend(p, 1, "opened a legendary chest");
                ChatUtils.broadcastTag("Chests", p.getDisplayName() + " &bis going to open a &c&lLegendary MAND&b! &aWish them luck!");
                Bukkit.getServer().getLogger().info(p.getName() + " is opening a legendary chest!");
                break;
            case BARRIER:
                if(event.getSlot() == 22){
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CLICK, 100, 1);
                    break;
                } else {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 100, 1);
                    break;
                }
            default:
                break;
        }
    }

}
