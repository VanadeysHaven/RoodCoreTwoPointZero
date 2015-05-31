package me.cooltimmetje.RoodCore.Utilities;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * This class has been created on 15-5-2015 at 15:10 by cooltimmetje.
 */
public class InventoryUtils {

    public static void createDisplay(Material m, int amount, int data, String name, String lore, Inventory inv, int slot){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        if(!(lore == null)){
            ArrayList<String> Lore = new ArrayList<>();
            String[] loreArray = lore.split("\n");
            for(int i = 0; i < loreArray.length; i++) {
                Lore.add(MiscUtils.color(loreArray[i]));
            }
            itemMeta.setLore(Lore);
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slot - 1, item);
    }

    public static void createTrueFalse(boolean bool, String name, String lore, Inventory inv, int slot){
        int data;
        if(bool){
            data = 10;
        } else {
            data = 8;
        }

        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(MiscUtils.color(name + " &8\u00BB " + MiscUtils.formatBoolean(bool)));
        }
        if(!(lore == null)){
            ArrayList<String> Lore = new ArrayList<>();
            String[] loreArray = lore.split("\n");
            for(int i = 0; i < loreArray.length; i++) {
                Lore.add(MiscUtils.color(loreArray[i]));
            }
            Lore.add(MiscUtils.color("&3Click to toggle"));
            itemMeta.setLore(Lore);
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slot - 1, item);
    }

    public static void createDisplay(ItemStack is, Inventory inv, int slot){
        inv.setItem(slot - 1, is);
    }
}
