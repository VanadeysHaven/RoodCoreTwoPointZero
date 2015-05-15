package me.cooltimmetje.RoodCore.Commands;

import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 15-5-2015 at 20:31 by cooltimmetje.
 */
public class MassTokens implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("masstokens")) {
            if(p.getName().equals("Cooltimmetje")){
                if(args.length > 0 && MiscUtils.isInt(args[0])){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        PlayerUtils.addTokens(pl, Integer.parseInt(args[0]), "Some love from " + p.getDisplayName() + "&9!");
                    }
                    ChatUtils.broadcastTag("MassTokens", p.getDisplayName() + " &agave everyone on the server &9" + args[0] + " tokens&a!");
                } else {
                    ChatUtils.msgPlayerTag(p, "MassTokens", ChatUtils.error + "Please specify a number.");
                }
            } else {
                p.sendMessage("nope");
            }
        }
        return true;
    }
}
