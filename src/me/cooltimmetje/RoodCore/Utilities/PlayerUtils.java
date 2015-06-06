package me.cooltimmetje.RoodCore.Utilities;

import com.evilmidget38.UUIDFetcher;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Core.UpdatesBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

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
        PlayerUtils.shootItem(p, Material.DIAMOND, add);
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

    public static void addChest(Player p, Integer add, String reason){
        int chestOld = DataClass.normalChests.get(p.getName());
        int chestNew = chestOld + add;
        DataClass.normalChests.put(p.getName(), chestNew);
        TitleUtils.sendAction(p, "&e+" + add + " normal chests! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&e+" + add + " normal chests! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
        PlayerUtils.shootItem(p, Material.CHEST, add);
    }

    public static void removeChest(Player p, Integer remove, String reason){
        int chestOld = DataClass.normalChests.get(p.getName());
        int chestNew = chestOld - remove;
        DataClass.normalChests.put(p.getName(), chestNew);
        TitleUtils.sendAction(p, "&e-" + remove + " normal chests! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&e-" + remove + " normal chests! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
        PlayerUtils.shootItem(p, Material.CHEST, remove);
    }

    public static void addEpic(Player p, Integer add, String reason){
        int chestOld = DataClass.epicChest.get(p.getName());
        int chestNew = chestOld + add;
        DataClass.epicChest.put(p.getName(), chestNew);
        TitleUtils.sendAction(p, "&5+" + add + " epic chests! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&5+" + add + " epic chests! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
        PlayerUtils.shootItem(p, Material.CHEST, add);
    }

    public static void removeEpic(Player p, Integer remove, String reason){
        int chestOld = DataClass.epicChest.get(p.getName());
        int chestNew = chestOld - remove;
        DataClass.epicChest.put(p.getName(), chestNew);
        TitleUtils.sendAction(p, "&5-" + remove + " epic chests! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&5-" + remove + " epic chests! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
        PlayerUtils.shootItem(p, Material.CHEST, remove);
    }

    public static void addLegend(Player p, Integer add, String reason){
        int chestOld = DataClass.legendChest.get(p.getName());
        int chestNew = chestOld + add;
        DataClass.legendChest.put(p.getName(), chestNew);
        TitleUtils.sendAction(p, "&c+" + add + " legendary chests! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&c+" + add + " legendary chests! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
        PlayerUtils.shootItem(p, Material.CHEST, add);
    }

    public static void removeLegend(Player p, Integer remove, String reason){
        int chestOld = DataClass.legendChest.get(p.getName());
        int chestNew = chestOld - remove;
        DataClass.legendChest.put(p.getName(), chestNew);
        TitleUtils.sendAction(p, "&c-" + remove + " legendary chests! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&c-" + remove + " legendary chests! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
        PlayerUtils.shootItem(p, Material.CHEST, remove);
    }

    public static void removeTokens(Player p, Integer remove, String reason){
        int tokensOld = DataClass.tokens.get(p.getName());
        int tokensNew = tokensOld - remove;
        DataClass.tokens.put(p.getName(), tokensNew);
        TitleUtils.sendAction(p, "&9-" + remove + " tokens! (" + reason + ")");
        ChatUtils.msgPlayer(p, "&9-" + remove + " tokens! (" + reason + ")");
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

    public static void setResourcePack(Player p, String url){
        ChatUtils.msgPlayerTag(p, "ResourcePacks", "Sending ResourcePack...");
        p.setResourcePack(url);
    }

    public static void kickPlayer(Player p, String reason){
        p.kickPlayer(MiscUtils.color(reason));
    }

    public static String getGroup(Player p){
        String displayName = p.getDisplayName();

        int i = displayName.indexOf(' ');
        String groupName = displayName.substring(0, i);

        groupName = ChatColor.stripColor(groupName.replace('[', ' ').replace(']', ' ').replace("'", " ").replace("+", "Plus").trim());

        return groupName;
    }

    public static void rainbowDisplayName(Player p){
        String displayName = ChatColor.stripColor(p.getDisplayName());

        String[] split = displayName.split(" ");
        String name = split[1];

        StringBuilder sb = new StringBuilder();
        for(int x = 0; x < name.length(); x++){
            sb.append("&" + MiscUtils.randomColor() + name.charAt(x));
        }

        p.setDisplayName(MiscUtils.color(p.getDisplayName().replace(name, sb.toString().trim())));
    }

    public static void rankUp(Player p) {
        String curGroupName = PlayerUtils.getGroup(p).trim();
        String curGroupColor = DataClass.idColor.get(DataClass.nameID.get(curGroupName));

        String nextGroupName = DataClass.idName.get(DataClass.nameID.get(curGroupName) + 1);
        String nextGroupColor = DataClass.idColor.get(DataClass.nameID.get(curGroupName) + 1);

        for(Player pl : Bukkit.getOnlinePlayers()){
            TitleUtils.sendTitle("&9Rankup> &c&l" + p.getName() + " &a&lRANKED UP! &6&lGG!", curGroupColor + " &b-> " + nextGroupColor, 20, 100, 20, pl);
            ChatUtils.msgPlayer(pl, "&9RankUp> &c&l" + p.getName() + " &a&lRANKED UP! &6&lGG!");
            pl.playSound(pl.getLocation(), Sound.ENDERDRAGON_DEATH, 100, 1);
        }

        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + nextGroupName);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");

        p.chat("gg");
        p.setPlayerListName(p.getDisplayName());
//        UpdatesBoard.updateRecentRankup(p, curGroupColor, nextGroupColor);
    }

    public static void shootItem(Player p, Material m, int amount){
        int ticks = 2;
        for(int i = 0; i < amount; i++){
            ScheduleUtils.scheduleTask(ticks, new Runnable() {
                @Override
                public void run() {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 100, 1);
                    Item item = Bukkit.getWorld(p.getWorld().getName()).dropItemNaturally(p.getLocation().add(0, 3, 0), new ItemStack(m, 64));

                    item.setPickupDelay(32767);

                    item.setVelocity(new Vector(MiscUtils.randomInt(-1, 1)/2, MiscUtils.randomInt(0, 1)/2, MiscUtils.randomInt(-1, 1)/2));
                    ScheduleUtils.scheduleTask(100, new Runnable() {
                        @Override
                        public void run() {
                            item.remove();
                        }
                    });
                }
            });
            ticks = ticks + 2;

        }
    }

}



