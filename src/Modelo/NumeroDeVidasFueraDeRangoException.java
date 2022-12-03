/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Mateo
 */
class NumeroDeVidasFueraDeRangoException extends Exception {
    public NumeroDeVidasFueraDeRangoException(){
        super();
    }
    public NumeroDeVidasFueraDeRangoException(String msg){
        super(msg);
    }
}
