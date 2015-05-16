package me.cooltimmetje.RoodCore.Core;

import com.darkblade12.particleeffect.ParticleEffect;
import me.cooltimmetje.RoodCore.Utilities.*;
import org.bukkit.*;
import org.bukkit.block.Jukebox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 16-5-2015 at 13:58 by cooltimmetje.
 */
public class Rankup implements CommandExecutor,Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("rankup")) {
                String curGroupName = PlayerUtils.getGroup(p).trim();
                String curGroupColor = DataClass.idColor.get(DataClass.nameID.get(curGroupName));

                String nextGroupName = DataClass.idName.get(DataClass.nameID.get(curGroupName) + 1);
                String nextGroupColor = DataClass.idColor.get(DataClass.nameID.get(curGroupName) + 1);

            if(DataClass.tokens.get(p.getName()) >= DataClass.rankupCost){
                if (curGroupColor != null || nextGroupColor != null) {
                    openRankUp(p, curGroupColor, nextGroupColor);
                } else {
                    ChatUtils.msgPlayerTag(p, "Rankup", ChatUtils.error + "There is no next rank available for you, sorry.");
                }
            } else {
                int tokensNeeded = DataClass.tokens.get(p.getName()) - DataClass.rankupCost;
                ChatUtils.msgPlayerTag(p, "Rankup", ChatUtils.error + "You need &9" + tokensNeeded + " &amore &9tokens &ato do that!");
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 100, 1);
            }
        }
        return true;
    }

    private void openRankUp(Player p, String curRank, String nextRank){
        Inventory inv = Bukkit.createInventory(null, 27, "Rankup");

        InventoryUtils.createDisplay(Material.STAINED_CLAY, 1, 13, "&2&lCONFIRM", "&7&oClick to buy this!", inv, 12);
        InventoryUtils.createDisplay(Material.NETHER_STAR, 1, 13, "&aRankup: " + curRank + " &b-> " + nextRank, "&7&oCost: &9&o" + DataClass.rankupCost + " tokens" , inv, 14);
        InventoryUtils.createDisplay(Material.STAINED_CLAY, 1, 14, "&2&lCANCEL", "&7&oClick to cancel", inv, 16);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();

        if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Rankup"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if (!event.getCurrentItem().hasItemMeta()) {
            return;
        }

        switch (event.getCurrentItem().getType()) {
            case STAINED_CLAY:
                if(event.getCurrentItem().getItemMeta().getDisplayName().contains("CONFIRM")){
                    PlayerUtils.removeTokens(p, DataClass.rankupCost, "bought a rankup");
                    ChatUtils.msgPlayerTag(p, "Rankup", "Purchase successful! Rank will be given in 5 seconds!");
                    p.closeInventory();
                    ScheduleUtils.scheduleTask(100, new Runnable() {
                        @Override
                        public void run() {
                            PlayerUtils.rankUp(p);
                        }
                    });
                    break;
                } else {
                    p.closeInventory();
                    break;
                }
            default:
                break;
        }

    }

}
