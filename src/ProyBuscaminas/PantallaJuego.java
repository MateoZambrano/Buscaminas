/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyBuscaminas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class PantallaJuego extends javax.swing.JFrame {

    int numFilas = 10;
    int numcolumnas = 10;
    int numMinas = 20;

    JButton[][] botonesTablero;
    Tablero tableroBuscaminas;

    /**
     * Creates new form PantallaJuego
     */
    public PantallaJuego() {
        initComponents();
        cargaControles();
        crearTableroBuscaminas();
    }
    
    private void crearTableroBuscaminas(){
        tableroBuscaminas = new Tablero(numFilas, numcolumnas, numMinas);
        tableroBuscaminas.setEventoPartidaPerdida(new Consumer<List<Compartimiento>>() {
            @Override
            public void accept(List<Compartimiento> t) {
                for(Compartimiento casillaConMina: t){
                    botonesTablero[casillaConMina.getPosicionFila()][casillaConMina.getPosicionColumna()].setText("*");
                }
            }
            
        });
        tableroBuscaminas.setEventoCasillaAbierta(new Consumer<Compartimiento>() {
            @Override
            public void accept(Compartimiento t) {
               botonesTablero[t.getPosicionFila()][t.getPosicionColumna()].setEnabled(false);
               botonesTablero[t.getPosicionFila()][t.getPosicionColumna()].setText(t.getNumMinasAlrededor()+"");
            }
        });
        tableroBuscaminas.imprimirTablero();   
    }

    private void cargaControles() {
        int posXReferencia = 25;
        int posYReferencia = 25;
        int anchoControl = 30;
        int altoControl = 30;

        botonesTablero = new JButton[numFilas][numcolumnas];
        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + j);
                botonesTablero[i][j].setBorder(null);
                if (i == 0 && j == 0) {
                    botonesTablero[i][j].setBounds(posXReferencia, posYReferencia,
                            anchoControl, altoControl);
                }else if(i==0 && j!=0){
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i][j-1].getX()+botonesTablero[i][j-1].getWidth(),
                            posYReferencia, anchoControl, altoControl);
                }else{
                     botonesTablero[i][j].setBounds(
                            botonesTablero[i-1][j].getX(),
                            botonesTablero[i-1][j].getY()+botonesTablero[i-1][j].getHeight(), 
                            anchoControl, altoControl);
                }
                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  
                        btnClick(e);    
                    }
                });
                getContentPane().add(botonesTablero[i][j]);
            }
        }
    }
    
    private void btnClick(ActionEvent e){
        JButton btn=(JButton)e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila=Integer.parseInt(coordenada[0]);
        int posColumna=Integer.parseInt(coordenada[1]);
        JOptionPane.showMessageDialog(rootPane, posFila+","+posColumna);
        tableroBuscaminas.seleccionarCasilla(posFila, posColumna);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
