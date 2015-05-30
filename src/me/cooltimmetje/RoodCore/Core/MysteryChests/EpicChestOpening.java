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
public class EpicChestOpening implements Listener {

    private static ArrayList<Integer> chestSlotEpic = new ArrayList<>();
    private static ArrayList<String> allowEpic = new ArrayList<>();
    private static HashMap<String, Integer> rollsEpic = new HashMap<>();

    public static void openEpic(Player p) {
        if(allowEpic.contains(p.getName())){
            allowEpic.remove(p.getName());
        }
        rollsEpic.put(p.getName(), 4);
        setChestSlot();
        Inventory inv = Bukkit.createInventory(null, 54, MiscUtils.color("&5Epic Chest"));

        InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&a&lYou are opening:", "&5&lEpic Chest".toUpperCase(), inv, 5);
        p.openInventory(inv);

        int tick = 1;
        for (int i = 10; i < 55; i++) {
            if (!chestSlotEpic.contains(i)) {
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
            if(chestSlotEpic.contains(i)) {
                int slot = i;
                ScheduleUtils.scheduleTask((long) tickChest, new Runnable() {
                    @Override
                    public void run() {
                        InventoryUtils.createDisplay(Material.TRAPPED_CHEST, 1, 0, "&5Epic Chest", " \n&3Click to choose!", inv, slot);
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 100, 1);
                    }
                });
                tickChest = tickChest + 10;
            }
            if(i == 54){
                allowEpic.add(p.getName());
            }
        }
    }

