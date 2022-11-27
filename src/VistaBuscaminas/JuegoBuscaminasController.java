/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaBuscaminas;

import Controlador.Inicio;
import ProyBuscaminas.Compartimiento;
import ProyBuscaminas.Tablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mateo
 */
public class JuegoBuscaminasController implements Initializable {

    int numFilas = 10;
    int numColumnas = 10;
    int numMinas = 20;

    JButton[][] botonesTablero;
    Tablero tableroBuscaminas;

    public JuegoBuscaminasController() {

    }
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

    public void setProgramaPrincipal(Inicio ProgramaPrincipal) {
        this.Menu = ProgramaPrincipal;
    }

    @FXML
    private void crearTablero() {
        tableroBuscaminas = new Tablero(numFilas, numColumnas, numMinas);
        tableroBuscaminas.setEventoPartidaPerdida(new Consumer<List<Compartimiento>>() {
            @Override
            public void accept(List<Compartimiento> t) {
                for (Compartimiento casillaConMina : t) {
                    botonesTablero[casillaConMina.getPosicionFila()][casillaConMina.getPosicionColumna()].setText("*");
                }
            }
        });
        tableroBuscaminas.setEventoCasillaAbierta(new Consumer<Compartimiento>() {
            @Override
            public void accept(Compartimiento t) {
                botonesTablero[t.getPosicionFila()][t.getPosicionColumna()].setEnabled(false);
                botonesTablero[t.getPosicionFila()][t.getPosicionColumna()].setText(t.getNumMinasAlrededor() + "");
            }
        });
        tableroBuscaminas.imprimirTablero();
    }

    private void cargaControles() {
        int posXReferencia = 25;
        int posYReferencia = 25;
        int anchoControl = 30;
        int altoControl = 30;

        botonesTablero = new JButton[numFilas][numColumnas];
        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + j);
                botonesTablero[i][j].setBorder(null);
                if (i == 0 && j == 0) {
                    botonesTablero[i][j].setBounds(posXReferencia, posYReferencia,
                            anchoControl, altoControl);
                } else if (i == 0 && j != 0) {
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i][j - 1].getX() + botonesTablero[i][j - 1].getWidth(),
                            posYReferencia, anchoControl, altoControl);
                } else {
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i - 1][j].getX(),
                            botonesTablero[i - 1][j].getY() + botonesTablero[i - 1][j].getHeight(),
                            anchoControl, altoControl);
                }
                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnClick(e);
                    }
                });
               // getContentPane().add(botonesTablero[i][j]);
            }
        }
    }
    
    private void btnClick(ActionEvent e){
        JButton btn=(JButton)e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila=Integer.parseInt(coordenada[0]);
        int posColumna=Integer.parseInt(coordenada[1]);
       // JOptionPane.showMessageDialog(rootPane, posFila+","+posColumna);
        tableroBuscaminas.seleccionarCasilla(posFila, posColumna);
    }

}
