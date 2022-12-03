/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaBuscaminas;

import Controlador.Inicio;
import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
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
public class IniciarSecionController implements Initializable {

    @FXML
    private TextField txtNombreUsuario;
    
    private Inicio Menu;
    
    @FXML
    private TextField txtPastword;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnAtras;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void nombreUsuario(ActionEvent event) {
        System.out.println("hola como estas");
    }

    
    public void setProgramaPrincipal(Inicio ProgramaPrincipal){
        this.Menu = ProgramaPrincipal;
    }
    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        Conexion conecxion = new Conexion();
        Connection conn = conecxion.getConn();
        int resultado = 0;
        String usuario = txtNombreUsuario.getText();
        String contrasenia = txtPastword.getText();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USUARIO WHERE nombre='" + usuario + "' and  pasword='" + contrasenia + "' ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, txtNombreUsuario.getText() + "  Ya puedes jugar :)");
                    Menu.abrirJuego();//Llama la ventana donde se va ha jugar
                    
                }
                txtNombreUsuario.setText("");
                txtPastword.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Error de Acceso , Ususario no registrado");
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @FXML
    private void buttonAtras(ActionEvent event) {
        Menu.initRootLayout();
    }
    
}
