package me.cooltimmetje.RoodCore.Utilities;

import java.util.Random;

/**
 * This class has been created on 13-5-2015 at 21:21 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class MiscUtils {

    public static String color(String s){
        String colorString = s.replace('&', '\u00A7');
        return colorString;
    }

    public static int randomInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
