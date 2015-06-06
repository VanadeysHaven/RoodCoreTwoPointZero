package me.cooltimmetje.RoodCore.MysqlManager;

import com.zaxxer.hikari.HikariDataSource;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import me.cooltimmetje.RoodCore.Core.DataClass;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Utilities.ChatUtils;
import me.cooltimmetje.RoodCore.Utilities.MiscUtils;
import me.cooltimmetje.RoodCore.Utilities.PlayerUtils;
import me.cooltimmetje.RoodCore.Utilities.TitleUtils;
import org.bukkit.Bukkit;
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

    public static void loadData(Player p){
        ChatUtils.msgPlayerTag(p, "Profile", "Loading your profile from the Database... &oPlease Wait...");

        Connection c = null;

        PreparedStatement psData = null;
        PreparedStatement psSettings = null;

        ResultSet rsData = null;
        ResultSet rsSettings = null;

        String uuid = PlayerUtils.getUUID(p);

        String loadData = "SELECT * FROM playerdata WHERE uuid = '" + uuid + "';";
        String loadSettings = "SELECT * FROM player_settings WHERE uuid = '" + uuid + "';";

        try {
            c = hikari.getConnection();

            psData = c.prepareStatement(loadData);
            psSettings = c.prepareStatement(loadSettings);

            rsData = psData.executeQuery();
            rsSettings = psSettings.executeQuery();

            if(rsData.next() && rsSettings.next()){
                setData(rsData, p);
                setSettings(rsSettings, p);
                ChatUtils.msgPlayerTag(p, "Profile", "Profile loaded! &lWE DID IT! &a*dances*");
            } else {
                //create profile
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

            if(psData != null) {
                try {
                    psData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rsData != null){
                try {
                    rsData.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psSettings != null) {
                try {
                    psSettings.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rsSettings != null){
                try {
                    rsSettings.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setSettings(ResultSet rsSettings, Player p) {
        try {
            DataClass.resourcePack.put(p.getName(), rsSettings.getInt("resource_pack"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setData(ResultSet rs, Player p) {
        try {
            DataClass.tokens.put(p.getName(), rs.getInt("tokens"));
            DataClass.tokensTime.put(p.getName(), rs.getInt("token_time"));
            DataClass.experiencePoint.put(p.getName(), rs.getInt("experience_point"));
            DataClass.normalChests.put(p.getName(), rs.getInt("normal_chests"));
            DataClass.chestsTime.put(p.getName(), rs.getInt("chest_time"));
            DataClass.epicChest.put(p.getName(), rs.getInt("epic_chest"));
            DataClass.legendChest.put(p.getName(), rs.getInt("legend_chest"));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createProfile(Player p) {
        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement psSettigns = null;
        String uuid = PlayerUtils.getUUID(p);
        String createData = "INSERT INTO playerdata VALUES(?, ?, 0, ?, 0, 0, ?, 0, 0)";
        String createSettings = "INSERT INTO player_settings VALUES(?, ?, 0)";

        try{
            c = hikari.getConnection();

            ps = c.prepareStatement(createData);
            psSettigns = c.prepareStatement(createSettings);

            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setInt(3, DataClass.tokenTime);
            ps.setInt(4, DataClass.chestTime);

            psSettigns.setString(1, uuid);
            psSettigns.setString(2, p.getName());

            ps.execute();
            psSettigns.execute();

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

            if(psSettigns != null) {
                try {
                    psSettigns.close();
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
        PreparedStatement psSettings = null;
        String uuid = PlayerUtils.getUUID(p);
        String updateData = "UPDATE playerdata SET last_name=?,tokens=?,token_time=?,experience_point=?,normal_chests=?,chest_time=?,epic_chest=?,legend_chest=? WHERE uuid=?";
        String updateSettings = "UPDATE playerdata SET last_name=?,resource_pack=? WHERE uuid=?";
        try{
            c = hikari.getConnection();


            ps = c.prepareStatement(updateData);
            ps.setString(1, p.getName());
            ps.setInt(2, DataClass.tokens.get(p.getName()));
            ps.setInt(3, DataClass.tokensTime.get(p.getName()));
            ps.setInt(4, DataClass.experiencePoint.get(p.getName()));
            ps.setInt(5, DataClass.normalChests.get(p.getName()));
            ps.setInt(6, DataClass.chestsTime.get(p.getName()));
            ps.setInt(7, DataClass.epicChest.get(p.getName()));
            ps.setInt(8, DataClass.legendChest.get(p.getName()));
            ps.setString(9, uuid);

            psSettings = c.prepareStatement(updateSettings);
            psSettings.setString(1, p.getName());
            psSettings.setInt(2, DataClass.resourcePack.get(p.getName()));
            psSettings.setString(3, uuid);

            psSettings.execute();
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

            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psSettings != null) {
                try {
                    psSettings.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
