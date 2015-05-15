package me.cooltimmetje.RoodCore;

import me.cooltimmetje.RoodCore.Commands.ExperienceSystem;
import me.cooltimmetje.RoodCore.Commands.TokensCommand;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.EventListeners.JoinQuitEvent;
import me.cooltimmetje.RoodCore.MysqlManager.Database;
import me.cooltimmetje.RoodCore.Timers.Announce;
import me.cooltimmetje.RoodCore.Timers.DataSaving;
import me.cooltimmetje.RoodCore.Timers.Tokens;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class has been created on 13-5-2015 at 20:40 by cooltimmetje.
 */
public class Main extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        plugin = this;
        Database.connectToDatabase();

        registerEvents(this, new JoinQuitEvent());
        getCommand("tokens").setExecutor(new TokensCommand());
        getCommand("xp").setExecutor(new ExperienceSystem());

        for(Player p : Bukkit.getOnlinePlayers()){
            Database.loadData(p);
        }

        Announce.announcer();
        Tokens.tokensGiver();
        DataSaving.dataSaver();
    }

    @Override
    public void onDisable(){
        for(Player p : Bukkit.getOnlinePlayers()) {
            Database.saveData(p);
        }
        plugin = null;
    }

    public static void registerEvents(Plugin plugin, Listener... listeners) {
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static Plugin getPlugin(){
        return plugin;
    }



}
