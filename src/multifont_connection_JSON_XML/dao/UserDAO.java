package multifont_connection_JSON_XML.dao;

import multifont_connection_JSON_XML.vo.UserVO;

import javax.xml.transform.Source;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserDAO {
    public boolean existsUser(Connection conn, String name) {
        String query = "SELECT userName FROM user WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, name);
            try(ResultSet rs = stmt.executeQuery()){
                return rs.next(); // So if there is at least one instance we get a true from this.
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

public void createTable(String tableName, int colNum) {
        Scanner sc = new Scanner(System.in);
    Map<String, String> columnNames_Types = new HashMap<>();
//    ArrayList <String> columnTypes = new ArrayList<>();
    String columnName = "";
    String columnType = "";
    long lenght = 0;
    for (int i = 0; i < colNum; i++) {
        System.out.print("Name of the column: ");
        columnName = sc.nextLine().trim();
        System.out.print("Value type: ");
        columnName = sc.nextLine().toUpperCase().trim();
        String query = "CREATE TABLE " + tableName + "(\n";
        switch (columnType){
            case "INT", "BIGINT" -> {
                columnNames_Types.put(columnName, columnType);
                System.out.print("Length: ");
                lenght = sc.nextInt();
                query = query + columnNames_Types.get("INT") + " INT (" + lenght + ")"; // I LEFT IT HERE
            }
            case  "VARCHAR" -> {

            }
            case "CHAR" -> {
                lenght = 1;
            }
            case "ENUM" -> { // Hmmm, problematiic I think

            }
            case "BOOLEAN" -> {
                columnNames_Types.put(columnName, columnType);
            }
            case "DOUBLE", "DECIMAL"  -> {
                columnNames_Types.put(columnName, columnType);
                System.out.print("Length: ");
                double decimalLenght = sc.nextDouble();
            }
            case "DATE" -> {

            }
            case "DATETIME" -> {

            }
            case "TIMESTAMP" -> {

            }
            case "TIME" -> {

            }
            default -> {
                System.out.println("Not admitted type!!");
            }

        };


    }

}

public void insertUser(Connection conn, UserVO newUser, String company){
        String query = "INSERT INTO user (name, surname, dni, company) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, newUser.getNombre());
            stmt.setString(2, newUser.getApellido());
            stmt.setString(3, newUser.getDni());
            stmt.setString(4, company);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    for (int i = 0; i < newUser.getTelefonos().size(); i++) {
        String query2 = "INSERT INTO phoneNums (dni, number) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query2)){

            stmt.setString(1, newUser.getDni());
            // Iterate so we insert as many times as there are telephone numbers
            stmt.setLong(2, newUser.getTelefonos().get(i));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }
    for (int i = 0; i < newUser.getCorreos().size(); i++) {
        String query2 = "INSERT INTO emails (dni, email) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query2)){

            stmt.setString(1, newUser.getDni());
            // Iterate so we insert as many times as there are emails
            stmt.setString(2, newUser.getCorreos().get(i));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }



}

}
