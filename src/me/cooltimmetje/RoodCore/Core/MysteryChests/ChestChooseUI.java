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
        int chests = DataClass.normalChests.get(p.getName());
        if (cmd.getName().equalsIgnoreCase("chests")) {
            if(chests != 0){
                openChestChooser(p, chests);
            } else {
                ChatUtils.msgPlayerTag(p, "Chests", ChatUtils.error + "You need at least 1 chest to open one!");
            }
        }
        return true;
    }

    private void openChestChooser(Player p, int chests) {
        Inventory inv = Bukkit.createInventory(null, 54, "Chests");

        int slot = 1;
        for(int i = 0; i < chests; i++){
            InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&eNormal Chest", "&3Click to open me.", inv, slot);

            slot++;
        }

        InventoryUtils.createDisplay(Material.WORKBENCH, 1, 0, "&eChest Crafting &3(Coming Soon)",
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
            case WORKBENCH:
                ChatUtils.msgPlayerTag(p, "Chests", "Chest crafting is coming soon!");
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 100, 1);
                break;
            case CHEST:
                ChatUtils.msgPlayerTodo(p, "Add chest opening GUI");
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 100, 1);
                break;
            default:
                break;
        }
    }

}