    private static void setChestSlot(){
        if(chestSlotEpic.isEmpty()) {
            chestSlotEpic.add(20);
            chestSlotEpic.add(22);
            chestSlotEpic.add(24);
            chestSlotEpic.add(26);
            chestSlotEpic.add(38);
            chestSlotEpic.add(40);
            chestSlotEpic.add(42);
            chestSlotEpic.add(44);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Epic Chest"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta())
            return;

        switch (event.getCurrentItem().getType()){
            case TRAPPED_CHEST:
                if(allowEpic.contains(p.getName())){
                    if(event.getSlot() != 4){
                        if(rollsEpic.get(p.getName()) != 0) {
                            int roll = rollsEpic.get(p.getName());
                            allowEpic.remove(p.getName());
                            rollSlot(p, event.getSlot(), event.getInventory());
                            rollsEpic.put(p.getName(), roll - 1);
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
        items = new String[200];

        int index = 0;
        for(int i = 0; i < 20; i++){
            items[index++] = "tokens";
        }
        for(int i = 0; i < 20; i++){
            items[index++] = "diamond";
        }
        for(int i = 0; i < 30; i++){
            items[index++] = "gold";
        }
        for(int i = 0; i < 40; i++){
            items[index++] = "iron";
        }
        for(int i = 0; i < 10; i++){
            items[index++] = "armorstand";
        }
        for(int i = 0; i < 5; i++){
            items[index++] = "emerald";
        }
        for(int i = 0; i < 35; i++){
            items[index++] = "lapis";
        }
        for(int i = 0; i < 30; i++){
            items[index++] = "redstone";
        }
        for(int i = 0; i < 10; i++){
            items[index++] = "xp";
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
                        InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9Tokens", "&9+" + MiscUtils.randomInt(100, 200) + " tokens!", inventory, slotFinal);
                    } else if(item.equals("diamond")){
                        InventoryUtils.createDisplay(Material.DIAMOND, MiscUtils.randomInt(1, 16), 0, "&bDiamonds!",
                                "&bIn de muuuuuuuurrrr \n&bPak gauw want die zijn duuuurr!", inventory, slotFinal);
                    } else if(item.equals("gold")){
                        InventoryUtils.createDisplay(Material.GOLD_INGOT, MiscUtils.randomInt(1, 24), 0, "&6GOLD!", null, inventory, slotFinal);
                    } else if (item.equals("iron")){
                        InventoryUtils.createDisplay(Material.IRON_INGOT, MiscUtils.randomInt(1, 32), 0, "&3IRON!", null, inventory, slotFinal);
                    } else if (item.equals("armorstand")){
                        InventoryUtils.createDisplay(Material.ARMOR_STAND, 1, 0, "&aArmor Stand customization!", null, inventory, slotFinal);
                    } else if (item.equals("emerald")){
                        InventoryUtils.createDisplay(Material.EMERALD, MiscUtils.randomInt(1, 8), 0, "&aEMERALD!", null, inventory, slotFinal);
                    } else if (item.equals("lapis")){
                        InventoryUtils.createDisplay(Material.INK_SACK, MiscUtils.randomInt(1, 64), 4, "&9LAPIS!", null, inventory, slotFinal);
                    } else if (item.equals("redstone")){
                        InventoryUtils.createDisplay(Material.REDSTONE, MiscUtils.randomInt(1, 32), 0, "&cREDSTONE!", null, inventory, slotFinal);
                    } else if (item.equals("xp")){
                        InventoryUtils.createDisplay(Material.EXP_BOTTLE, MiscUtils.randomInt(1, 4), 0, "&dXP!", null, inventory, slotFinal);
                    }

                    if(times == 49){
                        allowEpic.add(p.getName());
                        MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName());
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 100, 1);

                        if(item.equals("tokens")){
                            int tokens = MiscUtils.randomInt(100, 200);
                            InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9" + tokens + " tokens", null, inventory, slotFinal);
                            PlayerUtils.addTokens(p, tokens, "found in a normal chest");
                            if(tokens == 200){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + tokens + " tokens" + " &bin a &5&lEpic Chest&b!");
                            }
                        } else if(item.equals("diamond")){
                            InventoryUtils.createDisplay(Material.DIAMOND, MiscUtils.randomInt(1, 16), 0, "&bDiamonds!",
                                    "&bIn de muuuuuuuurrrr \n&bPak gauw want die zijn duuuurr!", inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.DIAMOND, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 16){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &l" + inventory.getItem(slot).getAmount() + " diamonds" + " &bin a &5&lEpic Chest&b!");
                            }
                        } else if(item.equals("gold")){
                            InventoryUtils.createDisplay(Material.GOLD_INGOT, MiscUtils.randomInt(1, 24), 0, "&6GOLD!", null, inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.GOLD_INGOT, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 24){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &6&l" + inventory.getItem(slot).getAmount() + " gold" + " &bin a &5&lEpic Chest&b!");
                            }
                        } else if (item.equals("iron")){
                            InventoryUtils.createDisplay(Material.IRON_INGOT, MiscUtils.randomInt(1, 32), 0, "&3IRON!", null, inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.IRON_INGOT, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 32){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &3&l" + inventory.getItem(slot).getAmount() + " iron" + " &bin a &5&lEpic Chest&b!");
                            }
                        } else if (item.equals("armorstand")){
                            InventoryUtils.createDisplay(Material.ARMOR_STAND, 1, 0, "&aArmorStand customization!", null, inventory, slotFinal);
                            if(!p.hasPermission("roodcore.armorstand")) {
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &a&lArmorStand customization" + " &bin a &5&lEpic Chest&b!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " roodcore.armorstand");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                            } else {
                                int tokens = MiscUtils.randomInt(100, 200);
                                ChatUtils.msgPlayerTag(p, "Chests", "You found a item you already have! So you got &9" + tokens + " tokens &ainstead!");
                                InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9" + tokens + " tokens", null, inventory, slotFinal);
                                PlayerUtils.addTokens(p, tokens, "found in a normal chest");
                                if(tokens == 200){
                                    ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + tokens + " tokens" + " &bin a &5&lEpic Chest&b!");
                                }
                            }
                        }  else if (item.equals("emerald")){
                            InventoryUtils.createDisplay(Material.EMERALD, MiscUtils.randomInt(1, 8), 0, "&aEMERALD!", null, inventory, slotFinal);
                            ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &a&l" + inventory.getItem(slot).getAmount() + " emeralds" + " &bin a &5&lEpic Chest&b!");
                            Bukkit.getWorld("Survival").dropItemNaturally(p.getLocation(), new ItemStack(Material.EMERALD, inventory.getItem(slot).getAmount()));
                        } else if (item.equals("lapis")){
                            InventoryUtils.createDisplay(Material.INK_SACK, MiscUtils.randomInt(1, 64), 4, "&9LAPIS!", null, inventory, slotFinal);
                            Bukkit.getWorld("Survival").dropItemNaturally(p.getLocation(), new ItemStack(Material.INK_SACK, inventory.getItem(slot).getAmount(), (byte) 4));
                            if(inventory.getItem(slot).getAmount() == 64){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + inventory.getItem(slot).getAmount() + " lapis" + " &bin a &5&lEpic Chest&b!");
                            }
                        } else if (item.equals("redstone")){
                            InventoryUtils.createDisplay(Material.REDSTONE, MiscUtils.randomInt(1, 32), 0, "&cREDSTONE!", null, inventory, slotFinal);
                            Bukkit.getWorld("Survival").dropItemNaturally(p.getLocation(), new ItemStack(Material.REDSTONE, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 32){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &c&l" + inventory.getItem(slot).getAmount() + " redstone" + " &bin a &5&lEpic Chest&b!");
                            }
                        } else if (item.equals("xp")){
                            InventoryUtils.createDisplay(Material.EXP_BOTTLE, MiscUtils.randomInt(1, 4), 0, "&dXP!", null, inventory, slotFinal);
                            PlayerUtils.addXpPoint(p, inventory.getItem(slot).getAmount(), "found in a epic chest");
                            ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &d&l" + inventory.getItem(slot).getAmount() + " xp points" + " &bin a &5&lEpic Chest&b!");
                        }
                    }
                }
            });


        }


    }
}
