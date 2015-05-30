package me.cooltimmetje.RoodCore.Core;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityPortalEnterEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 14-5-2015 at 11:01 by cooltimmetje.
 */

public class DataClass {

    public static HashMap<Integer, String> announce = new HashMap<>();
    public static HashMap<String, Integer> tokens = new HashMap<>();
    public static HashMap<String, Integer> tokensTime = new HashMap<>();
    public static HashMap<String, Integer> experiencePoint = new HashMap<>();
    public static HashMap<String, Integer> resourcePack = new HashMap<>();
    public static HashMap<Integer, String> resourceURL = new HashMap<>();
    public static HashMap<Integer, HashMap> resourceList = new HashMap<>();
    public static HashMap<String, String> resource1 = new HashMap<>();
    public static HashMap<String, String> resource2 = new HashMap<>();
    public static HashMap<String, String> resource3 = new HashMap<>();
    public static HashMap<String, Integer> nameID = new HashMap<>();
    public static HashMap<Integer, String> idName = new HashMap<>();
    public static HashMap<Integer, String> idColor = new HashMap<>();
    public static HashMap<Integer, String> colorCodes = new HashMap<>();
    public static HashMap<Integer, String> updates = new HashMap<>();
    public static HashMap<String, Integer> chestsTime = new HashMap<>();
    public static HashMap<String, Integer> normalChests = new HashMap<>();
    public static HashMap<String, Integer> epicChest = new HashMap<>();
    public static HashMap<String, Integer> legendChest = new HashMap<>();

    public static ArrayList<String> updatesList = new ArrayList<>();

    public static int tokensIncrement = 20;
    public static int tokenDoubleChance = 10;
    public static int tokenTripleChance = 1;
    public static int tokenTime = 15;

    public static int announceTicks = 6000;
    public static int announceStrings = 5;
    public static int rankupCost = 300;

    public static int chestTime = 60;
    public static int chestIncrement = 1;
    public static int chestDoubleChance = 10;
    public static int chestTripeChance = 1;
    public static int chestCost = 75;


    public static void setAnnounce() {
        announce.put(1, "Your profile is saved every 10 minutes, or when you leave.");
        announce.put(2, "Tokens are given every 15 minutes!");
        announce.put(3, "This server is still in developement! Please report any bugs you find.");
        announce.put(4, "Did you know: You can store your XP! Type &o/xp &afor information on how to do it!");
        announce.put(5, "Equip one of our custom-made resourcepacks! Type &o/rp <id>");
    }

    public static void setResourceURL() {
        resourceURL.put(1, "https://www.dropbox.com/s/kgw83iyhxzn81ql/RoodMusic1.zip?dl=1");
        resourceURL.put(2, "https://www.dropbox.com/s/9a006cyx0d1g8ea/RoodMusic2.zip?dl=1");
        resourceURL.put(3, "https://www.dropbox.com/s/fo7ir54goa48s0w/RoodMusic3.zip?dl=1");
    }

    public static void setResourceList() {
        resourceList.put(1, resource1);
        resourceList.put(2, resource2);
        resourceList.put(3, resource3);
    }

    public static void setResource1() {
        resource1.put("GOLD_RECORD", "Darude - Sandstorm");
        resource1.put("GREEN_RECORD", "K3 - Mama S�");
        resource1.put("RECORD_3", "New Kids - Turbo (ft. DJ Paul Elstak)");
        resource1.put("RECORD_4", "Star Wars - The Imperial March");
        resource1.put("RECORD_5", "DJ Snake - Lean On (ft. M�)");
        resource1.put("RECORD_6", "Pegboard Nerds - Hero (ft. Elizaveta)");
        resource1.put("RECORD_7", "Avicii - The Nights");
        resource1.put("RECORD_8", "Lets Be Friends - FTW");
        resource1.put("RECORD_9", "Stephen Walking - Dads In Space");
        resource1.put("RECORD_10", "Pixl - The Escape");
        resource1.put("RECORD_11", "DVBBS and Borgeous - TSUNAMI");
        resource1.put("RECORD_12", "Meneer Mand - MAND!");
    }

