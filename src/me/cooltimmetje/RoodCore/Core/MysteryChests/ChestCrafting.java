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

/**
 * This class has been created on 30-5-2015 at 19:50 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class ChestCrafting implements Listener {

    public static void openCraft(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Chest Crafting");

        int normal = DataClass.normalChests.get(p.getName());
        int epic = DataClass.epicChest.get(p.getName());
        int legend = DataClass.legendChest.get(p.getName());

        InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&aYou have &e&l" + normal + " Normal Chests!", null, inv, 4);
        InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 5);
        InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&aYou have &c&l" + legend + " Legendary Chests!", null, inv, 6);

        InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aCraft: &5&l1 Epic Chest", "&3Cost: &e&l4 Normal Chests\n" +
                "&3Click to craft", inv, 14);
        InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&aCraft: &c&l1 Legendary Chest", "&3Cost: &5&l4 Epic Chests\n" +
                "&3Click to craft", inv, 15);

        InventoryUtils.createDisplay(Material.CHEST, 4, 0, "&aCraft: &e&l4 Normal Chests", "&3Cost: &5&l1 Epic Chest\n" +
                "&3Click to craft", inv, 22);
        InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 4, 0, "&aCraft: &5&l4 Epic Chests", "&3Cost: &c&l1 Legendary Chest\n" +
                "&3Click to craft", inv, 23);

        p.openInventory(inv);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Chest Crafting"))
            return;

        Player p = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        int slot = event.getSlot();
        int normal = DataClass.normalChests.get(p.getName());
        int epic = DataClass.epicChest.get(p.getName());
        int legend = DataClass.legendChest.get(p.getName());
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta())
            return;


        switch (event.getCurrentItem().getType()){
            case CHEST:
                if(slot != 3){
                    if(slot == 21){
                        if(epic >= 1){
                            PlayerUtils.removeEpic(p, 1, "crafted 4 normal chests");
                            PlayerUtils.addChest(p, 4, "crafted 4 normal chests");

                            normal = DataClass.normalChests.get(p.getName());
                            epic = DataClass.epicChest.get(p.getName());

                            InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&aYou have &e&l" + normal + " Normal Chests!", null, inv, 4);
                            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 5);
                            p.playSound(p.getLocation(), Sound.ANVIL_USE, 100, 1);
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
                if(slot != 4){
                    if(slot == 13){
                        if(normal >= 4) {
                            PlayerUtils.removeChest(p, 4, "crafted 1 epic chest");
                            PlayerUtils.addEpic(p, 1, "crafted 1 epic chest");

                            normal = DataClass.normalChests.get(p.getName());
                            epic = DataClass.epicChest.get(p.getName());

                            InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&aYou have &e&l" + normal + " Normal Chests!", null, inv, 4);
                            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 5);
                            p.playSound(p.getLocation(), Sound.ANVIL_USE, 100, 1);
                        } else {
                            break;
                        }
                    } else if (slot == 22){
                        if(legend >= 1) {
                            PlayerUtils.removeLegend(p, 1, "crafted 4 epic chests");
                            PlayerUtils.addEpic(p, 4, "crafted 4 epic chests");

                            epic = DataClass.epicChest.get(p.getName());
                            legend = DataClass.legendChest.get(p.getName());

                            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 5);
                            InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&aYou have &c&l" + legend + " Legendary Chests!", null, inv, 6);
                            p.playSound(p.getLocation(), Sound.ANVIL_USE, 100, 1);
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
                if(slot != 5){
                    if(slot == 14){
                        if(epic >= 4){
                            PlayerUtils.removeEpic(p, 4, "crafted 1 legendary chest");
                            PlayerUtils.addLegend(p, 1, "crafted 1 legendary chest");

                            epic = DataClass.epicChest.get(p.getName());
                            legend = DataClass.legendChest.get(p.getName());

                            InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&aYou have &5&l" + epic + " Epic Chests!", null, inv, 5);
                            InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&aYou have &c&l" + legend + " Legendary Chests!", null, inv, 6);
                            p.playSound(p.getLocation(), Sound.ANVIL_USE, 100, 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            default:
                break;
        }
    }


}
