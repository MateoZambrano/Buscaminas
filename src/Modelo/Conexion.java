/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Conexion {
    
    Connection conn;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/buscaminas", "Mateo", "12345");
            System.out.println("Se ha conectado satisfactoriamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
    
}
