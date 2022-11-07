/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Buscaminas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author diego
 */
public class MenuBuscaminas extends JFrame {

    private JLabel imagenfondoPrincipal;
    private JButton botonIniciarJuego;
    private JButton botonRegistrarse;
    private JButton botonVerPuntuaciones;

    public MenuBuscaminas() {
        //Creamos ventana en el tamaño de pixeles
        this.setSize(800, 650);
        getContentPane().setBackground(Color.BLACK);

        getContentPane().setLayout(null);

        //Colocamos la imagen
        imagenfondoPrincipal = new JLabel();

        Image img = new ImageIcon("src/Imagenes/Principal.png").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(600, 600, Image.SCALE_SMOOTH));
        imagenfondoPrincipal.setIcon(img2);
        imagenfondoPrincipal.setBounds(90, 0, 800, 600);

        getContentPane().add(imagenfondoPrincipal);

        //coloco mis boton
        botonIniciarJuego = new JButton("INICIAR JUEGO");
        botonIniciarJuego.setBounds(325, 380, 150, 35);
        botonIniciarJuego.setBackground(Color.orange);
        getContentPane().add(botonIniciarJuego);

        botonRegistrarse = new JButton("REGISTRARSE");
        botonRegistrarse.setBounds(325, 425, 150, 35);
        botonRegistrarse.setBackground(Color.orange);
        getContentPane().add(botonRegistrarse);

        botonVerPuntuaciones = new JButton("VER PUNTUACIONES");
        botonVerPuntuaciones.setBounds(325, 470, 150, 35);
        botonVerPuntuaciones.setBackground(Color.orange);
        getContentPane().add(botonVerPuntuaciones);

        ActionListener escucha = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                if (evento.getSource().equals(botonRegistrarse)) {
                    Registro pantallaRegistro = new Registro();
                    pantallaRegistro.setVisible(true);
                }
            }
        };

        botonRegistrarse.addActionListener(escucha);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MenuBuscaminas ventanaInicialBuscaminas = new MenuBuscaminas();
        ventanaInicialBuscaminas.setVisible(true);
    }
}
