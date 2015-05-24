package me.cooltimmetje.RoodCore.UserInterfaces;

import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

/**
 * This class has been created on 24-5-2015 at 16:11 by cooltimmetje.
 */
public class TimeRainUI implements Listener {

    private HashMap<Integer, Long> timeDataMap = new HashMap<>();
    private HashMap<Integer, String> timeNameMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(p.getItemInHand().getType() == Material.WATCH){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
                openTime(p);
            }
            if(!p.getItemInHand().hasItemMeta()){
                ItemStack item = p.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(MiscUtils.color("&aTime and Weather settings &8\u00BB &3(Right Click)"));
                item.setItemMeta(meta);
                p.setItemInHand(item);
            }
        }
    }

    private void openTime(Player p) {
        Inventory inv = Bukkit.createInventory(null, 18, "Time and Weather");

        if(timeDataMap.isEmpty()){
            timeDataMap.put(5,(long) 0);
            timeDataMap.put(4,(long) 6000);
            timeDataMap.put(1,(long) 12000);
            timeDataMap.put(15,(long) 18000);
        }

        if(timeNameMap.isEmpty()){
            timeNameMap.put(5, "day");
            timeNameMap.put(4, "noon");
            timeNameMap.put(1, "dusk");
            timeNameMap.put(15, "midnight");
        }

        String timeName = null;
        int timeData = 0;

        long currentTime = Bukkit.getWorld("Survival").getTime();
        if (currentTime >= 21000 || currentTime < 3000) {
            timeName = "day";
            timeData = 5;
        } else if (currentTime >= 3000 && currentTime < 9000) {
            timeName = "noon";
            timeData = 4;
        } else if (currentTime >= 9000 && currentTime < 15000) {
            timeName = "dusk";
            timeData = 1;
        } else if (currentTime >= 15000 && currentTime < 21000) {
            timeName = "midnight";
            timeData = 15;
        }

        InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 5, "&a&lSet time to: &bday", null, inv, 1);
        InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 4, "&a&lSet time to: &bnoon", null, inv, 2);
        InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 1, "&a&lSet time to: &bdusk", null, inv, 3);
        InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 15, "&a&lSet time to: &bmidnight", null, inv, 4);

        if (timeData == 5) {
            InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 5, "&a&lSet time to: &bday", "&c&lCurrent Time", inv, 1);
        } else if (timeData == 4) {
            InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 4, "&a&lSet time to: &bnoon", "&c&lCurrent Time", inv, 2);
        } else if (timeData == 1) {
            InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 1, "&a&lSet time to: &bdusk", "&c&lCurrent Time", inv, 3);
        } else if (timeData == 15) {
            InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, 15, "&a&lSet time to: &bmidnight", "&c&lCurrent Time", inv, 4);
        }

        InventoryUtils.createDisplay(Material.WATCH, 1, 0, "&a&lCurrent time: &b" + timeName, "&7&o(" + Bukkit.getWorld("Survival").getTime() + ")", inv, 10);

        InventoryUtils.createDisplay(Material.BUCKET, 1, 0, "&a&lSet weather to: &bclear", null, inv, 8);
        InventoryUtils.createDisplay(Material.WATER_BUCKET, 1, 0, "&a&lSet weather to: &brain", null, inv, 9);

        if(Bukkit.getWorld("Survival").isThundering()){
            InventoryUtils.createDisplay(Material.WATER_BUCKET, 1, 0, "&a&lCurrent weather: &brain", null, inv, 18);
            InventoryUtils.createDisplay(Material.WATER_BUCKET, 1, 0, "&a&lSet weather to: &brain", "&c&lCurrent Weather", inv, 9);
        } else {
            InventoryUtils.createDisplay(Material.BUCKET, 1, 0, "&a&lCurrent weather: &bclear", null, inv, 18);
            InventoryUtils.createDisplay(Material.BUCKET, 1, 0, "&a&lSet weather to: &bclear", "&c&lCurrent Weather", inv, 8);
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Time and Weather"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            case STAINED_GLASS_PANE:
                Bukkit.getWorld("Survival").setTime(timeDataMap.get((int) event.getCurrentItem().getDurability()));
                p.closeInventory();
                ChatUtils.broadcastTag("Time and Weather", p.getDisplayName() + " &achanged the time to &b" + (timeNameMap.get((int) event.getCurrentItem().getDurability())) + "&a.");
                break;
            case WATER_BUCKET:
                if(event.getSlot() != 18){
                    Bukkit.getWorld("Survival").setStorm(true);
                    ChatUtils.broadcastTag("Time and Weather", p.getDisplayName() + " &achanged the weather to &brain&a.");
                } else {
                    break;
                }
                break;
            case BUCKET:
                if(event.getSlot() != 18){
                    Bukkit.getWorld("Survival").setThundering(false);
                    Bukkit.getWorld("Survival").setStorm(false);
                    ChatUtils.broadcastTag("Time and Weather", p.getDisplayName() + " &achanged the weather to &bclear&a.");
                    p.closeInventory();
                } else {
                    break;
                }
            default:
                break;
        }
    }

}
