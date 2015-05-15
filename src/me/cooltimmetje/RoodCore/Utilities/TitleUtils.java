package me.cooltimmetje.RoodCore.Utilities;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import io.puharesource.mc.titlemanager.api.animations.AnimationFrame;
import io.puharesource.mc.titlemanager.api.animations.FrameSequence;
import io.puharesource.mc.titlemanager.api.animations.TitleAnimation;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class has been created on 14-5-2015 at 18:07 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class TitleUtils {

    public static void sendTitle(String title, String subTitle, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(MiscUtils.color(title), MiscUtils.color(subTitle)).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendMain(Player p, String title, int fadeIn, int stay, int fadeOut){
        new TitleObject(MiscUtils.color(title), TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendSub(Player p, String title, int fadeIn, int stay, int fadeOut){
        new TitleObject(MiscUtils.color(title), TitleObject.TitleType.SUBTITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendAction(Player p, String title){
        new ActionbarTitleObject(MiscUtils.color(title)).send(p);
    }

    public static void sendActionTag(Player p, String tag, String title){
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


}
