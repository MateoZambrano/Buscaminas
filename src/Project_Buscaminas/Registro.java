/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Buscaminas;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author diego
 */
public class Registro extends JFrame {
    
    private JLabel imagenVentana2;
    private JButton guardarRegistro;
    private JTextField cajaTextoNombre;
    
    public static void Registro() {
        
    }
    
    public Registro() {
//Tamano de la ventana de registro
        this.setSize(800, 650);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(null);

//Se agrega caracteristicas a la ventana de registro
//Imagen de fonto
        imagenVentana2 = new JLabel();
        Image img = new ImageIcon("src/Imagenes/Registro.jpg").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(600, 600, Image.SCALE_SMOOTH));
        imagenVentana2.setIcon(img2);
        imagenVentana2.setBounds(90, 0, 800, 600);
        getContentPane().add(imagenVentana2);

//Boton guardar
        guardarRegistro = new JButton("GUARDAR");
        guardarRegistro.setBounds(325, 400, 150, 35);
        guardarRegistro.setBackground(Color.orange);
        getContentPane().add(guardarRegistro);

//Caja de texto para registro del nombre
        cajaTextoNombre = new JTextField(50);
        cajaTextoNombre.setBounds(250, 340, 280, 30);
        cajaTextoNombre.setBackground(Color.GRAY);
        getContentPane().add(cajaTextoNombre);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
