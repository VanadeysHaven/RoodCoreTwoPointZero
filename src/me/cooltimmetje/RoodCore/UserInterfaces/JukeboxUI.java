package me.cooltimmetje.RoodCore.UserInterfaces;

import com.darkblade12.particleeffect.ParticleEffect;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.InventoryUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * This class has been created on 15-5-2015 at 13:46 by cooltimmetje.
 */

@SuppressWarnings("all")
public class JukeboxUI implements Listener {

    public static HashMap<String,Jukebox> jukeboxPlayer = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = event.getClickedBlock();
            if(b.getType() == Material.JUKEBOX){
                event.setCancelled(true);
                Jukebox jfirst = (Jukebox) event.getClickedBlock().getState();
                jfirst.setPlaying(null);
                event.getClickedBlock().setType(Material.AIR);
                event.getClickedBlock().setType(Material.JUKEBOX);
                Jukebox j = (Jukebox) event.getClickedBlock().getState();
                int rp = DataClass.resourcePack.get(p.getName());
                if(rp != 0) {
                    openJukeboxUI(p, rp);
                    jukeboxPlayer.put(p.getName(), j);
                } else {
                    ChatUtils.msgPlayerTag(p, "Jukebox", ChatUtils.error + "Please load a Resourcepack! Use &o/rp <id>&a!");
                }
            }
        }
    }

    private void openJukeboxUI(Player p, int rp){
        Inventory inv = Bukkit.createInventory(null, 18, "Choose a song: ");
        HashMap<String, String> songNames = DataClass.resourceList.get(rp);
        int i = 0;
        for(String disc : songNames.keySet()){
            i = i + 1;
            String name = songNames.get(disc);
            InventoryUtils.createDisplay(Material.getMaterial(disc), 1, 0, "&a" + name, "&7&oClick to play!", inv, i);
        }
        InventoryUtils.createDisplay(Material.PAPER, 1, 0, "&a&lNOTE:", "&aAll rights of all music used go to their respective owners!", inv, 18);
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player p = (Player) event.getPlayer();
        if (event.getInventory().getName().contains("Choose a song:")){
            if(jukeboxPlayer.containsKey(p.getName())){
                jukeboxPlayer.remove(p.getName());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Choose a song: "))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta() || event.getCurrentItem().getType() == Material.PAPER){
            return;
        }

        Jukebox j = jukeboxPlayer.get(p.getName());
        j.setPlaying(event.getCurrentItem().getType());
        ChatUtils.msgPlayerTag(p, "Jukebox", "Now playing: &6" + event.getCurrentItem().getItemMeta().getDisplayName());
        p.closeInventory();

        MiscUtils.shootFirework(j.getLocation(), j.getWorld().getName());
        MiscUtils.shootFirework(j.getLocation().add(1, 0, 1), p.getWorld().getName());
        MiscUtils.shootFirework(j.getLocation().add(1, 0, 0), p.getWorld().getName());
        MiscUtils.shootFirework(j.getLocation().add(0, 0, 1), p.getWorld().getName());

        int tick = 0;
        for(double y = 0; y <= 10; y += 0.1){
            double yFinal = y;
            ScheduleUtils.scheduleTask(tick, new Runnable() {
                @Override
                public void run() {
                    double adjustedX = 2 * Math.cos(yFinal);
                    double adjustedZ = 2 * Math.sin(yFinal);

                    Location loc = j.getLocation().add(adjustedX, yFinal, adjustedZ);
                    Location locMinus = j.getLocation().add(adjustedX * -1, yFinal, adjustedZ * -1);
                    ParticleEffect.NOTE.display(0, 0, 0, 1, 1, loc, 20);
                    ParticleEffect.NOTE.display(0, 0, 0, 1, 1, locMinus, 20);
                }
            });
            tick = tick + 1;
        }
    }

}
