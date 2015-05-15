package me.cooltimmetje.RoodCore.Commands;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 14-5-2015 at 20:56 by cooltimmetje.
 */
public class CodeCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("codetim")) {
            ChatUtils.msgPlayerTag(p, "Tokens", "You have &9" + DataClass.tokens.get(p.getName()) + " tokens&a! You'll get more in &9" + DataClass.tokensTime.get(p.getName()) + "m&a!");
        } else if(cmd.getName().equalsIgnoreCase("coderood")){

        }
        return true;
    }

}
