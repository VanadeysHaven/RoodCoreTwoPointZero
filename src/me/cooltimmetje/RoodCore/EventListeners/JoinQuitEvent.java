package me.cooltimmetje.RoodCore.EventListeners;

import com.evilmidget38.UUIDFetcher;
import io.puharesource.mc.titlemanager.api.animations.AnimationFrame;
import io.puharesource.mc.titlemanager.api.animations.FrameSequence;
import io.puharesource.mc.titlemanager.api.animations.TitleAnimation;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.MysqlManager.Database;
import me.cooltimmetje.RoodCore.Utilities.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Arrays;
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

        if(!authencate(p)){
            if(p.getName().equals("mars") || p.getName().equals("martijn113")){
                ChatUtils.msgPlayerTag(p, "Authencate", "&lWarning: This a account is a cracked account! " +
                        "But since you are on a trusted cracked account you have access to this server!");
            } else {
                p.kickPlayer(MiscUtils.color("&9&lAuthencate> &a&lThis account is cracked and not whitelisted, if this account is a premium: " +
                        "&d&lTry again or check if the mojang servers are down: &3&lxpaw.ru/mcstatus"));
                return;
            }
        } else {
            ChatUtils.msgPlayerTag(p, "Authencate", "Authencation success! Welcome!");
        }

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

        int pack = DataClass.resourcePack.get(p.getName());
        String url = DataClass.resourceURL.get(pack);
        if(url != null){
            ChatUtils.msgPlayerTag(p, "ResourcePacks", "Attempting to send you the resourcepack in 5 seconds! You must click yes when prompted.");
            ChatUtils.msgPlayerTag(p, "ResourcePacks", "Downloading can take a while depending on your internet speed!");
            ScheduleUtils.scheduleTask(100, new Runnable() {
                @Override
                public void run() {
                    PlayerUtils.setResourcePack(p, url);
                    p.setPlayerListName(p.getDisplayName());
                    TitleUtils.setTabList(p);
                }
            });
        }


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        Database.saveData(p);
        DataClass.tokens.remove(p.getName());
        DataClass.tokensTime.remove(p.getName());
        DataClass.experiencePoint.remove(p.getName());
        DataClass.chestsTime.remove(p.getName());
        DataClass.normalChests.remove(p.getName());
    }

    private boolean authencate(Player p){
        String name, uuid = null;
        name = p.getName();
        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
            return true;
        } catch (Exception e) {
            Main.getPlugin().getLogger().info(p.getName() + " is not a premium account!");
            return false;
        }
    }


}