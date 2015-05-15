package me.cooltimmetje.RoodCore.EventListeners;

import io.puharesource.mc.titlemanager.api.animations.AnimationFrame;
import io.puharesource.mc.titlemanager.api.animations.FrameSequence;
import io.puharesource.mc.titlemanager.api.animations.TitleAnimation;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.MysqlManager.Database;
import me.cooltimmetje.RoodCore.Utilities.ScheduleUtils;
import me.cooltimmetje.RoodCore.Utilities.TitleUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * This class has been created on 14-5-2015 at 11:11 by cooltimmetje.
 */
@SuppressWarnings("all")
public class JoinQuitEvent implements Listener {

    public static ArrayList<AnimationFrame> joinTitleMain = new ArrayList<>();
    public static ArrayList<AnimationFrame> joinTitleSub = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        Database.loadData(p);

        joinTitleMain.clear();
        joinTitleSub.clear();

        joinTitleMain.add(TitleUtils.createAnimationFrame("&6Welcome &c" + p.getName(), 20, 0, 0));
        joinTitleMain.add(TitleUtils.createAnimationFrame("&6Welcome &c" + p.getName(), 0, 100, 20));
        joinTitleSub.add(TitleUtils.createAnimationFrame(" ", 0, 20, 0));
        joinTitleSub.add(TitleUtils.createAnimationFrame("&6to &cThe &4#TeamRood &cNetwork&6!", 20, 100, 20));

        FrameSequence frameMain = TitleUtils.createFrameSequence(joinTitleMain);
        FrameSequence frameSub = TitleUtils.createFrameSequence(joinTitleSub);

        TitleAnimation joinTitle = TitleUtils.createTitleAnimation(frameMain, frameSub);

        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                joinTitle.send(p);
            }
        });

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        Database.saveData(p);
        DataClass.tokens.remove(p.getName());
        DataClass.tokensTime.remove(p.getName());
        DataClass.experiencePoint.remove(p.getName());
    }

}