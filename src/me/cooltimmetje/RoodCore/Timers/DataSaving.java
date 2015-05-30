package me.cooltimmetje.RoodCore.Timers;

import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.MysqlManager.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 14-5-2015 at 17:42 by cooltimmetje.
 */
public class DataSaving {

    public static void dataSaver(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    Database.saveData(p);
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
            }
        }, 12000, 12000);
    }

}
