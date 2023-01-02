package cz.ragy.hideandseek.mysql;

import java.sql.*;
import java.util.UUID;

import static cz.ragy.hideandseek.managers.DatabaseManager.connection;

public class Execute {
    public void addPlayer(UUID uuid, String firstTimeJoined) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO players (uuid, first_time_joined) VALUES (?, ?)");
            statement.setString(1, uuid.toString());
            statement.setString(2, firstTimeJoined);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getExistsPlayer(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE uuid = ?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
