package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.HideAndSeek;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static File file = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public static String databaseType = (String) config.get("Database.Type");
    public String host = (String) config.get("Database.Type");
    public String port = (String) config.get("Database.Type");
    public String database = (String) config.get("Database.Type");
    public String username = (String) config.get("Database.Type");
    public String password = (String) config.get("Database.Type");

    public void DatabaseManager() {

        if(databaseType.toLowerCase().equals("MySQL")) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(databaseType.toLowerCase().equals("SQLite")) {
            String fileName = "database.db";
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
