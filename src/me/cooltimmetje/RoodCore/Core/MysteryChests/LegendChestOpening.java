package me.cooltimmetje.RoodCore.Core.MysteryChests;

import me.cooltimmetje.RoodCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 30-5-2015 at 21:05 by cooltimmetje.
 */
public class LegendChestOpening implements Listener {

    private static ArrayList<Integer> chestSlotLegend = new ArrayList<>();
    private static ArrayList<String> allowLegend = new ArrayList<>();
    private static HashMap<String, Integer> rollsLegend = new HashMap<>();

    public static void openLegend(Player p) {
        if(allowLegend.contains(p.getName())){
            allowLegend.remove(p.getName());
        }
        rollsLegend.put(p.getName(), 4);
        setChestSlot();
        Inventory inv = Bukkit.createInventory(null, 54, MiscUtils.color("&cLegendary Chest"));

        InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&a&lYou are opening:", "&c&lLegendary Chest".toUpperCase(), inv, 5);
        p.openInventory(inv);

        int tick = 1;
        for (int i = 10; i < 55; i++) {
            if (!chestSlotLegend.contains(i)) {
                int slot = i;
                ScheduleUtils.scheduleTask((long) tick, new Runnable() {
                    @Override
                    public void run() {
                        InventoryUtils.createDisplay(Material.STAINED_GLASS_PANE, 1, MiscUtils.randomInt(0, 15), "&" + MiscUtils.randomColor() + "Colors!", null, inv, slot);
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 100, 1);
                    }
                });
                tick = tick + 1;
            }
        }


