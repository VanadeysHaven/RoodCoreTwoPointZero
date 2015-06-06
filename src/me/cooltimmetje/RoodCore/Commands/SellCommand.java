package me.cooltimmetje.RoodCore.Commands;

import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This class has been created on 2-6-2015 at 16:30 by cooltimmetje.
 */
public class SellCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("sell")) {
            if(p.getItemInHand().getType() != Material.AIR) {
               int amount =  p.getItemInHand().getAmount();
                switch (p.getItemInHand().getType()) {
                    case DIAMOND:
                        int sellDiamond = getPrice(amount, 5, 10);
                        p.setItemInHand(new ItemStack(Material.AIR));
                        ChatUtils.msgPlayerTag(p, "Sell", "You sold &b&l" + amount + " diamonds &afor &9&l" + sellDiamond + " tokens&a!");
                        PlayerUtils.addTokens(p, sellDiamond, "sold " + amount + " diamonds");
                        break;
                    case GOLD_INGOT:
                        int sellGold = getPrice(amount, 5, 8);
                        p.setItemInHand(new ItemStack(Material.AIR));
                        ChatUtils.msgPlayerTag(p, "Sell", "You sold &6&l" + amount + " gold &afor &9&l" + sellGold + " tokens&a!");
                        PlayerUtils.addTokens(p, sellGold, "sold " + amount + " gold");
                        break;
                    case IRON_INGOT:
                        int sellIron = getPrice(amount, 4, 7);
                        p.setItemInHand(new ItemStack(Material.AIR));
                        ChatUtils.msgPlayerTag(p, "Sell", "You sold &3&l" + amount + " iron &afor &9&l" + sellIron + " tokens&a!");
                        PlayerUtils.addTokens(p, sellIron, "sold " + amount + " iron");
                        break;
                    case REDSTONE:
                        int sellRed = getPrice(amount, 3, 7);
                        p.setItemInHand(new ItemStack(Material.AIR));
                        ChatUtils.msgPlayerTag(p, "Sell", "You sold &c&l" + amount + " redstone &a for &9&l" + sellRed + " tokens&a!");
                        PlayerUtils.addTokens(p, sellRed, "sold " + amount + " redstone");
                        break;
                    case INK_SACK:
                        if(p.getItemInHand().getDurability() == 4) {
                            int sellLapis = getPrice(amount, 1, 5);
                            p.setItemInHand(new ItemStack(Material.AIR));
                            ChatUtils.msgPlayerTag(p, "Sell", "You sold &9&l" + amount + " lapis &a for &9&l" + sellLapis + " tokens&a!");
                            PlayerUtils.addTokens(p, sellLapis, "sold " + amount + " lapis");
                        } else {
                            ChatUtils.msgPlayerTag(p, "Sell", ChatUtils.error + "You can't sell that item!");
                        }
                        break;
                    case EMERALD:
                        int sellEmerald = getPrice(amount, 8, 12);
                        p.setItemInHand(new ItemStack(Material.AIR));
                        ChatUtils.msgPlayerTag(p, "Sell", "You sold &2&l" + amount + " emeralds &a for &9&l" + sellEmerald + " tokens&a!");
                        PlayerUtils.addTokens(p, sellEmerald, "sold " + amount + " emeralds");
                        break;
                    default:
                        ChatUtils.msgPlayerTag(p, "Sell", ChatUtils.error + "You can't sell that item!");
                        break;
                }
            } else {
                ChatUtils.msgPlayerTag(p, "Sell", ChatUtils.error + "You need to hold a item in your hand that you want to sell!");
            }
        }
        return true;
    }

    private int getPrice(int amount, int min, int max){
        int sell = 0;
        for(int i = 0; i < amount; i++){
            sell = sell + MiscUtils.randomInt(min, max);
        }
        return sell;
    }

}
