package me.cooltimmetje.RoodCore.Utilities;

import me.cooltimmetje.RoodCore.Main;
import org.bukkit.Bukkit;

/**
 * This class has been created on 14-5-2015 at 21:11 by cooltimmetje.
 */
public class ScheduleUtils {

    public static void scheduleTask(long ticks, Runnable runnable){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), runnable, ticks);
    }

}
