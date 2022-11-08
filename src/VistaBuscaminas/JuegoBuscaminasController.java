/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaBuscaminas;

import Controlador.Inicio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Mateo
 */
public class JuegoBuscaminasController implements Initializable {
    
    private Inicio Menu;
    
    @FXML
    private GridPane gdpButtons;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setProgramaPrincipal(Inicio ProgramaPrincipal){
        this.Menu = ProgramaPrincipal;
    }
    
}
