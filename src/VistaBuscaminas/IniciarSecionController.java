/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaBuscaminas;

import Controlador.NewFXMain;
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
    
    private NewFXMain Menu;
    
    @FXML
    private TextField txtPastword;
    @FXML
    private Button btnConfirmar;
   
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

    
    public void setProgramaPrincipal(NewFXMain ProgramaPrincipal){
        this.Menu = ProgramaPrincipal;
    }
    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        System.out.println(txtNombreUsuario.getText());
        System.out.println(txtPastword.getText());
        
        Menu.llamarSegundaVentanaJuego();
    }
    
}
