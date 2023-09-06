/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos_java_ant_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author KAVI
 */
public class db {
    public static Connection myCon(){
        Connection con = null ;
     
        try {
         
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ant_pos","root","");
            return con;
            } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Database not connected!");
            return null;
        }
    }
}
