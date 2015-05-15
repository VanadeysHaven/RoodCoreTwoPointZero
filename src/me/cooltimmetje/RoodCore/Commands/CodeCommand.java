package me.cooltimmetje.RoodCore.Commands;

import com.darkblade12.particleeffect.ParticleEffect;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import me.cooltimmetje.RoodCore.Utilities.TitleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * This class has been created on 14-5-2015 at 20:56 by cooltimmetje.
 */
public class CodeCommand implements CommandExecutor{

    // -- CodeTim Variables --
    int cdtimeTim = 150;
    long lastUsedTim;
    String lastUsedByTim;

    // -- CodeRood Variables --
    int cdtimeRood = 150;
    long lastUsedRood;
    String lastUsedByRood;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("codetim")) {
            Player tim = Bukkit.getPlayer("Cooltimmetje");
            if (tim != null) {
                if (MiscUtils.cooldownCheck(lastUsedTim, cdtimeTim)) {
                    shootTim(p, tim);
                    PlayerUtils.addTokens(p, 2, "Shot " + tim.getDisplayName() + " &9into the sky!");
                    PlayerUtils.addTokens(tim, 2, p.getDisplayName() + " &9shot you into the sky!");
                    lastUsedTim = System.currentTimeMillis();
                    lastUsedByTim = p.getName();
                } else {
                    int timeUntilUse = (int) (cdtimeTim - ((System.currentTimeMillis() - lastUsedTim) / 1000));
                    ChatUtils.msgPlayerTag(p, "CodeTim", ChatUtils.error + "This command is still on cooldown! It was last used by: &6"
                            + lastUsedByTim + "&a. It will be available again in: &6" + MiscUtils.formatTime(timeUntilUse));
                }
            } else {
                ChatUtils.msgPlayerTag(p, "CodeTim", ChatUtils.error + "&6Cooltimmetje &ais not online! :(");
            }
        } else if(cmd.getName().equalsIgnoreCase("coderood")) {
            Player rood = Bukkit.getPlayer("ThoThoKill");
            if (rood != null) {
                if (MiscUtils.cooldownCheck(lastUsedRood, cdtimeRood)) {
                    shootRood(p, rood);
                    PlayerUtils.addTokens(p, 2, "Shot " + rood.getDisplayName() + " &9into the sky!");
                    PlayerUtils.addTokens(rood, 2, p.getDisplayName() + " &9shot you into the sky!");
                    lastUsedRood = System.currentTimeMillis();
                    lastUsedByRood = p.getName();
                } else {
                    int timeUntilUse = (int) (cdtimeRood - ((System.currentTimeMillis() - lastUsedRood) / 1000));
                    ChatUtils.msgPlayerTag(p, "CodeRood", ChatUtils.error + "This command is still on cooldown! It was last used by: &6"
                            + lastUsedByRood + "&a. It will be available again in: &6" + MiscUtils.formatTime(timeUntilUse));
                }
            } else {
                ChatUtils.msgPlayerTag(p, "CodeRood", ChatUtils.error + "&6ThoThoKill &ais not online! :(");
            }
        }
        return true;
    }

    private void shootTim(Player p, Player tim) {
        TitleUtils.sendTitle("&aYou shot " + tim.getDisplayName() + " &ainto the sky!", "&9+2 tokens!", 20, 60, 20, p);
        TitleUtils.sendTitle(p.getDisplayName() + "&a shot you into the sky!", "&9+2 tokens!", 20, 60, 20, tim);
        tim.setVelocity(new Vector(0, 3, 0));
        ParticleEffect.EXPLOSION_HUGE.display(3, 3, 3, 1, 10, tim.getLocation(), 20);
        Bukkit.getWorld("Survival").playSound(tim.getLocation(), Sound.EXPLODE, 100, 1);
    }

    private void shootRood(Player p, Player rood){
        rood.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 10));
        TitleUtils.sendTitle("&aYou shot " + rood.getDisplayName() + " &ainto the sky!", "&9+2 tokens!", 20, 60, 20, p);
        TitleUtils.sendTitle(p.getDisplayName() + "&a shot you into the sky!", "&9+2 tokens!", 20, 60, 20, rood);
        rood.setVelocity(new Vector(0, 3, 0));
        ParticleEffect.EXPLOSION_HUGE.display(3, 3, 3, 1, 10, rood.getLocation(), 20);
        Bukkit.getWorld("Survival").playSound(rood.getLocation(), Sound.EXPLODE, 100, 1);
    }


}
