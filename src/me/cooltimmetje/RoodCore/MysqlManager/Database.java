package me.cooltimmetje.RoodCore.MysqlManager;

import com.zaxxer.hikari.HikariDataSource;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import me.cooltimmetje.RoodCore.Utilities.TitleUtils;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class has been created on 13-5-2015 at 20:48 by cooltimmetje.
 */
public class Database {

    private static HikariDataSource hikari = null;

    public static void connectToDatabase(){
        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(10);

        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", Main.getPlugin().getConfig().getString("Database.serverName"));
        hikari.addDataSourceProperty("port", Main.getPlugin().getConfig().getString("Database.port"));
        hikari.addDataSourceProperty("databaseName", Main.getPlugin().getConfig().getString("Database.databaseName"));
        hikari.addDataSourceProperty("user", Main.getPlugin().getConfig().getString("Database.user"));
        hikari.addDataSourceProperty("password", Main.getPlugin().getConfig().getString("Database.password"));
    }

    public static void loadData(Player p) throws NullPointerException{
        ChatUtils.msgPlayerTag(p, "Profile", "Loading your profile from the Database... &oPlease Wait...");

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uuid = PlayerUtils.getUUID(p);
        String load = "SELECT * FROM playerdata WHERE uuid = '" + uuid + "';";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                DataClass.tokens.put(p.getName(), rs.getInt("tokens"));
                DataClass.tokensTime.put(p.getName(), rs.getInt("token_time"));
                DataClass.experiencePoint.put(p.getName(), rs.getInt("experience_point"));
                ChatUtils.msgPlayerTag(p, "Profile", "Profile loaded! &lWE DID IT! &a*dances*");
            } else {
                ChatUtils.msgPlayerTag(p, "Profile", "You do not have a profile yet! So we will create one for you! This process will happen automagically with the use of" +
                        " some magic! Ooooh! Maaaagiiiicccc!");
                createProfile(p);
            }

        } catch (SQLException e){
            e.printStackTrace();
            ChatUtils.msgPlayerTag(p, "Profile", ChatUtils.error + "Profile loading failed! Please relog to retry!");
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Organize
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createProfile(Player p) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uuid = PlayerUtils.getUUID(p);
        String update = "INSERT INTO playerdata VALUES(?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE uuid=?";

        try{
            c = hikari.getConnection();

            ps = c.prepareStatement(update);
            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setString(3, p.getAddress().toString());
            ps.setInt(4, 0);
            ps.setInt(5, DataClass.tokenTime);
            ps.setInt(6, 0);
            ps.setString(7, uuid);
            ps.execute();
            ChatUtils.msgPlayerTag(p, "Profile", "Profile created! &lYaaay! &a*whoop whoop*");
        } catch (SQLException e){
            e.printStackTrace();
            ChatUtils.msgPlayerTag(p, "Profile", ChatUtils.error + "Profile creation failed! Please relog to retry!");
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Organize
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void saveData(Player p) {
        TitleUtils.sendActionTag(p, "Profile", "Saving profile... &oPlease Wait...");
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = PlayerUtils.getUUID(p);
        String update = "INSERT INTO playerdata VALUES(?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE uuid=?,tokens=?,token_time=?,experience_point=?";
//        String update = "UPDATE playerdata SET uuid=?,last_name=?,last_ip_adress=?,tokens=?,token_time=? WHERE 1";
        try{
            c = hikari.getConnection();

            ps = c.prepareStatement(update);
            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setString(3, p.getAddress().toString());
            ps.setInt(4, DataClass.tokens.get(p.getName()));
            ps.setInt(5, DataClass.tokensTime.get(p.getName()));
            ps.setInt(6, DataClass.experiencePoint.get(p.getName()));
            ps.setString(7, uuid);
            ps.setInt(8, DataClass.tokens.get(p.getName()));
            ps.setInt(9, DataClass.tokensTime.get(p.getName()));
            ps.setInt(10, DataClass.experiencePoint.get(p.getName()));
            ps.execute();
            TitleUtils.sendActionTag(p, "Profile", "Profile saved!");
        } catch (SQLException e){
            e.printStackTrace();
            ChatUtils.msgPlayerTag(p, "Profile", ChatUtils.error + "Profile saving failed! Please relog to retry!");
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Organize
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
