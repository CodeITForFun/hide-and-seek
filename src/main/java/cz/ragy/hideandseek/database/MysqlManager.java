package cz.ragy.hideandseek.database;

import java.sql.*;

public class MysqlManager {
    private static Connection connection;
    public MysqlManager(String host, String database, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + "/" + database;
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("MySQL Connection Established!");
        } catch (Exception e) {
            System.err.println("Cannot connect to MySQL server: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            return null;
        }
    }

    public int executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            return result;
        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            return -1;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("MySQL Connection Closed!");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
