package me.cooltimmetje.RoodCore.Utilities;

import com.evilmidget38.UUIDFetcher;
import me.cooltimmetje.RoodCore.Core.DataClass;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

/**
 * This class has been created on 13-5-2015 at 20:55 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class PlayerUtils {

    public static String getUUID(Player p) {
        String name, uuid = null;
        name = p.getName();

        if(name.equals("mars")){
            uuid = "e153e9fa-cbde-4138-8923-33db0bd632b3";
            return uuid;
        } else if(name.equals("martijn113")){
            uuid = "9f752ed1-3267-4c88-80c7-b0401fe5312c";
            return uuid;
        }

        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

    public static void addTokens(Player p, Integer add, String reason){
        int tokensOld = DataClass.tokens.get(p.getName());
        int tokensNew = tokensOld + add;
        DataClass.tokens.put(p.getName(), tokensNew);
        TitleUtils.sendAction(p, "&9+" + add + " tokens! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&9+" + add + " tokens! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
    }

    public static void addXpPoint(Player p, Integer add, String reason){
        int pointsOld = DataClass.experiencePoint.get(p.getName());
        int pointsNew = pointsOld + add;
        DataClass.experiencePoint.put(p.getName(), pointsNew);
        TitleUtils.sendAction(p, "&d+" + add + " xp points! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&d+" + add + " xp points! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
    }

    public static void addXpLevel(Player p, Integer add, String reason){
        int levelOld = p.getLevel();
        int levelNew = levelOld + add;
        p.setLevel(levelNew);
        TitleUtils.sendAction(p, "&3+" + add + " xp levels! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&3+" + add + " xp levels! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
    }

    public static void removeXpPoint(Player p, Integer remove, String reason){
        int pointsOld = DataClass.experiencePoint.get(p.getName());
        int pointsNew = pointsOld - remove;
        DataClass.experiencePoint.put(p.getName(), pointsNew);
        TitleUtils.sendAction(p, "&d-" + remove + " xp points! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&d-" + remove + " xp points! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
    }

    public static void removeXpLevel(Player p, Integer remove, String reason){
        int levelOld = p.getLevel();
        int levelNew = levelOld - remove;
        p.setLevel(levelNew);
        TitleUtils.sendAction(p, "&3-" + remove + " xp levels! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&3-" + remove + " xp levels! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
    }
}



