package me.cooltimmetje.RoodCore.Core.MysteryChests;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import javax.xml.crypto.Data;

/**
 * This class has been created on 30-5-2015 at 20:41 by cooltimmetje.
 */
public class ChestsShop implements Listener {

    public static void openChest(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Chest Shop");

        int tokens = DataClass.tokens.get(p.getName());
        int normal = DataClass.normalChests.get(p.getName());
        int epic = DataClass.epicChest.get(p.getName());
        int legend = DataClass.legendChest.get(p.getName());

        InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&aYou have &9&l" + tokens + " tokens!", null, inv, 5);
        InventoryUtils.createDisplay(Material.CHEST, normal, 0, "&aYou have &e&l" + normal + " Normal Chests!", null, inv, 13);
        InventoryUtils.createDisplay(Material.TRAPPED_CHEST, epic, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 14);
        InventoryUtils.createDisplay(Material.ENDER_CHEST, legend, 0, "&aYou have &c&l" + legend + " Legendary Chests!", null, inv, 15);

        InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&aBuy: &e&l1 Normal Chest", "&3Cost: &9&l" + DataClass.chestCost + " tokens\n" +
                "&3Click to buy", inv, 22);
        InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aBuy: &5&l1 Epic Chest", "&3Cost: &9&l" + DataClass.chestCost*3 + " tokens\n" +
                "&3Click to buy", inv, 23);
        InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&aBuy: &c&l1 Legendary Chest!", "&3Cost: &9&l" + DataClass.chestCost*12 + " tokens\n" +
                "&3Click to buy", inv, 24);

        InventoryUtils.createDisplay(Material.BARRIER, 1, 0, "&aGo Back", null, inv, 19);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Chest Shop"))
            return;

        Player p = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        int slot = event.getSlot();
        int tokens = DataClass.tokens.get(p.getName());
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta())
            return;


        switch (event.getCurrentItem().getType()){
            case CHEST:
                if(slot != 12){
                    if(slot == 21){
                        if(tokens >= DataClass.chestCost){
                            PlayerUtils.removeTokens(p, DataClass.chestCost, "bought 1 normal chest");
                            PlayerUtils.addChest(p, 1, "bought 1 normal chest");

                            tokens = DataClass.tokens.get(p.getName());
                            int normal = DataClass.normalChests.get(p.getName());

                            InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&aYou have &9&l" + tokens + " tokens!", null, inv, 5);
                            InventoryUtils.createDisplay(Material.CHEST, normal, 0, "&aYou have &e&l" + normal + " Normal Chests!", null, inv, 13);
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case TRAPPED_CHEST:
                if(slot != 13){
                    if(slot == 22) {
                        if (tokens >= DataClass.chestCost*3) {
                            PlayerUtils.removeTokens(p, DataClass.chestCost*3, "bought 1 epic chest");
                            PlayerUtils.addEpic(p, 1, "bought 1 epic chest");

                            tokens = DataClass.tokens.get(p.getName());
                            int epic = DataClass.epicChest.get(p.getName());

                            InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&aYou have &9&l" + tokens + " tokens!", null, inv, 5);
                            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, epic, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 14);
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case ENDER_CHEST:
                if(slot != 14){
                    if(slot == 23){
                        if(tokens >= DataClass.chestCost*12){
                            PlayerUtils.removeTokens(p, DataClass.chestCost*12, "bought 1 legendary chest");
                            PlayerUtils.addLegend(p, 1, "bought 1 legendary chest");

                            tokens = DataClass.tokens.get(p.getName());
                            int legend = DataClass.legendChest.get(p.getName());

                            InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&aYou have &9&l" + tokens + " tokens!", null, inv, 5);
                            InventoryUtils.createDisplay(Material.ENDER_CHEST, legend, 0, "&aYou have &c&l" + legend + " Legendary Chests!", null, inv, 15);
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case BARRIER:
                ChestChooseUI.openChestChooser(p, DataClass.normalChests.get(p.getName()), DataClass.epicChest.get(p.getName()), DataClass.legendChest.get(p.getName()));
                p.playSound(p.getLocation(), Sound.CLICK, 100, 1);
                break;
            default:
                break;
        }
    }

}
