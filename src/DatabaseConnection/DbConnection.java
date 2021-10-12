/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Lenovo
 */
public class DbConnection {
public Connection conn=null;

public static Connection connect(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbdd","root","");
       System.out.println("Connected to database");
        return conn;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
       return null;
    }
    
    
}

    public static abstract class connect implements Connection {

        public connect() {
      
        
        
        }
    }
}
