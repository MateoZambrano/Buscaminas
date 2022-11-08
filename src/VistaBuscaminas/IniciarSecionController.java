/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaBuscaminas;

import Controlador.Inicio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        
        Menu.llamarSegundaVentana();
    }

    @FXML
    private void buttonAtras(ActionEvent event) {
        Menu.initRootLayout();
    }
    
}
