/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import VistaBuscaminas.InicioBuscaminasController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Inicio extends Application {

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
            loader.setLocation(Inicio.class.getResource("/VistaBuscaminas/InicioBuscaminas.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            // Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // creamos el controlador de la ventana
            // enviamos el administrador Principal a la ventana abierta
            InicioBuscaminasController ventanaAbierta = loader.getController();
            ventanaAbierta.setProgramaPrincipal(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");

        }
        
    }
}