    public static void setResource2() {
        resource2.put("GOLD_RECORD", "Deorro - Five Hours");
        resource2.put("GREEN_RECORD", "Rick Astley - Never Gonna Give You Up");
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

    public static void setResource3() {
        resource3.put("GOLD_RECORD", "Martin Garrix and Tiesto - The Only Way Is Up");
        resource3.put("GREEN_RECORD", "Axwell and Ingrosso - On My Way");
        resource3.put("RECORD_3", "Mr. Belt and Wezol - Finally");
        resource3.put("RECORD_4", "James Bay - Hold Back The River");
        resource3.put("RECORD_5", "Steve Angello - The Power [Live at Ultra Miami 2015]");
        resource3.put("RECORD_6", "Michael Calfan - Treasured Soul (Kryder and Genairo Nvilla Remix)");
        resource3.put("RECORD_7", "Tim Berg - Bromance (Avicii's Arena Mix)");
//        resource3.put("RECORD_8", "Hellberg - The Girl (feat. Cozi Zuehlsdorff)");
//        resource3.put("RECORD_9", "Muzzy - Get Crazy");
//        resource3.put("RECORD_10", "Echosmith - Cool Kids");
//        resource3.put("RECORD_11", "Mark Ronson - Uptown Funk (ft. Bruno Mars)");
//        resource3.put("RECORD_12", "");
    }

    public static void setColorCodes(){
        colorCodes.put(1, "0");
        colorCodes.put(2, "1");
        colorCodes.put(3, "2");
        colorCodes.put(4, "3");
        colorCodes.put(5, "4");
        colorCodes.put(6, "5");
        colorCodes.put(7, "6");
        colorCodes.put(8, "7");
        colorCodes.put(9, "8");
        colorCodes.put(10, "9");
        colorCodes.put(11, "a");
        colorCodes.put(12, "b");
        colorCodes.put(13, "c");
        colorCodes.put(14, "d");
        colorCodes.put(15, "e");
        colorCodes.put(16, "f");
    }

    public static void listRanks() {
        if (nameID.isEmpty()) {
            nameID.put("User", 1);
            nameID.put("UserPlus", 2);
            nameID.put("UserPlusPlus", 3);
            nameID.put("Noob", 4);
            nameID.put("NoobPlus", 5);
            nameID.put("NoobPlusPlus", 6);
            nameID.put("Bruh", 7);
            nameID.put("BruhPlus", 8);
            nameID.put("BruhPlusPlus", 9);
            nameID.put("Miner", 10);
            nameID.put("MinerPlus", 11);
            nameID.put("MinerPlusPlus", 12);
            nameID.put("Mand", 13);
            nameID.put("MandPlus", 14);
            nameID.put("MandPlusPlus", 15);
            nameID.put("Tichelaar", 16);
            nameID.put("TichelaarPlus", 17);
            nameID.put("TichelaarPlusPlus", 18);
            nameID.put("Makkum", 19);
            nameID.put("MakkumPlus", 20);
            nameID.put("MakkumPlusPlus", 21);
            nameID.put("Vip", 22);
            nameID.put("VipPlus", 23);
            nameID.put("VipPlusPlus", 24);
            nameID.put("Mvp", 25);
            nameID.put("MvpPlus", 26);
            nameID.put("MvpPlusPlus", 27);
            nameID.put("Ultra", 28);
            nameID.put("UltraPlus", 29);
            nameID.put("UltraPlusPlus", 30);
            nameID.put("Hero", 31);
            nameID.put("HeroPlus", 32);
            nameID.put("HeroPlusPlus", 33);
            nameID.put("Legend", 34);
            nameID.put("LegendPlus", 35);
            nameID.put("LegendPlusPlus", 36);
            nameID.put("Air", 37);
            nameID.put("AirPlus", 38);
            nameID.put("AirPlusPlus", 39);
            nameID.put("Earth", 40);
            nameID.put("EarthPlus",41);
            nameID.put("EarthPlusPlus", 42);
            nameID.put("Water", 43);
            nameID.put("WaterPlus", 44);
            nameID.put("WaterPlusPlus", 45);

        }
        if (idName.isEmpty()) {
            idName.put(1, "User");
            idName.put(2, "UserPlus");
            idName.put(3, "UserPlusPlus");
            idName.put(4, "Noob");
            idName.put(5, "NoobPlus");
            idName.put(6, "NoobPlusPlus");
            idName.put(7, "Bruh");
            idName.put(8, "BruhPlus");
            idName.put(9, "BruhPlusPlus");
            idName.put(10, "Miner");
            idName.put(11, "MinerPlus");
            idName.put(12, "MinerPlusPlus");
            idName.put(13, "Mand");
            idName.put(14, "MandPlus");
            idName.put(15, "MandPlusPlus");
            idName.put(16, "Tichelaar");
            idName.put(17, "TichelaarPlus");
            idName.put(18, "TichelaarPlusPlus");
            idName.put(19, "Makkum");
            idName.put(20, "MakkumPlus");
            idName.put(21, "MakkumPlusPlus");
            idName.put(22, "Vip");
            idName.put(23, "VipPlus");
            idName.put(24, "VipPlusPlus");
            idName.put(25, "Mvp");
            idName.put(26, "MvpPlus");
            idName.put(27, "MvpPlusPlus");
            idName.put(28, "Ultra");
            idName.put(29, "UltraPlus");
            idName.put(30, "UltraPlusPlus");
            idName.put(31, "Hero");
            idName.put(32, "HeroPlus");
            idName.put(33, "HeroPlusPlus");
            idName.put(34, "Legend");
            idName.put(35, "LegendPlus");
            idName.put(36, "LegendPlusPlus");
            idName.put(37, "Air");
            idName.put(38, "AirPlus");
            idName.put(39, "AirPlusPlus");
            idName.put(40, "Earth");
            idName.put(41, "EarthPlus");
            idName.put(42, "EarthPlusPlus");
            idName.put(43, "Water");
            idName.put(44, "WaterPlus");
            idName.put(45, "WaterPlusPlus");
        }
        if (idColor.isEmpty()) {
            idColor.put(1, "&8[&7User&8]");
            idColor.put(2, "&8[&7User&6+&8]");
            idColor.put(3, "&8[&7User&6+&c+&8]");
            idColor.put(4, "&8[&dNoob&8]");
            idColor.put(5, "&8[&dNoob&6+&8]");
            idColor.put(6, "&8[&dNoob&6+&c+&8]");
            idColor.put(7, "&8[&9Bruh&8]");
            idColor.put(8, "&8[&9Bruh&6+&8]");
            idColor.put(9, "&8[&9Bruh&6+&c+&8]");
            idColor.put(10, "&8[&eMiner&8]");
            idColor.put(11, "&8[&eMiner&6+&8]");
            idColor.put(12, "&8[&eMiner&6+&c+&8]");
            idColor.put(13, "&8[&2Mand&8]");
            idColor.put(14, "&8[&2Mand&6+&8]");
            idColor.put(15, "&8[&2Mand&6+&c+&8]");
            idColor.put(16, "&8[&3Tichelaar&8]");
            idColor.put(17, "&8[&3Tichelaar&6+&8]");
            idColor.put(18, "&8[&3Tichelaar&6+&c+&8]");
            idColor.put(19, "&8[&5Makkum&8]");
            idColor.put(20, "&8[&5Makkum&6+&8]");
            idColor.put(21, "&8[&5Makkum&6+&c+&8]");
            idColor.put(22, "&8[&1Vip&8]");
            idColor.put(23, "&8[&1Vip&6+&8]");
            idColor.put(24, "&8[&1Vip&6+&c+&8]");
            idColor.put(25, "&8[&bMvp&8]");
            idColor.put(26, "&8[&bMvp&6+&8]");
            idColor.put(27, "&8[&bMvp&6+&c+&8]");
            idColor.put(28, "&8[&aUltra&8]");
            idColor.put(29, "&8[&aUltra&6+&8]");
            idColor.put(30, "&8[&aUltra&6+&c+&8]");
            idColor.put(31, "&8[&6Hero&8]");
            idColor.put(32, "&8[&6Hero&6+&8]");
            idColor.put(33, "&8[&6Hero&6+&c+&8]");
            idColor.put(34, "&8[&aLegend&8]");
            idColor.put(35, "&8[&aLegend&6+&8]");
            idColor.put(36, "&8[&aLegend&6+&c+&8]");
            idColor.put(37, "&8[&fAir&8]");
            idColor.put(38, "&8[&fAir&6+&8]");
            idColor.put(39, "&8[&fAir&6+&c+&8]");
            idColor.put(40, "&8[&2Earth&8]");
            idColor.put(41, "&8[&2Earth&6+&8]");
            idColor.put(42, "&8[&2Earth&6+&c+&8]");
            idColor.put(43, "&8[&9Water&8]");
            idColor.put(44, "&8[&9Water&6+&8]");
            idColor.put(45, "&8[&9Water&6+&c+&8]");
        }
    }

    private static void listUpdates(){
        int i = 1;
        for(String s : updatesList){
            updates.put(i, s);
            i = i + 1;
        }
    }

    public static void setUpdatesList() {
        updatesList.add("+ You can now see \nthe amount of \nchests you have in \nthe TAB list.");
        updatesList.add("/ Shooting items \nhave been temp \ndisabled due to \na unfixable bug.");
        updatesList.add("+ You can now \nchange the time \nand weather by \nRClicking a clock!");
        updatesList.add("* The issue where \nyou could pickup \nshot items after \nrelogging is gone!");
        updatesList.add("+ You can now see \nthe 5 most recent \nrankups below!");
        updatesList.add("+ You now gain \nMystery Chests \nevery hour!");
        updatesList.add("/ The way this \nboard now works \nin the code allows \nfor easier change");
        updatesList.add("* Fixed a bug \nwhere this \nboard would not \nupdate properly");
        updatesList.add("/ Tweaked the \nvelocity of shot \nitems a bit");
        updatesList.add("/ The albums from \nJukeboxes now \nshoot from the \nJukebox itself.");
        updatesList.add("+ This board now \nshows the \nupdates \nautomagically!");
        updatesList.add("+ Diamonds now \nshoot from your \nhead when you \ngain tokens!");
        updatesList.add("+ New song! (rp 3) \nTim Berg - Bromance (Aviciis \nArena Remix)");
        updatesList.add("+ New staff \nmember! Reges is \nnow a MOD");
        updatesList.add("/ Our IP has \nchanged! Ask \nCooltimmetje for \nthe new one!");
        updatesList.add("* The issue with \nThoThoKill's \nbirthday has been \nresolved.");
        updatesList.add("- ThoThoKill's \nBirthday!");
        updatesList.add("+ New Ranks: \n&9Water \n&9Water&6+ \n&9Water&6+&c+");
        updatesList.add("+ A Hologram for \nthis board!");

        listUpdates();
    }



}
