/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyBuscaminas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Mateo
 */
public class Tablero {

    Compartimiento[][] casillas;

    int numFilas;
    int numColumnas;
    int numeroMinas;

    int numCasillasAbierta;
    boolean juegoTerminado;

    private Consumer<List<Compartimiento>> eventoPartidaPerdida;
    private Consumer<List<Compartimiento>> eventoPartidaGanada;

    private Consumer<Compartimiento> eventoCasillaAbierta;

    public Tablero(int numFilas, int numColumnas, int numeroMinas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numeroMinas = numeroMinas;
        iniciarCasillas();
    }

    public void iniciarCasillas() {
        casillas = new Compartimiento[this.numFilas][this.numColumnas];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new Compartimiento(i, j);
            }
        }
        generarMinas();
    }

    private void generarMinas() {
        int minasGeneradas = 0;
        while (minasGeneradas != numeroMinas) {
            int posTmpFila = (int) (Math.random() * casillas.length);
            int posTmColumna = (int) (Math.random() * casillas[0].length);
            if (!casillas[posTmpFila][posTmColumna].isTieneMina()) {
                casillas[posTmpFila][posTmColumna].setTieneMina(true);
                minasGeneradas++;
            }
        }
        actualizarNumeroMinasAlrededor();
    }

    public void imprimirTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].isTieneMina() ? "*" : "0");
            }
            System.out.println("");
        }
    }

    private void imprimirPistas() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].getNumMinasAlrededor());
            }
            System.out.println("");
        }
    }

    private void actualizarNumeroMinasAlrededor() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].isTieneMina()) {
                    List<Compartimiento> casillasAlrededor = obtenerCasillasAlrededor(i, j);
                    casillasAlrededor.forEach((c) -> c.incrementarNumeroMinasAlrededor());
                }
            }
        }
    }

    private List<Compartimiento> obtenerCasillasAlrededor(int posFila, int posColumna) {
        List<Compartimiento> listaCasillas = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            int tmpPosFila = posFila;
            int tmpPosColumna = posColumna;
            switch (i) {
                case 0:
                    tmpPosFila--;
                    break; //Arriba
                case 1:
                    tmpPosFila--;
                    tmpPosColumna++;
                    break; //Arriba Derecha
                case 2:
                    tmpPosColumna++;
                    break; //Derecha
                case 3:
                    tmpPosColumna++;
                    tmpPosFila++;
                    break; //Derecha abajo
                case 4:
                    tmpPosFila++;
                    break; //Abajo
                case 5:
                    tmpPosFila++;
                    tmpPosColumna--;
                    break; //Abajo izquierda
                case 6:
                    tmpPosColumna--;
                    break; //Izquierda
                case 7:
                    tmpPosFila--;
                    tmpPosColumna--;
                    break; //Izquierda arriba
            }
            if (tmpPosFila >= 0 && tmpPosFila < this.casillas.length
                    && tmpPosColumna >= 0 && tmpPosColumna < this.casillas[0].length) {
                listaCasillas.add(this.casillas[tmpPosFila][tmpPosColumna]);
            }
        }
        return listaCasillas;
    }
    
    List<Compartimiento> obtenerCasillasConMinas(){
         List<Compartimiento> casillasConMinas = new LinkedList<>();
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[i].length; j++) {
                    if (casillas[i][j].isTieneMina()) {
                        casillasConMinas.add(casillas[i][j]);
                    }
                }
            }
            return casillasConMinas;
    }

    public void seleccionarCasilla(int posFila, int posColumna) {
        eventoCasillaAbierta.accept(this.casillas[posFila][posColumna]);
        if (this.casillas[posFila][posColumna].isTieneMina()) {
            List<Compartimiento> casillasConMinas = new LinkedList<>();
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[i].length; j++) {
                    if (casillas[i][j].isTieneMina()) {
                        casillasConMinas.add(casillas[i][j]);
                    }
                }
            }
            eventoPartidaPerdida.accept(casillasConMinas);
        } else if (this.casillas[posFila][posColumna].getNumMinasAlrededor() == 0) {
            marcarCasillaAbierta(posFila, posColumna);
            List<Compartimiento> casillasAlrededor = obtenerCasillasAlrededor(posFila, posColumna);
            for (Compartimiento casilla : casillasAlrededor) {
                if (!casilla.isAbierta()) {
                    seleccionarCasilla(casilla.getPosicionFila(), casilla.getPosicionColumna());
                }
            }
        }else{
            marcarCasillaAbierta(posFila, posColumna);
        }
        if(partidaGanada()){
            //eventoPartidaGanada.accept();
        }
    }
    
    void marcarCasillaAbierta(int posFila,int posColumna){
        if(!this.casillas[posFila][posColumna].isAbierta()){
            numCasillasAbierta++;
            this.casillas[posFila][posColumna].setAbierta(true);
        }
    }
    
    boolean partidaGanada(){
        return numCasillasAbierta >= (numFilas*numColumnas)- numeroMinas;
    }

    public static void main(String[] args) {
        Tablero tablero = new Tablero(5, 5, 5);
        tablero.imprimirTablero();
        System.out.println("____________________");
        tablero.imprimirPistas();
    }

    /**
     * @param eventoPartidaPerdida the eventoPartidaPerdida to set
     */
    public void setEventoPartidaPerdida(Consumer<List<Compartimiento>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }

    /**
     * @param eventoCasillaAbierta the eventoCasillaAbierta to set
     */
    public void setEventoCasillaAbierta(Consumer<Compartimiento> eventoCasillaAbierta) {
        this.eventoCasillaAbierta = eventoCasillaAbierta;
    }

    /**
     * @param eventoPartidaGanada the eventoPartidaGanada to set
     */
    public void setEventoPartidaGanada(Consumer<List<Compartimiento>> eventoPartidaGanada) {
        this.eventoPartidaGanada = eventoPartidaGanada;
    }
}
