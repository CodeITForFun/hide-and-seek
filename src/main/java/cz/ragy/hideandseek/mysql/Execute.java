package cz.ragy.hideandseek.mysql;

import java.sql.*;

public class Execute {
        private static Connection conn;

        public Execute(Connection connection) {
            this.connection = connection;
        }

        public void executeDropDatabase() throws SQLException {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE yourmom;");
        }
}
