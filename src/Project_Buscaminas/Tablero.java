/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Buscaminas;

/**
 *
 * @author diego
 */
public class Tablero {

    Tablero[][] tableroMatriz;
    int numeroDeFilas;
    int numeroDeColumnas;
    int numeroDeMinas;
    
    public Tablero(int numeroDeFilas, int numeroDeColumnas){
        this.numeroDeFilas = numeroDeFilas;
        this.numeroDeColumnas = numeroDeColumnas;
    }
    
    public void iniciarTablero(){
        tableroMatriz = new Tablero[this.numeroDeFilas][this.numeroDeColumnas];
        for(int i=0; i< tableroMatriz.length; i++){
            for(int j=0; j< tableroMatriz[i].length; j++){
                tableroMatriz[i][j]= new Tablero(i,j);
            }
            
        }
    }
}
