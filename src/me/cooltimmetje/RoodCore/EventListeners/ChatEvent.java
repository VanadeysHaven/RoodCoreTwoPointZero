package me.cooltimmetje.RoodCore.EventListeners;

import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This class has been created on 31-5-2015 at 16:45 by cooltimmetje.
 */
public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if(p.hasPermission("roodcore.colorname") && !p.getName().equals("Reges")){
            PlayerUtils.rainbowDisplayName(p);
        }
    }

}
