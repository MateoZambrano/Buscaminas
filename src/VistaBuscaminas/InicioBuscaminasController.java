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

/**
 * FXML Controller class
 *
 * @author Mateo
 */
public class InicioBuscaminasController implements Initializable {

    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnVerPuntuaciones;
    
    private Inicio MenuPrincipal;
    
    @FXML
    private Button btnIniciarJuego;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarJuego(ActionEvent event) {
        MenuPrincipal.llamarSegundaVentana();
    }

    @FXML
    private void registro(ActionEvent event) {
    }

    @FXML
    private void verPuntuaciones(ActionEvent event) {
    }
    
    
    public void setProgramaPrincipal(Inicio ProgramaPrincipal){
        this.MenuPrincipal = ProgramaPrincipal;
    }
}
