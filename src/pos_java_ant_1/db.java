/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos_java_ant_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author KAVI
 */
//public class db {
//    public static Connection myCon(){
//        String dbUrl = "jdbc:h2:./database/ant_pos;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=9092";
//
//        String dbUser = "sa";
//        String dbPassword = "";
//        
//        Connection con = null ;
//     
////        try {
////         
////            Class.forName("com.mysql.jdbc.Driver");
//////            con = DriverManager.getConnection("jdbc:mysql://localhost/ant_pos","root","");
////            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
////            return con;
////            } catch (ClassNotFoundException | SQLException e) {
//////            System.out.println(e);
////            JOptionPane.showMessageDialog(null, "Database not connected!");
////            return null;
////        }
//        
//        try {
//            // Load the H2 JDBC driver
//            Class.forName("org.h2.Driver");
//
//            // Connect to the H2 database
//            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//            return con;
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Database not connected!");
//            return null;
//        }
//    }
//}

public class db {
    public static Connection myCon() {
//        String dbUrl = "jdbc:h2:./database/h2/bin/database/ant_pos;AUTO_SERVER=TRUEAUTO_SERVER_PORT=8084"; // Change the URL as needed

        String dbUrl = "jdbc:h2:./database/h2/bin/database/ant_pos";
        String dbUser = "sa";
        String dbPassword = "";
        
        Connection con = null;
     
        try {
            // Load the H2 JDBC driver
            Class.forName("org.h2.Driver");

            // Connect to the H2 database
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // Check if the database exists
//            if (!checkDatabaseExists(con)) {
//                // If it doesn't exist, create it
//                createDatabase(con);
//            }

            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Database not connected!");
            return null;
        }
    }

    private static boolean checkDatabaseExists(Connection con) throws SQLException {
        // Query to check if the database exists
        String query = "SELECT 1 FROM information_schema.tables WHERE table_name = cart";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If the table exists, this returns true
            }
        }
    }

    private static void createDatabase(Connection con) throws SQLException {
        // SQL script to create your database schema
        String createScript = "CREATE TABLE cart (cid,name);";
    
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(createScript);
        }
    }
}
