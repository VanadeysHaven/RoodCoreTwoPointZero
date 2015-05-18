package me.cooltimmetje.RoodCore.EventListeners;

import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * This class has been created on 18-5-2015 at 21:23 by cooltimmetje.
 */
public class PickupManager implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        Entity item = event.getItem();
        if(PlayerUtils.shotItems.contains(item)){
            event.setCancelled(true);
        } else {
            return;
        }
    }

}
