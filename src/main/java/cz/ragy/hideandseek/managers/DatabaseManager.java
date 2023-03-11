package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.database.MysqlManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection connection;

    public DatabaseManager() {
        if(ConfigLoader.databaseType.toLowerCase().equals("mysql")) {
            new MysqlManager(ConfigLoader.databaseHost, ConfigLoader.database, ConfigLoader.databaseUsername, ConfigLoader.databasePassword);
        } else if(ConfigLoader.databaseType.toLowerCase().equals("sqlite")) {
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
