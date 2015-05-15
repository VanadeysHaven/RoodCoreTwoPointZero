package me.cooltimmetje.RoodCore.Commands;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import me.cooltimmetje.RoodCore.Utilities.ScheduleUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

/**
 * This class has been created on 15-5-2015 at 12:17 by cooltimmetje.
 */
public class ResourcePackCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("rp")) {
            if(args.length == 1 && MiscUtils.isInt(args[0])){
                int pack = Integer.parseInt(args[0]);
                String url = DataClass.resourceURL.get(pack);
                if(url != null){
                    ChatUtils.msgPlayerTag(p, "ResourcePacks", "Attempting to send you the resourcepack in 5 seconds! You must click yes when prompted.");
                    ChatUtils.msgPlayerTag(p, "ResourcePacks", "Downloading can take a while depending on your internet speed!");
                    ScheduleUtils.scheduleTask(100, new Runnable() {
                        @Override
                        public void run() {
                            PlayerUtils.setResourcePack(p, url);
                            DataClass.resourcePack.put(p.getName(), pack);
                        }
                    });
                } else {
                    ChatUtils.msgPlayerTag(p, "ResourcePacks", ChatUtils.error + "Invalid pack!");
                }
            } else {
                ChatUtils.msgPlayerTag(p, "ResourcePacks", ChatUtils.error + "Please specify the pack!");
            }
        }
        return true;
    }

}
