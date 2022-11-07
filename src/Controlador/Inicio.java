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
import javafx.scene.layout.Pane;
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
            Pane ventana = (Pane) loader.load();

            // Muestra la escena que contiene el diseño raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // creamos el controlador de la ventana
            // enviamos el administrador Principal a la ventana abierta
            InicioBuscaminasController ventanaAbierta = loader.getController();
            ventanaAbierta.setProgramaPrincipal(this);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void llamarSegundaVentana() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(NewFXMain.class.getResource("/VistaBuscaminas/IniciarSecion.fxml"));
            Pane ventana = (Pane) loader.load();

            // Muestra la escena que contiene el diseño raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*

    public void llamarJuegoTetris() {

        try {

            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/TetrisJuegoVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            // Crear la escena
            sceneT = new Scene(rootLaoyut);
            primaryStage.setScene(sceneT);
            primaryStage.setResizable(false);

            // creamos el controlador de la ventana
            // Tetris ventanaAbierta = loader.getController();
            // ventanaAbierta.setProgramaTetris(this);
            TetrisJuegoVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    public void abrirVentanaPausa() {

        try {

            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/PausaVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            // Crear la escena
            scenePausa = new Scene(rootLaoyut);
            primaryStagePausa.setScene(scenePausa);
            primaryStagePausa.setResizable(false);

            PausaVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStagePausa.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    public void abrirVentanaGameOver() {
        try {
            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/GameOverVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();
            // Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            GameOverVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    public void llamarNivel() {
        try {
            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/NivelVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();
            // Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            NivelVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    public void llamarRanking() {
        try {
            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/rankingVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();
            // Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            RankingVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }
    
    public void llamarComoQuitar() {
        try {
            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/GaleriaVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();
            // Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            Galeria_imagenesController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }
    
    
    public void abririntrucciones() {
        try {
            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/GaleriaVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();
            // Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            Galeria_imagenesController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

     */
    public Scene getSceneT() {
        return sceneT;
    }

    public void setSceneT(Scene sceneT) {
        this.sceneT = sceneT;
    }

    public Stage getPrimaryStagePausa() {
        return primaryStagePausa;
    }
}
