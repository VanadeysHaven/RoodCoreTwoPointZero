package me.cooltimmetje.RoodCore.Core;

import org.bukkit.Material;

import java.util.HashMap;

/**
 * This class has been created on 14-5-2015 at 11:01 by cooltimmetje.
 */

public class DataClass {

    public static HashMap<Integer,String> announce = new HashMap<>();
    public static HashMap<String,Integer> tokens = new HashMap<>();
    public static HashMap<String,Integer> tokensTime = new HashMap<>();
    public static HashMap<String,Integer> experiencePoint = new HashMap<>();
    public static HashMap<String,Integer> resourcePack = new HashMap<>();
    public static HashMap<Integer,String> resourceURL = new HashMap<>();
    public static HashMap<Integer,HashMap> resourceList = new HashMap<>();
    public static HashMap<String,String> resource1 = new HashMap<>();
    public static HashMap<String,String> resource2 = new HashMap<>();


    public static int tokensIncrement = 20;
    public static int tokenDoubleChance = 10;
    public static int tokenTripleChance = 1;
    public static int tokenTime = 15;
    public static int announceTicks = 6000;
    public static int announceStrings = 5;

    public static void setAnnounce() {
        announce.put(1, "Your profile is saved every 10 minutes, or when you leave.");
        announce.put(2, "Tokens are given every 15 minutes!");
        announce.put(3, "This server is still in developement! Please report any bugs you find.");
        announce.put(4, "Did you know: You can store your XP! Type &o/xp &afor information on how to do it!");
        announce.put(5, "Equip one of our custom-made resourcepacks! Type &o/rp <id>");
    }

    public static void setResourceURL(){
        resourceURL.put(1, "https://www.dropbox.com/s/kgw83iyhxzn81ql/RoodMusic1.zip?dl=1");
        resourceURL.put(2, "https://www.dropbox.com/s/9a006cyx0d1g8ea/RoodMusic2.zip?dl=1");
    }

    public static void setResourceList(){
        resourceList.put(1, resource1);
        resourceList.put(2, resource2);
    }

    public static void setResource1(){
        resource1.put("GOLD_RECORD", "Darude - Sandstorm");
        resource1.put("GREEN_RECORD", "K3 - Mama Sé");
        resource1.put("RECORD_3", "New Kids - Turbo (ft. DJ Paul Elstak)");
        resource1.put("RECORD_4", "Star Wars - The Imperial March");
        resource1.put("RECORD_5", "DJ Snake - Lean On (ft. MØ)");
        resource1.put("RECORD_6", "Pegboard Nerds - Hero (ft. Elizaveta)");
        resource1.put("RECORD_7", "Avicii - The Nights");
        resource1.put("RECORD_8", "Lets Be Friends - FTW");
        resource1.put("RECORD_9", "Stephen Walking - Dads In Space");
        resource1.put("RECORD_10", "Pixl - The Escape");
        resource1.put("RECORD_11", "DVBBS and Borgeous - TSUNAMI");
        resource1.put("RECORD_12", "Meneer Mand - MAND!");

    }

    public static void setResource2(){
        resource2.put("GOLD_RECORD", "Deorro - Five Hours");
        resource2.put("GREEN_RECORD", "Rick Astley - Never Gonna Give You U");
        resource2.put("RECORD_3", "Heleentje van Cappelle - En Dan Gaan We Naar De Speeltuin");
        resource2.put("RECORD_4", "Andrew Gold - Spooky Scary Skeletons (Remix)");
        resource2.put("RECORD_5", "Andrew Gold - Spooky Scary Skeletons (Original)");
        resource2.put("RECORD_6", "SKRILLEX - Bangarang (ft. Sirah)");
        resource2.put("RECORD_7", "Pegboard Nerds - Hero (ft. Elizaveta)[Stonebank Remix]");
        resource2.put("RECORD_8", "Hellberg - The Girl (feat. Cozi Zuehlsdorff)");
        resource2.put("RECORD_9", "Muzzy - Get Crazy");
        resource2.put("RECORD_10", "Echosmith - Cool Kids");
        resource2.put("RECORD_11", "Mark Ronson - Uptown Funk (ft. Bruno Mars)");
//        resource2.put("RECORD_12", "");
    }



}
