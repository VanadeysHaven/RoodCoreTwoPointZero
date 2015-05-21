package me.cooltimmetje.RoodCore.Core;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * This class has been created on 20-5-2015 at 19:45 by cooltimmetje.
 */
public class UpdatesBoard {

    public static int x = -231;

    public static Hologram hologram;

    public static Location holoLoc = Bukkit.getWorld("Survival").getBlockAt(-231, 78, 219).getLocation();

    public static void updateBoard() {
        int update = 1;

        hologram = HologramsAPI.createHologram(Main.getPlugin(), holoLoc.add(0.5, 0.5, 0.5));

        StringBuilder topLine = new StringBuilder();
        for(int i = 1; i<=7; i++){
            if(i != 4){
                if(i < 4) {
                    topLine.append("&" + MiscUtils.randomColor() + "\u00BB");
                } else {
                    topLine.append("&" + MiscUtils.randomColor() + "\u00AB");
                }
            } else {
                topLine.append("&8[&aServer Updates&8]");
            }
        }

        hologram.appendTextLine(MiscUtils.color(topLine.toString().trim()));

        for(int y = 77; y >= 74; y--){
            for(int z = 217; z <= 221; z++){
                Bukkit.getWorld("Survival").getBlockAt(x, y, z).setType(Material.WALL_SIGN);
                Bukkit.getWorld("Survival").getBlockAt(x, y, z).setData((byte)4);
                Sign sign = (Sign) Bukkit.getWorld("Survival").getBlockAt(x, y, z).getState();
                if(DataClass.updates.get(update) != null){
                    sign.setLine(0, " ");
                    sign.setLine(1, " ");
                    sign.setLine(2, " ");
                    sign.setLine(3, " ");
                    String[] lines = DataClass.updates.get(update).split("\n");
                    for(int i = 0; i < lines.length; i++) {
                        sign.setLine(i, MiscUtils.color(lines[i].trim()));
                    }
                    sign.update();
                } else {
                    sign.setLine(0, " ");
                    if(MiscUtils.randomInt(0, 100) <= 25){
                        sign.setLine(1, "i am a sign! :o");
                    } else {
                        sign.setLine(1, " ");
                    }
                    sign.setLine(2, " ");
                    sign.setLine(3, " ");
                    sign.update();
                }
                update = update + 1;
            }
        }
    }

    public static void updateRecentRankup(Player p, String oldName, String newName){
        for(int z = 221; z > 217; z--){
            Sign signNew = (Sign) Bukkit.getWorld("Survival").getBlockAt(x, 73, z).getState();
            Sign signOld = (Sign) Bukkit.getWorld("Survival").getBlockAt(x, 73, z-1).getState();

            String[] lines = signOld.getLines();
            for(int i = 0; i < lines.length; i++){
                signNew.setLine(i, lines[i]);

            }
            signNew.update();
        }
        Bukkit.getWorld("Survival").getBlockAt(x, 73, 217).setType(Material.WALL_SIGN);
        Bukkit.getWorld("Survival").getBlockAt(x, 73, 217).setData((byte)4);

        Sign sign = (Sign) Bukkit.getWorld("Survival").getBlockAt(x, 73, 217).getState();
        sign.setLine(0, p.getName());
        sign.setLine(1, MiscUtils.color(oldName));
        sign.setLine(3, MiscUtils.color(newName));
        sign.update();
    }

    public static void removeHologram(){
        hologram.delete();
    }

}
