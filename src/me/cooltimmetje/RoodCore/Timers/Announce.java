package me.cooltimmetje.RoodCore.Timers;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;

/**
 * This class has been created on 14-5-2015 at 11:14 by cooltimmetje.
 */
public class Announce {

    public static void announcer() {
        DataClass.listAnnounce();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                ChatUtils.broadcastTag("AutoMessage", DataClass.announce.get(MiscUtils.randomInt(1, DataClass.announceStrings)));
            }
        }, DataClass.announceTicks, DataClass.announceTicks);
    }

}
