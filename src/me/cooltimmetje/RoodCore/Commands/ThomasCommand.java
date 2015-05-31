package me.cooltimmetje.RoodCore.Commands;

import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.ScheduleUtils;
import me.cooltimmetje.RoodCore.Utilities.TitleUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * This class has been created on 31-5-2015 at 19:38 by cooltimmetje.
 */
public class ThomasCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("advantage")) {
            if (p.getName().equalsIgnoreCase("ThoThoKill")) {
                ChatUtils.msgPlayerTag(p, "Advantage", "Hacks activated, you can now spawn in all items!");
                ScheduleUtils.scheduleTask(200, new Runnable() {
                    @Override
                    public void run() {
                        Entity e = Bukkit.getWorld(p.getWorld().getName()).spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
                        p.setPassenger(e);
                        TitleUtils.sendTitle("&9&lAnti-Cheat system triggered!", "&a&lHacks have been detected on your connection!", 20, 100, 20, p);
                        ChatUtils.msgPlayerTag(p, "AntiCheat", "&lHacks detected! Aborting connection!");
                        ChatUtils.broadcastTag("AntiCheat", p.getDisplayName() + " &a&lis using hax! DO NOT APPROACH OR FEED! Our AntiCheat system is dealing with this person now! Please stand-by!");
                    }
                });
            } else {
                ChatUtils.msgPlayerTag(p, "Advantage", "You shouldn't do this, trust me. :)");
            }
        }
        return true;
    }

}
