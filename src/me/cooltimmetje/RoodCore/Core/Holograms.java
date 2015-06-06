package me.cooltimmetje.RoodCore.Core;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * This class has been created on 1-6-2015 at 20:58 by cooltimmetje.
 */
public class Holograms {

    public static ArrayList<Hologram> holograms = new ArrayList<>();

    public static void setHolograms() {
        Hologram roodHolo = HologramsAPI.createHologram(Main.getPlugin(), Bukkit.getWorld("Survival").
        getBlockAt(-252,74,198).getLocation().add(0.5,0.9,0.5));

        StringBuilder topLine = new StringBuilder();
        for(int i = 1; i<=7; i++){
            if(i != 4){
                if(i < 4) {
                    topLine.append("&" + MiscUtils.randomColor() + "\u00BB");
                } else {
                    topLine.append("&" + MiscUtils.randomColor() + "\u00AB");
                }
            } else {
                topLine.append("&8[&aTown&8]");
            }
        }

        roodHolo.appendTextLine(MiscUtils.color(topLine.toString().trim()));
        roodHolo.appendTextLine(MiscUtils.color("&aWelcome to: &4R00D&cTown!"));
        roodHolo.appendTextLine(MiscUtils.color("&aMayor: &6mars"));

        holograms.add(roodHolo);
    }

    public static void removeHolograms(){
        for(Hologram holo : holograms){
            holo.delete();
        }
    }
}
