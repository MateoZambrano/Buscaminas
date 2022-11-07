/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import VistaBuscaminas.IniciarSecionController;
import VistaBuscaminas.JuegoBuscaminasController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewFXMain extends Application {

    private Stage primaryStage;
    private Stage primaryStagePausa;
    public static Scene scenePausa;
    private BorderPane rootLaoyut; // Es el borderPane
    public static Scene sceneT;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStagePausa = new Stage();
        primaryStage.setTitle("Buscaminas"); // Titulo
        initRootLayout();
    }

    public void initRootLayout() {

        try {

            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(NewFXMain.class.getResource("/VistaBuscaminas/IniciarSecion.fxml"));
            Pane ventana = (Pane) loader.load();

            // Muestra la escena que contiene el diseño raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // creamos el controlador de la ventana
            // enviamos el administrador Principal a la ventana abierta
            IniciarSecionController ventanaAbierta = loader.getController();
            ventanaAbierta.setProgramaPrincipal(this);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void llamarSegundaVentanaJuego() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JuegoBuscaminas.class.getResource("/VistaBuscaminas/JuegoBuscaminas.fxml"));
            Pane ventana = (Pane) loader.load();

            // Muestra la escena que contiene el diseño raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            JuegoBuscaminasController ventanaAbierta = loader.getController();
            ventanaAbierta.setProgramaPrincipal(this);
            
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
