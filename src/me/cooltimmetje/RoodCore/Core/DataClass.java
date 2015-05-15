package me.cooltimmetje.RoodCore.Core;

import io.puharesource.mc.titlemanager.api.animations.AnimationFrame;
import me.cooltimmetje.RoodCore.Utilities.TitleUtils;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 14-5-2015 at 11:01 by cooltimmetje.
 */

public class DataClass {

    public static HashMap<Integer,String> announce = new HashMap<>();
    public static HashMap<String,Integer> tokens = new HashMap<>();
    public static HashMap<String,Integer> tokensTime = new HashMap<>();
    public static HashMap<String,Integer> experiencePoint = new HashMap<>();


    public static int tokensIncrement = 20;
    public static int tokenDoubleChance = 10;
    public static int tokenTripleChance = 1;
    public static int tokenTime = 15;
    public static int announceTicks = 6000;
    public static int announceStrings = 4;

    public static void listAnnounce() {
        announce.put(1, "Your profile is saved every 5 minutes, or when you leave.");
        announce.put(2, "Tokens are given every 15 minutes!");
        announce.put(3, "This server is still in developement! Please report any bugs you find.");
        announce.put(4, "Did you know: You can store your XP! Type &o/xp &afor information on how to do it!");
    }

}
