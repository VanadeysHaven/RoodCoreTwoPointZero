package me.cooltimmetje.RoodCore.Core.ArmorStandCustomization;

import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 31-5-2015 at 18:48 by cooltimmetje.
 */
public class Contents implements Listener {

    public static void openContents(Player p) {
        ArmorStand as = MainMenu.pAs.get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 27, "ArmorStand Inventory");

        InventoryUtils.createDisplay(Material.DIAMOND_SWORD, 1, 0, "&aHand", "&3Put in this slot what the \n&3ArmorStand should hold in its hand.", inv, 12);
        InventoryUtils.createDisplay(Material.DIAMOND_HELMET, 1, 0, "&aHelmet", "&3Put in this slot what the \n&3ArmorStand should wear on its head.", inv, 13);
        InventoryUtils.createDisplay(Material.DIAMOND_CHESTPLATE, 1, 0, "&aChestplate", "&3Put in this slot what the \n&3ArmorStand should wear on its chest.", inv, 14);
        InventoryUtils.createDisplay(Material.DIAMOND_LEGGINGS, 1, 0, "&aLeggings", "&3Put in this slot what the \n&3ArmorStand should wear on its legs.", inv, 15);
        InventoryUtils.createDisplay(Material.DIAMOND_BOOTS, 1, 0, "&aBoots", "&3Put in this slot what the \n&3ArmorStand should wear on its feet.", inv, 16);

        if(as.getItemInHand().getType() != Material.AIR) {
            InventoryUtils.createDisplay(as.getItemInHand(), inv, 12);
        }
        if(as.getHelmet().getType() != Material.AIR){
            InventoryUtils.createDisplay(as.getHelmet(), inv, 13);
        }
        if(as.getChestplate().getType() != Material.AIR){
            InventoryUtils.createDisplay(as.getChestplate(), inv, 14);
        }
        if(as.getLeggings().getType() != Material.AIR){
            InventoryUtils.createDisplay(as.getLeggings(), inv, 15);
        }
        if(as.getBoots().getType() != Material.AIR) {
            InventoryUtils.createDisplay(as.getBoots(), inv, 16);
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("ArmorStand Inventory"))
            return;

        if(event.getSlot() == -999)
            return;

        if(event.getClickedInventory().getType() == InventoryType.PLAYER){
            return;
        }

        Player p = (Player) event.getWhoClicked();

        ArmorStand as = MainMenu.pAs.get(p.getName());
        Inventory inv = event.getInventory();
        switch (event.getSlot()){
            case 11:
                if(p.getItemOnCursor().getItemMeta().hasLore()) {
                    as.setItemInHand(event.getInventory().getItem(11));
                    p.setItemOnCursor(null);
                } else {
                    InventoryUtils.createDisplay(Material.DIAMOND_SWORD, 1, 0, "&aHand", "&3Put in this slot what the \n&3ArmorStand should hold in its hand.", inv, 12);
                    as.setItemInHand(null);
                }
                break;
            case 12:
                if(p.getItemOnCursor().getItemMeta().hasLore()) {
                    as.setHelmet(event.getInventory().getItem(12));
                    p.setItemOnCursor(null);
                } else {
                    InventoryUtils.createDisplay(Material.DIAMOND_HELMET, 1, 0, "&aHelmet", "&3Put in this slot what the \n&3ArmorStand should wear on its head.", inv, 13);
                    as.setHelmet(null);
                }
                break;
            case 13:
                if(p.getItemOnCursor().getItemMeta().hasLore()) {
                    as.setChestplate(event.getInventory().getItem(13));
                    p.setItemOnCursor(null);
                } else {
                    InventoryUtils.createDisplay(Material.DIAMOND_CHESTPLATE, 1, 0, "&aChestplate", "&3Put in this slot what the \n&3ArmorStand should wear on its chest.", inv, 14);
                    as.setChestplate(null);
                }
                break;
            case 14:
                if(p.getItemOnCursor().getItemMeta().hasLore()) {
                    as.setLeggings(event.getInventory().getItem(14));
                    p.setItemOnCursor(null);
                } else {
                    InventoryUtils.createDisplay(Material.DIAMOND_LEGGINGS, 1, 0, "&aLeggings", "&3Put in this slot what the \n&3ArmorStand should wear on its legs.", inv, 15);
                    as.setLeggings(null);
                }
                break;
            case 15:
                if(p.getItemOnCursor().getItemMeta().hasLore()) {
                    as.setBoots(event.getInventory().getItem(15));
                    p.setItemOnCursor(null);
                } else {
                    InventoryUtils.createDisplay(Material.DIAMOND_BOOTS, 1, 0, "&aBoots", "&3Put in this slot what the \n&3ArmorStand should wear on its feet.", inv, 16);
                    as.setBoots(null);
                }
                break;
            default:
                event.setCancelled(true);
                break;
        }
    }
}
