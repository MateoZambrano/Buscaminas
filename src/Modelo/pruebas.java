/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class pruebas {
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        
        Statement st;
        ResultSet rs;
        try{
            st = con.getConn().createStatement();
            rs = st.executeQuery("select * from usuario");
            while(rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("nombre")+" "+rs.getString("pasword")+" "+rs.getInt("puntaje"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
