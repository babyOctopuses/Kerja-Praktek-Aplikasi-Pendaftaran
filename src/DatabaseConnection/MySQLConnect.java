/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Lenovo
 */
public class MySQLConnect {
    Connection conn=null;
    
    public static Connection ConnectDB(){
    try{
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbarang","root","");
       JOptionPane.showMessageDialog(null, "Connected to database");
        return conn;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }   return null;
}
}
