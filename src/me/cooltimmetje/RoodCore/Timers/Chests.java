package me.cooltimmetje.RoodCore.Timers;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import me.cooltimmetje.RoodCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 21-5-2015 at 20:16 by cooltimmetje.
 */
public class Chests {

    public static void chestsGiver(){
        ScheduleUtils.repeatTask(20, 1200, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    int curTime = DataClass.chestsTime.get(p.getName());
                    int chance = MiscUtils.randomInt(0, 100);
                    int newTime;

                    if(curTime == 0){
                        if(chance <= DataClass.chestDoubleChance){
                            if(chance <= DataClass.chestTripeChance){
                                PlayerUtils.addChest(p, DataClass.chestIncrement * 3, "1 hour online, OH BABY A TRIPLE!");
                                ChatUtils.broadcastTag("Chests", "&lOH BABY A TRIPLE! " +  p.getDisplayName() + " &ajust got lucky and gained triple chests!");
                            } else {
                                PlayerUtils.addChest(p, DataClass.chestIncrement * 2, "1 hour online, double gain!");
                                ChatUtils.broadcastTag("Chests", p.getDisplayName() + " &ajust got lucky and gained double chests!");
                            }
                        } else {
                            PlayerUtils.addChest(p, DataClass.chestIncrement, "1 hour online");
                        }
                        newTime = DataClass.chestTime;
                    } else {
                        newTime = curTime - 1;
                    }
                    DataClass.chestsTime.put(p.getName(), newTime);
                }
            }
        });
    }

}
