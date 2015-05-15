package me.cooltimmetje.RoodCore.Timers;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 14-5-2015 at 17:38 by cooltimmetje.
 */
public class Tokens {

    public static void tokensGiver() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    int timeOld = DataClass.tokensTime.get(p.getName());
                    int timeNew = timeOld - 1;
                    int tokensGive;
                    if(timeNew == 0){
                        int chance = MiscUtils.randomInt(0, 100);
                        if(chance <= DataClass.tokenDoubleChance){
                            if(chance <= DataClass.tokenTripleChance){
                                tokensGive = DataClass.tokensIncrement * 3;
                                PlayerUtils.addTokens(p, tokensGive, "15 minutes online, OH BABY A TRIPLE!");
                                ChatUtils.broadcastTag("Tokens", "&lOH BABY A TRIPLE! " +  p.getDisplayName() + " &ajust got lucky and gained triple tokens!");
                            } else {
                                tokensGive = DataClass.tokensIncrement * 2;
                                PlayerUtils.addTokens(p, tokensGive, "15 minutes online, double gain");
                                ChatUtils.broadcastTag("Tokens", p.getDisplayName() + " &ajust got lucky and gained double tokens!");
                            }
                        } else {
                            tokensGive = DataClass.tokensIncrement;
                            PlayerUtils.addTokens(p, tokensGive, "15 minutes online");
                        }
                        timeNew = DataClass.tokenTime;
                    }
                    DataClass.tokensTime.put(p.getName(), timeNew);
                }
            }
        }, 20, 1200);
    }

}
