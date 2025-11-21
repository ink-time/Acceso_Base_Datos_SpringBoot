package multifont_connection_JSON_XML;

import multifont_connection_JSON_XML.connection.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AppDB {
    public static void main(String[] args) {
    Connection conn = null;
    try {
        conn = MyConnection.getConnection();
        if(conn!=null) {
            Scanner scan = new Scanner (System.in);
            // All the stuff that happens if the connection is successful and if there are no errors
            System.out.println("Connected");

//          This SHOULD work, and maybe I work on creating the database and the tables tomorrow
            FileReader.scanXMLfile(conn);
            FileReader.scanJSONfile(conn);

        }else {
            System.out.println("Not connected properly!");
        }
        // method returned, if it was 1, it executed succesfully, if it was 0, then no
    } catch (Exception e) {
        e.printStackTrace();
    }
		finally {
        if(conn!=null){
            try {
                conn.close();
                System.out.print("The database connection has been closed.");
            } catch (SQLException e) {
                System.out.print("The database connection was not closed properly.");
//                throw new RuntimeException(e);
                e.printStackTrace();
                //The connection cannot be closed
            }
        }
    }
    }
}
