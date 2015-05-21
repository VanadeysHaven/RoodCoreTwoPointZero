package me.cooltimmetje.RoodCore.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * This class has been created on 21-5-2015 at 21:02 by cooltimmetje.
 */
public class WorldUtils {

    public static void shootItem(Location loc, Material m, int amount){
        int ticks = 2;
        for(int i = 0; i < amount; i++){
            ScheduleUtils.scheduleTask(ticks, new Runnable() {
                @Override
                public void run() {
                    Bukkit.getWorld("Survival").playSound(loc, Sound.CHICKEN_EGG_POP, 100, 1);
                    Entity item = Bukkit.getWorld("Survival").dropItemNaturally(loc, new ItemStack(m, 64));
                    PlayerUtils.shotItems.add(item);

                    item.setVelocity(new Vector(MiscUtils.randomInt(-1, 1)/2, MiscUtils.randomInt(0, 1)/2, MiscUtils.randomInt(-1, 1)/2));
                    ScheduleUtils.scheduleTask(100, new Runnable() {
                        @Override
                        public void run() {
                            item.remove();
                            PlayerUtils.shotItems.remove(item);
                        }
                    });
                }
            });
            ticks = ticks + 2;

        }
    }

}
