package me.cooltimmetje.RoodCore.Core.MysteryChests;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
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
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * This class has been created on 24-5-2015 at 21:23 by cooltimmetje.
 */
public class ChestChooseUI implements CommandExecutor,Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        int normal = DataClass.normalChests.get(p.getName());
        int epic = DataClass.epicChest.get(p.getName());
        int legend = DataClass.legendChest.get(p.getName());
        if (cmd.getName().equalsIgnoreCase("chests")) {
            openChestChooser(p, normal, epic, legend);
        }
        return true;
    }

    private void openChestChooser(Player p, int chests, int epic, int legend) {
        Inventory inv = Bukkit.createInventory(null, 54, "Chests");

        int slot = 1;
        for(int i = 0; i < chests; i++){
            InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&eNormal Chest", "&3Click to open me.", inv, slot);

            slot++;
        }
        for(int i = 0; i < epic; i++){
            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&5Epic Chest", "&3Click to open me.", inv, slot);

            slot++;
        }
        for(int i = 0; i < legend; i++){
            InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&cLegendary Chest", "&3Click to open me.", inv, slot);

            slot++;
        }

        InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&aChest Shop &3(Coming soon)", "&3You get chests every hour,\n" +
                "&3but if you want some more:\n" +
                "&3Buy them here!", inv, 53);
        InventoryUtils.createDisplay(Material.WORKBENCH, 1, 0, "&aChest Crafting",
                "&3Use chests to craft higher tiered chests, \n&3this means you can get better loot!", inv, 54);

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
                ChatUtils.msgPlayerTag(p, "Chests", "Chest shop is coming soon!");
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 100, 1);
                break;
            case WORKBENCH:
                ChestCrafting.openCraft(p);
                break;
            case CHEST:
                NormalChestOpening.openNormal(p);
                int chest = DataClass.normalChests.get(p.getName());
                DataClass.normalChests.put(p.getName(), chest - 1);
                break;
            default:
                break;
        }
    }

}
