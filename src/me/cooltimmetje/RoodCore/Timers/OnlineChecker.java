package me.cooltimmetje.RoodCore.Timers;

import com.darkblade12.particleeffect.ReflectionUtils;
import me.cooltimmetje.RoodCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;

/**
 * This class has been created on 15-6-2015 at 19:50 by cooltimmetje.
 */
public class OnlineChecker {

    private static boolean emptySinceLastCheck = false;

    public static void onlineChecker(){
        ScheduleUtils.repeatTask(20, 2400, () -> {
           if(!emptySinceLastCheck){
               emptySinceLastCheck = Bukkit.getOnlinePlayers().size() == 0;
           } else {
               if(Bukkit.getOnlinePlayers().size() == 0){
                   Bukkit.shutdown();
               } else {
                   emptySinceLastCheck = true;
               }
           }
        });
    }

}
