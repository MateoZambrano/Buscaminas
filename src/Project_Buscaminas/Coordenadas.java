/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Buscaminas;

/**
 *
 * @author diego
 */
public class Coordenadas {
    private int posicionFila;
    private int posicionColumna;
    private boolean validacionMina;

    public Coordenadas(int posicionFila, int posicionColumna) {
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
    }

       
    
    public int getPosicionFila() {
        return posicionFila;
    }

    public int getPosicionColumna() {
        return posicionColumna;
    }

    public boolean isValidacionMina() {
        return validacionMina;
    }

    public void setPosicionFila(int posicionFila) {
        this.posicionFila = posicionFila;
    }

    public void setPosicionColumna(int posicionColumna) {
        this.posicionColumna = posicionColumna;
    }

    public void setValidacionMina(boolean validacionMina) {
        this.validacionMina = validacionMina;
    }
    
    
}
