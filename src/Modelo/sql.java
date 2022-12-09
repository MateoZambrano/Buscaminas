/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Mateo
 */
public class sql {
    
    public int id_incrementable(){
        int contador = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion db = new Conexion();
        try{
            ps = db.getConn().prepareStatement("SELECT MAX(id) FROM usuario;");
            rs = ps.executeQuery();
            while(rs.next()){
                contador = rs.getInt(1) + 1;
            }
        }catch(Exception ex){
            System.out.println("Eror"+ex.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
            }catch(Exception ex){
                
            }
        }
        return contador;
    }
    
}
