package me.cooltimmetje.RoodCore.Core.Gadgets;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 6-6-2015 at 11:57 by cooltimmetje.
 */
public class GadgetsMenu implements CommandExecutor,Listener{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("gadgets")) {
            openGadgets(p);
        }
        return true;
    }

    private void openGadgets(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Gadgets");

        int selected = DataClass.selectedGadget.get(p.getName());

        String paintballLore =
                " " +
                        "\n&7This gun shoots balls with paint in them" +
                        "\n&7and causes blocks to change from color!" +
                        "\n " +
                        "\n&aUnlocked &8\u00BB " + MiscUtils.formatYesNo(p.hasPermission("roodcore.paintball")) +
                        "\n&aSelected &8\u00BB " + MiscUtils.formatYesNo(selected == 1) +
                        "\n&aAmmo &8\u00BB &b" + DataClass.paintballAmmo.get(p.getName());
        InventoryUtils.createDisplay(Material.IRON_BARDING, 1, 0, "&aPaintball Gun", paintballLore, inv, 1);

        String tntLore =
                " " +
                        "\n&7This gun shoots balls with paint in them" +
                        "\n&7and causes blocks to change from color!" +
                        "\n " +
                        "\n&aUnlocked &8\u00BB " + MiscUtils.formatYesNo(p.hasPermission("roodcore.paintball")) +
                        "\n&aSelected &8\u00BB " + MiscUtils.formatYesNo(selected == 1) +
                        "\n&aAmmo &8\u00BB &b" + DataClass.paintballAmmo.get(p.getName());

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Gadgets"))
            return;

        if(event.getSlot() == -999)
            return;

        if(!event.getCurrentItem().hasItemMeta())
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        Inventory inv = event.getInventory();
        switch (event.getCurrentItem().getType()){
            case IRON_BARDING:
                break;
            default:
                break;
        }
    }

}
