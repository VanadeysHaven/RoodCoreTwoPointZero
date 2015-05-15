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

    public static void createDisplay(Material m, int amount, int data, String name, String lore, Inventory inv, int slotNumber){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        if(!(lore == null)){
            ArrayList<String> Lore = new ArrayList<>();
            Lore.add(MiscUtils.color(lore));
            itemMeta.setLore(Lore);
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slotNumber - 1, item);
    }


}
