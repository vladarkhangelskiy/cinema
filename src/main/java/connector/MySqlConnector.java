package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {

    private final String url = "jdbc:mysql://localhost:3306/cinema";
    private final String user = "root";
    private final String password = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
