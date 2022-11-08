/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaBuscaminas;

import Controlador.Inicio;
import Modelo.Conexion;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mateo
 */
public class RegistroController implements Initializable {
    
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private Button btnAtras;
    
    private Inicio MenuVentana;
    
    @FXML
    private Button btnSiguiente;
    @FXML
    private TextField txtCampoUsuario;
    @FXML
    private TextField txtCampoContraseña;
    @FXML
    private TextField txtSegundaContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonAtras(ActionEvent event) {
        MenuVentana.initRootLayout();
    }

    @FXML
    private void buttonSiguiente(ActionEvent event) {
        
        String usuario = txtCampoUsuario.getText();
        String contrasenia = txtCampoContraseña.getText();
        String contrasenia2 = txtSegundaContraseña.getText();
        int contador = 2;
        
        if(txtCampoUsuario.getText().equals("") && txtCampoContraseña.getText().equals("") && txtSegundaContraseña.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingresar datos validos");
        }else if(contrasenia.equals(contrasenia2)){
            //Conexion conexion = new Conexion();
            String sql = "insert into usuario (id,nombre,pasword,puntaje)values(?,?,?,?)";
            
            try{
                Conexion conexion = new Conexion();
                
                //pst Manda la informacion a la base de datos
                pst = conexion.getConn().prepareCall(sql);
                pst.setInt(1, contador++);
                pst.setString(2, usuario);
                pst.setString(3, contrasenia);
                pst.setInt(4, 0);
                pst.execute();
                //informa al usuario que ha sidi añadido
                JOptionPane.showMessageDialog(null, "Usuario añadido");
                
                //REsetear los valores para que se vea mejor
                txtCampoUsuario.setText("");
                txtCampoContraseña.setText("");
                txtSegundaContraseña.setText("");
                
            }catch(Exception e){
               JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    
    public void setProgramaPrincipal(Inicio programaPrincipal){
        this.MenuVentana = programaPrincipal;
    }
    
    
}
