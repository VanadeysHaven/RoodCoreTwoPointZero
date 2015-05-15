package me.cooltimmetje.RoodCore.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 13-5-2015 at 21:20 by cooltimmetje.
 */
public class ChatUtils {

    public static void msgPlayerTag(Player p, String tag, String msg){
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + msg));
    }

    public static void msgPlayerTodo(Player p, String msg){
        p.sendMessage(MiscUtils.color("&7&oTODO> " + msg));
    }

    public static void msgPlayer(Player p, String msg){
        p.sendMessage(MiscUtils.color(msg));
    }

    public static void broadcastTag(String tag, String msg){
        Bukkit.broadcastMessage(MiscUtils.color("&9" + tag + "&9> &a" + msg));
    }



    public static String error = "&c&lERROR! &a";


}
