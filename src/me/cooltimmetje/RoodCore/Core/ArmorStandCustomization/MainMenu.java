package me.cooltimmetje.RoodCore.Core.ArmorStandCustomization;

import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * This class has been created on 31-5-2015 at 15:41 by cooltimmetje.
 */
public class MainMenu implements Listener {

    public static HashMap<String, ArmorStand> pAs = new HashMap<>();

    @EventHandler
    public void onRightClickEntity(PlayerInteractAtEntityEvent event){
        Player p = event.getPlayer();
        if(event.getRightClicked() != null) {
            if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
                if(event.getPlayer().getItemInHand().getType() == Material.AIR) {
                    if (p.hasPermission("roodcore.armorstand") || p.isOp()) {
                        event.setCancelled(true);
                        ArmorStand as = (ArmorStand) event.getRightClicked();
                        pAs.put(p.getName(), as);
                        openMain(p);
                    }
                }
            }
        }
    }

    private void openMain(Player p) {
        ArmorStand as = pAs.get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 36 , "ArmorStand Customizer");

        InventoryUtils.createDisplay(Material.ARROW, 1, 0, "&aArms &8\u00BB " + MiscUtils.formatBoolean(as.hasArms()), "&3This defines if the ArmorStand has arms.", inv, 11);
        InventoryUtils.createTrueFalse(as.hasArms(), "&aArms", "&3This defines if the ArmorStand has arms.\n ", inv, 20);

        InventoryUtils.createDisplay(Material.STEP, 1, 0, "&aBase Plate &8\u00BB " + MiscUtils.formatBoolean(as.hasBasePlate()), "&3This defines if the ArmorStand has a BasePlate.", inv, 12);
        InventoryUtils.createTrueFalse(as.hasBasePlate(), "&aBase Plate", "&3This defines if the ArmorStand has a BasePlate.\n ", inv, 21);

        InventoryUtils.createDisplay(Material.EMERALD, 1, 0, "&aSmall &8\u00BB " + MiscUtils.formatBoolean(as.isSmall()), "&3This defines if the ArmorStand is small.", inv, 13);
        InventoryUtils.createTrueFalse(as.isSmall(), "&aSmall", "&3This defines if the ArmorStand has a is small.\n ", inv, 22);

        InventoryUtils.createDisplay(Material.GOLDEN_CARROT, 1, 0, "&aVisible &8\u00BB " + MiscUtils.formatBoolean(as.isVisible()), "&3This defines if the ArmorStand is visible.", inv, 14);
        InventoryUtils.createTrueFalse(as.isVisible(), "&aVisible", "&3This defines if the ArmorStand is visible.\n ", inv, 23);

        InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&aInventory Contents", "&3Here you can edit the contents of \n&3the inventory of the ArmorStand!", inv, 15);
        InventoryUtils.createDisplay(Material.ENDER_PEARL, 1, 0, "&aInventory Contents &3(Disabled)", "&3Here you can edit the contents of \n&3the inventory of the ArmorStand!\n \n&3Click to open!", inv, 24);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("ArmorStand Customizer"))
            return;

        if(event.getSlot() == -999)
            return;

        if(!event.getCurrentItem().hasItemMeta())
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        ArmorStand as = pAs.get(p.getName());
        Inventory inv = event.getInventory();
        switch (event.getCurrentItem().getType()){
            case INK_SACK:
                switch(event.getSlot()){
                    case 19:
                        as.setArms(!as.hasArms());
                        p.playSound(p.getLocation(), Sound.CLICK, 100, 1);
                        InventoryUtils.createDisplay(Material.ARROW, 1, 0, "&aArms &8\u00BB " + MiscUtils.formatBoolean(as.hasArms()), "&3This defines if the ArmorStand has arms.", inv, 11);
                        InventoryUtils.createTrueFalse(as.hasArms(), "&aArms", "&3This defines if the ArmorStand has arms.\n ", inv, 20);
                        break;
                    case 20:
                        as.setBasePlate(!as.hasBasePlate());
                        p.playSound(p.getLocation(), Sound.CLICK, 100, 1);
                        InventoryUtils.createDisplay(Material.STEP, 1, 0, "&aBase Plate &8\u00BB " + MiscUtils.formatBoolean(as.hasBasePlate()), "&3This defines if the ArmorStand has a BasePlate.", inv, 12);
                        InventoryUtils.createTrueFalse(as.hasBasePlate(), "&aBase Plate", "&3This defines if the ArmorStand has a BasePlate.\n ", inv, 21);
                        break;
                    case 21:
                        as.setSmall(!as.isSmall());
                        p.playSound(p.getLocation(), Sound.CLICK, 100, 1);
                        InventoryUtils.createDisplay(Material.EMERALD, 1, 0, "&aSmall &8\u00BB " + MiscUtils.formatBoolean(as.isSmall()), "&3This defines if the ArmorStand is small.", inv, 13);
                        InventoryUtils.createTrueFalse(as.isSmall(), "&aSmall", "&3This defines if the ArmorStand has a is small.\n ", inv, 22);
                        break;
                    case 22:
                        as.setVisible(!as.isVisible());
                        p.playSound(p.getLocation(), Sound.CLICK, 100, 1);
                        InventoryUtils.createDisplay(Material.GOLDEN_CARROT, 1, 0, "&aVisible &8\u00BB " + MiscUtils.formatBoolean(as.isVisible()), "&3This defines if the ArmorStand is visible.", inv, 14);
                        InventoryUtils.createTrueFalse(as.isVisible(), "&aVisible", "&3This defines if the ArmorStand is visible.\n ", inv, 23);
                        break;
                    default:
                        break;
                }
                break;
            case ENDER_PEARL:
//                Contents.openContents(p);
                break;
            default:
                break;
        }
    }
}
