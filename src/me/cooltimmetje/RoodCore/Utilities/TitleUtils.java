package me.cooltimmetje.RoodCore.Utilities;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TabTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import io.puharesource.mc.titlemanager.api.animations.AnimationFrame;
import io.puharesource.mc.titlemanager.api.animations.FrameSequence;
import io.puharesource.mc.titlemanager.api.animations.TitleAnimation;
import me.cooltimmetje.RoodCore.Core.DataClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has been created on 14-5-2015 at 18:07 by cooltimmetje.
 */

@SuppressWarnings("unused")
    public class TitleUtils {

    public static void sendTitle(String title, String subTitle, int fadeIn, int stay, int fadeOut, Player p) {
        new TitleObject(MiscUtils.color(title), MiscUtils.color(subTitle)).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendMain(Player p, String title, int fadeIn, int stay, int fadeOut) {
        new TitleObject(MiscUtils.color(title), TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendSub(Player p, String title, int fadeIn, int stay, int fadeOut) {
        new TitleObject(MiscUtils.color(title), TitleObject.TitleType.SUBTITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendAction(Player p, String title) {
        new ActionbarTitleObject(MiscUtils.color(title)).send(p);
    }

    public static void sendActionTag(Player p, String tag, String title) {
        new ActionbarTitleObject(MiscUtils.color("&9" + tag + "> &a" + title)).send(p);
    }

    public static AnimationFrame createAnimationFrame(String text, int fadeIn, int stay, int fadeOut) {
        return new AnimationFrame(MiscUtils.color(text), fadeIn, stay, fadeOut);
    }

    public static FrameSequence createFrameSequence(ArrayList<AnimationFrame> frames) {
        return new FrameSequence(frames);
    }

    public static TitleAnimation createTitleAnimation(FrameSequence title, FrameSequence subtitle) {
        return new TitleAnimation(title, subtitle);
    }

    public static void setTabList(Player p) {

        StringBuilder topLine = new StringBuilder();
        for(int i = 1; i<=7; i++){
            if(i != 4){
                if(i < 4) {
                    topLine.append("&" + MiscUtils.randomColor() + "\u00BB");
                } else {
                    topLine.append("&" + MiscUtils.randomColor() + "\u00AB");
                }
            } else {
                topLine.append(" &cThe &4#TeamR00D &cNetwork ");
            }
        }

        String header = topLine.toString().trim() + "\n&aPlayers online: &8(&6" + Bukkit.getOnlinePlayers().size() + "&8/&6" + Bukkit.getMaxPlayers() + "&8)";
        String footer = p.getDisplayName() + " &b- &9" + DataClass.tokens.get(p.getName()) + " tokens\n&d" + DataClass.experiencePoint.get(p.getName()) + " XP Points &b- &eResourcePack " +
                DataClass.resourcePack.get(p.getName());
        new TabTitleObject(MiscUtils.color(header), MiscUtils.color(footer)).send(p);
    }
}

