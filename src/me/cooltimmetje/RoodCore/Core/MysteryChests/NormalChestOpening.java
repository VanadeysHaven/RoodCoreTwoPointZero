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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 26-5-2015 at 19:59 by cooltimmetje.
 */
public class NormalChestOpening implements Listener {

    private static ArrayList<Integer> chestSlot = new ArrayList<>();
    private static ArrayList<String> allow = new ArrayList<>();
    private static HashMap<String, Integer> rolls = new HashMap<>();

    public static void openNormal(Player p) {
        if(allow.contains(p.getName())){
            allow.remove(p.getName());
        }
        rolls.put(p.getName(), 4);
        setChestSlot();
        Inventory inv = Bukkit.createInventory(null, 54, MiscUtils.color("&eNormal Chest"));

        InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&a&lYou are opening:", "&e&lNORMAL CHEST", inv, 5);
        p.openInventory(inv);

        int tick = 1;
        for (int i = 10; i < 55; i++) {
            if (!chestSlot.contains(i)) {
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
            if(chestSlot.contains(i)) {
                int slot = i;
                ScheduleUtils.scheduleTask((long) tickChest, new Runnable() {
                    @Override
                    public void run() {
                        InventoryUtils.createDisplay(Material.CHEST, 1, 0, "&eNormal Chest", " \n&3Click to choose!", inv, slot);
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 100, 1);
                    }
                });
                tickChest = tickChest + 10;
            }
            if(i == 54){
                allow.add(p.getName());
            }
        }
    }

    private static void setChestSlot(){
        if(chestSlot.isEmpty()) {
            chestSlot.add(20);
            chestSlot.add(22);
            chestSlot.add(24);
            chestSlot.add(26);
            chestSlot.add(38);
            chestSlot.add(40);
            chestSlot.add(42);
            chestSlot.add(44);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Normal Chest"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta())
            return;

        switch (event.getCurrentItem().getType()){
            case CHEST:
                if(allow.contains(p.getName())){
                    if(event.getSlot() != 4){
                        if(rolls.get(p.getName()) != 0) {
                            int roll = rolls.get(p.getName());
                            allow.remove(p.getName());
                            rollSlot(p, event.getSlot(), event.getInventory());
                            rolls.put(p.getName(), roll - 1);
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
        items = new String[85];

        int index = 0;
        for(int i = 0; i < 20; i++){
            items[index++] = "tokens";
        }
        for(int i = 0; i < 10; i++){
            items[index++] = "diamond";
        }
        for(int i = 0; i < 20; i++){
            items[index++] = "gold";
        }
        for(int i = 0; i < 30; i++){
            items[index++] = "iron";
        }
        for(int i = 0; i < 5; i++){
            items[index++] = "armorstand";
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
                        InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9Tokens", "&9+" + MiscUtils.randomInt(20, 100) + " tokens!", inventory, slotFinal);
                    } else if(item.equals("diamond")){
                        InventoryUtils.createDisplay(Material.DIAMOND, MiscUtils.randomInt(1, 8), 0, "&bDiamonds!",
                                "&bIn de muuuuuuuurrrr \n&bPak gauw want die zijn duuuurr!", inventory, slotFinal);
                    } else if(item.equals("gold")){
                        InventoryUtils.createDisplay(Material.GOLD_INGOT, MiscUtils.randomInt(1, 16), 0, "&6GOLD!", null, inventory, slotFinal);
                    } else if (item.equals("iron")){
                        InventoryUtils.createDisplay(Material.IRON_INGOT, MiscUtils.randomInt(1, 24), 0, "&3IRON!", null, inventory, slotFinal);
                    } else if (item.equals("armorstand")){
                        InventoryUtils.createDisplay(Material.ARMOR_STAND, 1, 0, "&aArmor Stand customization!", null, inventory, slotFinal);
                    }

                    if(times == 49){
                        allow.add(p.getName());
                        MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName());
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 100, 1);

                        if(item.equals("tokens")){
                            int tokens = MiscUtils.randomInt(20, 100);
                            InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9" + tokens + " tokens", null, inventory, slotFinal);
                            PlayerUtils.addTokens(p, tokens, "found in a normal chest");
                            if(tokens == 100){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + tokens + " tokens" + " &bin a &e&lNormal Chest&b!");
                            }
                        } else if(item.equals("diamond")){
                            InventoryUtils.createDisplay(Material.DIAMOND, MiscUtils.randomInt(1, 8), 0, "&bDiamonds!",
                                    "&bIn de muuuuuuuurrrr \n&bPak gauw want die zijn duuuurr!", inventory, slotFinal);
                            ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &l" + inventory.getItem(slot).getAmount() + " diamonds" + " &bin a &e&lNormal Chest&b!");
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.DIAMOND, inventory.getItem(slot).getAmount()));
                        } else if(item.equals("gold")){
                            InventoryUtils.createDisplay(Material.GOLD_INGOT, MiscUtils.randomInt(1, 16), 0, "&6GOLD!", null, inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.GOLD_INGOT, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 8){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &6&l" + inventory.getItem(slot).getAmount() + " gold" + " &bin a &e&lNormal Chest&b!");
                            }
                        } else if (item.equals("iron")){
                            InventoryUtils.createDisplay(Material.IRON_INGOT, MiscUtils.randomInt(1, 24), 0, "&3IRON!", null, inventory, slotFinal);
                            Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), new ItemStack(Material.IRON_INGOT, inventory.getItem(slot).getAmount()));
                            if(inventory.getItem(slot).getAmount() == 24){
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &3&l" + inventory.getItem(slot).getAmount() + " iron" + " &bin a &e&lNormal Chest&b!");
                            }
                        } else if (item.equals("armorstand")){
                            InventoryUtils.createDisplay(Material.ARMOR_STAND, 1, 0, "&aArmorStand customization!", null, inventory, slotFinal);
                            if(!p.hasPermission("roodcore.armorstand")) {
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &a&lArmorStand customization" + " &bin a &e&lNormal Chest&b!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " roodcore.armorstand");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                            } else {
                                int tokens = MiscUtils.randomInt(20, 100);
                                ChatUtils.msgPlayerTag(p, "Chests", "You found a item you already have! So you got &9" + tokens + "tokens &ainstead!");
                                InventoryUtils.createDisplay(Material.GOLD_NUGGET, 1, 0, "&9" + tokens + " tokens", null, inventory, slotFinal);
                                PlayerUtils.addTokens(p, tokens, "found in a normal chest");
                                if(tokens == 100){
                                    ChatUtils.broadcastTag("Chests", p.getDisplayName() + "&b found &9&l" + tokens + " tokens" + " &bin a &e&lNormal Chest&b!");
                                }
                            }
                        }
                    }
                }
            });


        }


    }
}