        int tickChest = 10;
        for (int i = 10; i < 55; i++) {
            if(chestSlotLegend.contains(i)) {
                int slot = i;
                ScheduleUtils.scheduleTask((long) tickChest, new Runnable() {
                    @Override
                    public void run() {
                        InventoryUtils.createDisplay(Material.ENDER_CHEST, 1, 0, "&cLegendary Chest", " \n&3Click to choose!", inv, slot);
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 100, 1);
                    }
                });
                tickChest = tickChest + 10;
            }
            if(i == 54){
                allowLegend.add(p.getName());
            }
        }
    }

    private static void setChestSlot(){
        if(chestSlotLegend.isEmpty()) {
            chestSlotLegend.add(20);
            chestSlotLegend.add(22);
            chestSlotLegend.add(24);
            chestSlotLegend.add(26);
            chestSlotLegend.add(38);
            chestSlotLegend.add(40);
            chestSlotLegend.add(42);
            chestSlotLegend.add(44);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Legendary Chest"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta())
            return;

        switch (event.getCurrentItem().getType()){
            case ENDER_CHEST:
                if(allowLegend.contains(p.getName())){
                    if(event.getSlot() != 4){
                        if(rollsLegend.get(p.getName()) != 0) {
                            int roll = rollsLegend.get(p.getName());
                            allowLegend.remove(p.getName());
                            rollSlot(p, event.getSlot(), event.getInventory());
                            rollsLegend.put(p.getName(), roll - 1);
                        }
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case STAINED_GLASS_PANE:
                p.playSound(p.getLocation(), Sound.GLASS, 100, 1);
                event.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                if(MiscUtils.randomInt(0, 100) <= 5){
                    PlayerUtils.addTokens(p, 1, "The insurance paid for this broken glass!");
                    InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&91 token", "&9(insurance payout)", event.getInventory(), event.getSlot() + 1);
                }
                break;
            default:
                break;
        }
    }

    private void rollSlot(Player p, int slot, Inventory inventory) {
        String[] items;
        items = new String[160];

        int index = 0;
        for(int i = 0; i < 20; i++){
            items[index++] = "tokens";
        }
        for(int i = 0; i < 30; i++){
            items[index++] = "diamond";
        }
        for(int i = 0; i < 35; i++){
            items[index++] = "gold";
        }
        for(int i = 0; i < 10; i++){
            items[index++] = "emerald";
        }
        for(int i = 0; i < 40; i++){
            items[index++] = "redstone";
        }
        for(int i = 0; i < 15; i++){
            items[index++] = "xp";
        }
        for(int i = 0; i < 5; i++){
            items[index++] = "netherstar";
        }
        for(int i = 0; i < 5; i++){
            items[index++] = "colorname";
        }

        for (int i = 0; i < 50; i++) {
            int ticks = i * 2;
            int times = i;
            int slotFinal = slot + 1;
            ScheduleUtils.scheduleTask((long) ticks, new Runnable() {
                @Override
                public void run() {
                    String item = items[MiscUtils.randomInt(0, items.length - 1)];
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 100 ,1);
                    if(item.equals("tokens")){
                        InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9Tokens", "&9+" + MiscUtils.randomInt(200, 300) + " tokens!", inventory, slotFinal);
                    } else if(item.equals("diamond")){
                        InventoryUtils.createDisplay(Material.DIAMOND, MiscUtils.randomInt(1, 24), 0, "&bDiamonds!",
                                "&bIn de muuuuuuuurrrr \n&bPak gauw want die zijn duuuurr!", inventory, slotFinal);
                    } else if(item.equals("gold")){
                        InventoryUtils.createDisplay(Material.GOLD_INGOT, MiscUtils.randomInt(1, 32), 0, "&6GOLD!", null, inventory, slotFinal);
                    } else if (item.equals("emerald")){
                        InventoryUtils.createDisplay(Material.EMERALD, MiscUtils.randomInt(1, 16), 0, "&aEMERALD!", null, inventory, slotFinal);
                    } else if (item.equals("redstone")){
                        InventoryUtils.createDisplay(Material.REDSTONE, MiscUtils.randomInt(1, 32), 0, "&cREDSTONE!", null, inventory, slotFinal);
                    } else if (item.equals("xp")){
                        InventoryUtils.createDisplay(Material.EXP_BOTTLE, MiscUtils.randomInt(1, 8), 0, "&dXP!", null, inventory, slotFinal);
                    } else if (item.equals("netherstar")){
                        InventoryUtils.createDisplay(Material.NETHER_STAR, 1, 0, "&" + MiscUtils.randomColor() + "NETHER STAR!", null, inventory, slotFinal);
                    } else if (item.equals("colorname")){
                    InventoryUtils.createDisplay(Material.WOOL, 1, MiscUtils.randomInt(0, 15), "&" + MiscUtils.randomColor() + "COLOR NAME!", null, inventory, slotFinal);
                    }

                    if(times == 49){
                        allowLegend.add(p.getName());
                        MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName());
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 100, 1);

                        if(item.equals("tokens")){
                            int tokens = MiscUtils.randomInt(200, 300);
                            InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9" + tokens + " tokens", null, inventory, slotFinal);
                            PlayerUtils.addTokens(p, tokens, "found in a legendary chest");
                            if(tokens == 300){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + tokens + " tokens" + " &bin a &c&lLegendary Chest&b!");
                            }
                        } else if(item.equals("diamond")){
                            InventoryUtils.createDisplay(Material.DIAMOND, MiscUtils.randomInt(1, 24), 0, "&bDiamonds!",
                                    "&bIn de muuuuuuuurrrr \n&bPak gauw want die zijn duuuurr!", inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.DIAMOND, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 24){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &l" + inventory.getItem(slot).getAmount() + " diamonds" + " &bin a &c&lLegendary Chest&b!");
                            }
                        } else if(item.equals("gold")){
                            InventoryUtils.createDisplay(Material.GOLD_INGOT, MiscUtils.randomInt(1, 32), 0, "&6GOLD!", null, inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.GOLD_INGOT, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 32){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &6&l" + inventory.getItem(slot).getAmount() + " gold" + " &bin a &c&lLegendary Chest&b!");
                            }
                        }  else if (item.equals("emerald")){
                            InventoryUtils.createDisplay(Material.EMERALD, MiscUtils.randomInt(1, 16), 0, "&aEMERALD!", null, inventory, slotFinal);
                            if (inventory.getItem(slot).getAmount() == 16) {
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &a&l" + inventory.getItem(slot).getAmount() + " emeralds" + " &bin a &c&lLegendary Chest&b!");
                            }
                            Bukkit.getWorld("Survival").dropItemNaturally(p.getLocation(), new ItemStack(Material.EMERALD, inventory.getItem(slot).getAmount()));
                        } else if (item.equals("redstone")){
                            InventoryUtils.createDisplay(Material.REDSTONE, MiscUtils.randomInt(1, 32), 0, "&cREDSTONE!", null, inventory, slotFinal);
                            Bukkit.getWorld("Survival").dropItemNaturally(p.getLocation(), new ItemStack(Material.REDSTONE, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 32){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &c&l" + inventory.getItem(slot).getAmount() + " redstone" + " &bin a &c&lLegendary Chest&b!");
                            }
                        } else if (item.equals("xp")){
                            InventoryUtils.createDisplay(Material.EXP_BOTTLE, MiscUtils.randomInt(1, 8), 0, "&dXP!", null, inventory, slotFinal);
                            PlayerUtils.addXpPoint(p, inventory.getItem(slot).getAmount(), "found in a legendary chest");
                            if (inventory.getItem(slot).getAmount() == 8) {
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &d&l" + inventory.getItem(slot).getAmount() + " xp points" + " &bin a &c&lLegendary Chest&b!");
                            }
                        } else if (item.equals("netherstar")){
                            InventoryUtils.createDisplay(Material.NETHER_STAR, 1, 0, "&" + MiscUtils.randomColor() + "NETHER STAR!", null, inventory, slotFinal);
                            Bukkit.getWorld("Survival").dropItemNaturally(p.getLocation(), new ItemStack(Material.NETHER_STAR, 1));
                            ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &" + MiscUtils.randomColor() + "&l" + inventory.getItem(slot).getAmount() + " Nether Star" + " &bin a &c&lLegendary Chest&b!");
                        } else if (item.equals("colorname")){
                            if(!p.hasPermission("roodcore.colorname") || p.getName().equals("Reges")) {
                                InventoryUtils.createDisplay(Material.WOOL, 1, MiscUtils.randomInt(0, 15), "&" + MiscUtils.randomColor() + "RAINBOW NAME!", null, inventory, slotFinal);
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &a&lRainbow Name" + " &bin a &c&lLegendary Chest&b!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " roodcore.colorname");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                            } else {
                                int tokens = MiscUtils.randomInt(200, 300);
                                ChatUtils.msgPlayerTag(p, "Chests", "You found a item you already have! So you got &9" + tokens + "tokens &ainstead!");
                                InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9" + tokens + " tokens", null, inventory, slotFinal);
                                PlayerUtils.addTokens(p, tokens, "found in a legendary chest");
                                if(tokens == 300){
                                    ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + tokens + " tokens" + " &bin a &c&lLegendary Chest&b!");
                                }
                            }
                        }

                    }
                }
            });


        }


    }
}
