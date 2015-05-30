package me.cooltimmetje.RoodCore.MysqlManager;

import com.zaxxer.hikari.HikariDataSource;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
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

    public static HikariDataSource hikari = null;

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
                DataClass.resourcePack.put(p.getName(), rs.getInt("resource_pack"));
                DataClass.normalChests.put(p.getName(), rs.getInt("normal_chests"));
                DataClass.chestsTime.put(p.getName(), rs.getInt("chest_time"));
                DataClass.epicChest.put(p.getName(), rs.getInt("epic_chest"));
                DataClass.legendChest.put(p.getName(), rs.getInt("legend_chest"));
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
        String update = "INSERT INTO playerdata VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE uuid=?";

        try{
            c = hikari.getConnection();

            ps = c.prepareStatement(update);
            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setString(3, p.getAddress().toString());
            ps.setInt(4, 0);
            ps.setInt(5, DataClass.tokenTime);
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, DataClass.chestTime);
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setString(12, uuid);
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
        new ActionbarTitleObject("\u00A79Profile> \u00A7aSaving profile... \u00A7oPlease Wait...").send(p);
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = PlayerUtils.getUUID(p);
        String update = "INSERT INTO playerdata VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                "tokens=?,token_time=?,experience_point=?,resource_pack=?,normal_chests=?,chest_time=?,epic_chest=?,legend_chest=?";
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
            ps.setInt(7, DataClass.resourcePack.get(p.getName()));
            ps.setInt(8, DataClass.normalChests.get(p.getName()));
            ps.setInt(9, DataClass.chestsTime.get(p.getName()));
            ps.setInt(10, DataClass.epicChest.get(p.getName()));
            ps.setInt(11, DataClass.legendChest.get(p.getName()));

            ps.setInt(12, DataClass.tokens.get(p.getName()));
            ps.setInt(13, DataClass.tokensTime.get(p.getName()));
            ps.setInt(14, DataClass.experiencePoint.get(p.getName()));
            ps.setInt(15, DataClass.resourcePack.get(p.getName()));
            ps.setInt(16, DataClass.normalChests.get(p.getName()));
            ps.setInt(17, DataClass.chestsTime.get(p.getName()));
            ps.setInt(18, DataClass.epicChest.get(p.getName()));
            ps.setInt(19, DataClass.legendChest.get(p.getName()));
            ps.execute();
            new ActionbarTitleObject("\u00A79Profile> \u00A7aProfile saved!").send(p);
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
