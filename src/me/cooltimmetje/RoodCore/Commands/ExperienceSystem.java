package me.cooltimmetje.RoodCore.Commands;

import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 14-5-2015 at 22:08 by cooltimmetje.
 */
public class ExperienceSystem implements CommandExecutor {

    int levelsTaken;
    int pointsGiven;
    int levelsGiven;
    int pointsTaken;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("xp")){
            if(args.length == 0){
                ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aUsage: &o/xp <check/deposit/withdraw>");
            } else if(args.length == 1){

                if(args[0].equalsIgnoreCase("check")){
                    ChatUtils.msgPlayerTag(p, "XP", "You currently have &d" + DataClass.experiencePoint.get(p.getName()) +
                        " experience point(s) &aand &3" + p.getLevel() + " experience levels&a.");
                    return true;
                } else if(args[0].equalsIgnoreCase("deposit") || args[0].equalsIgnoreCase("withdraw")) {
                    ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aUsage: &o/xp " + args[0].toLowerCase() + " <all/number>");
                } else {
                    ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aUsage: &o/xp <check/deposit/withdraw>");
                }

            } else if(args.length == 2){
                if(!args[0].equalsIgnoreCase("deposit") && !args[0].equalsIgnoreCase("withdraw")){
                    ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aUsage: &o/xp <check/deposit/withdraw>");
                } else if(args[0].equalsIgnoreCase("deposit")){
                    if(args[1].equalsIgnoreCase("all")){
                        int level = p.getLevel();
                        if(level >= 10){
                            pointsGiven = 0;
                            levelsTaken = 0;
                            while (level >= 10){
                                levelsTaken = levelsTaken + 10;
                                pointsGiven = pointsGiven + 1;
                                level = level - 10;
                            }
                            PlayerUtils.addXpPoint(p, pointsGiven, "sold " + levelsTaken + " experience levels");
                            PlayerUtils.removeXpLevel(p, levelsTaken, "bought " + pointsGiven + " experience points");
                            return true;
                        } else {
                            ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aYou need at least &310 levels &ato store them.");
                        }
                    } else if(MiscUtils.isInt(args[1])){
                        int number = Integer.parseInt(args[1]);
                        if(p.getLevel() >= number * 10){
                            pointsGiven = 0;
                            levelsTaken = 0;
                            for (pointsGiven = 0; pointsGiven < number; pointsGiven++){
                                levelsTaken = levelsTaken + 10;
                            }
                            PlayerUtils.addXpPoint(p, pointsGiven, "sold " + levelsTaken + " experience levels");
                            PlayerUtils.removeXpLevel(p, levelsTaken, "bought " + pointsGiven + " experience points");
                            return true;
                        } else {
                            ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aYou need at least &3" + number * 10 + " levels &ato do that.");
                        }
                    } else {
                        ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aUsage: &o/xp " + args[0].toLowerCase() + " <all/number>");
                    }
                } else if(args[0].equalsIgnoreCase("withdraw")){
                    if(args[1].equalsIgnoreCase("all")){
                        int points = DataClass.experiencePoint.get(p.getName());
                        if(points >= 1){
                            pointsTaken = 0;
                            levelsGiven = 0;
                            while(points >= 1){
                                pointsTaken = pointsTaken + 1;
                                levelsGiven = levelsGiven + 10;
                                points = points - 1;
                            }
                            PlayerUtils.removeXpPoint(p, pointsTaken, "bought " + levelsGiven + " experience levels");
                            PlayerUtils.addXpLevel(p, levelsGiven, "sold " + pointsTaken + " experience points");
                        } else {
                            ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aYou need at least &31 point &ato use them.");
                        }
                    } else if(MiscUtils.isInt(args[1])){
                        int number = Integer.parseInt(args[1]);
                        int points = DataClass.experiencePoint.get(p.getName());
                        if(number <= points) {
                            pointsTaken = 0;
                            levelsGiven = 0;
                            for (pointsTaken = 0; pointsTaken < number; pointsTaken++){
                                levelsGiven = levelsGiven + 10;
                                points = points - 1;
                            }
                            PlayerUtils.removeXpPoint(p, pointsTaken, "bought " + levelsGiven + " experience levels");
                            PlayerUtils.addXpLevel(p, levelsGiven, "sold " + pointsTaken + " experience points");
                        } else {
                            ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aYou need at least &d" + number + " points &ato do that.");
                        }
                    } else {
                        ChatUtils.msgPlayerTag(p, "XP", ChatUtils.error + "&aUsage: &o/xp " + args[0].toLowerCase() + " <all/number>");
                    }
                }
            }
        }
        return true;
    }

}
