/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Connects {
    public static Connection getConnect() {
        Connection con = null;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/ChatTcp?useSSL=false";
        String DB_USER = "root";
        String DB_PASSWORD = "dangkhoa1997";
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}