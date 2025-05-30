/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konekcija;

import java.sql.Connection;
import konfiguracija.Konfiguracija;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej
 */
public class Konekcija {
    
    private static Konekcija instance;
    private Connection connection;

    private Konekcija(){
        try {
            String url = Konfiguracija.getInstance().getPropertie("url");
            String password = Konfiguracija.getInstance().getPropertie("password");
            String username = Konfiguracija.getInstance().getPropertie("username");
            
            connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Konekcija getInstance(){
        if(instance == null){
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
