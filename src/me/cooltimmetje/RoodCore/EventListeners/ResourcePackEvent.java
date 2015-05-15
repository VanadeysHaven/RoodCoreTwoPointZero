package me.cooltimmetje.RoodCore.EventListeners;

import de.inventivegames.rpapi.ResourcePackStatusEvent;
import de.inventivegames.rpapi.Status;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * This class has been created on 15-5-2015 at 12:08 by cooltimmetje.
 */
@SuppressWarnings("unused")
public class ResourcePackEvent implements Listener {

    @EventHandler
    public void onResourcePackStatus(ResourcePackStatusEvent e){
        Player p = e.getPlayer();
        if(e.getStatus() == Status.DECLINED){
            Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    PlayerUtils.kickPlayer(p, "&9ResourcePacks> &aYou must accept the resourcepack in order to play, more information here: goo.gl/FRpFiK");
                }
            });

        } else if(e.getStatus() == Status.ACCEPTED){
            ChatUtils.msgPlayerTag(p, "ResourcePacks", "Download accepted! &oStarting download...");
        } else if(e.getStatus() == Status.SUCCESSFULLY_LOADED){
            ChatUtils.msgPlayerTag(p, "ResourcePacks", "Download complete! :D");
        } else if(e.getStatus() == Status.FAILED_DOWNLOAD){
            ChatUtils.msgPlayerTag(p, "ResourcePacks", "Download failed! D: &oRelog to retry...");
        }
    }

}
