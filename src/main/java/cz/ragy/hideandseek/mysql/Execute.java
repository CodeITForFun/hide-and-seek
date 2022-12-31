package cz.ragy.hideandseek.mysql;

import java.sql.*;

public class Execute {
        private static Connection conn;

        public Execute(Connection connection) {
            this.conn = connection;
        }

        public void executeDropDatabase() throws SQLException {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP DATABASE yourmom;");
        }
}
