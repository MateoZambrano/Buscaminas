package ProyBuscaminas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateo
 */
public class Compartimiento {
    private int posicionFila;
    private int posicionColumna;
    private boolean tieneMina;
    private int numMinasAlrededor;
    private boolean abierta;
    
    // Buscaminas Pruebas

    public Compartimiento(int posicionFila, int posicionColumna) {
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
    }

    
    
    public int getPosicionFila() {
        return posicionFila;
    }

    public void setPosicionFila(int posicionFila) {
        this.posicionFila = posicionFila;
    }

    public int getPosicionColumna() {
        return posicionColumna;
    }

    public void setPosicionColumna(int posicionColumna) {
        this.posicionColumna = posicionColumna;
    }

    public boolean isTieneMina() {
        return tieneMina;
    }

    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }
    
    public int getNumMinasAlrededor() {
        return numMinasAlrededor;
    }
    
    public void setNumMinasAlrededor(int numMinasAlrededor){
        this.numMinasAlrededor = numMinasAlrededor;
    }
    
    public void incrementarNumeroMinasAlrededor(){
        this.numMinasAlrededor++;
    }
    
    public boolean isAbierta(){
        return abierta;
    }
    
    public void setAbierta(boolean abierta){
        this.abierta = abierta;
    }
    
    
}
