package multifont_connection_JSON_XML.connection;

import java.sql.Connection;

//import java.sql.DriverManager;

//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
    public static Connection getConnection() throws Exception {

        try {
            String URL = "jdbc:mysql://localhost:3306/pascual_mercadona_db";
            String username = "root";
            String password = "";
            // returns the connection to the database
            return DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            throw e;

        }

    }
}
