/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MiControlador;
import VistaBuscaminas.IniciarSecionController;
import VistaBuscaminas.InicioBuscaminasController;
import VistaBuscaminas.RegistroController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Inicio extends Application {

    private Stage primaryStage;
    private Stage primaryStagePausa;
    public static Scene scenePausa;
    private BorderPane rootLaoyut; // Es el borderPane
    public static Scene sceneT;
    public Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStagePausa = new Stage();
        primaryStage.setTitle("Buscaminas"); // Titulo
        primaryStage.getIcons().add(new Image("imgs/mnsw.png"));//El icono de el juego
        initRootLayout();
    }

    public void initRootLayout() {

        try {

            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Inicio.class.getResource("/VistaBuscaminas/InicioBuscaminas.fxml"));

            rootLaoyut = (BorderPane) loader.load();

            // Crear la escena
            sceneT = new Scene(rootLaoyut);
            primaryStage.setScene(sceneT);
            primaryStage.setResizable(false);

            // creamos el controlador de la ventana
            // enviamos el administrador Principal a la ventana abierta
            InicioBuscaminasController controlador = loader.getController();
            controlador.setProgramaPrincipal(this);

            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void llamarSegundaVentana() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Inicio.class.getResource("/VistaBuscaminas/IniciarSecion.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            // Crear la escena
            sceneT = new Scene(rootLaoyut);
            primaryStage.setScene(sceneT);
            primaryStage.setResizable(false);

            IniciarSecionController controlador = loader.getController();
            controlador.setProgramaPrincipal(this);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void llamarRegistro() {

        try {

            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Inicio.class.getResource("/VistaBuscaminas/Registro.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            // Crear la escena
            sceneT = new Scene(rootLaoyut);
            primaryStage.setScene(sceneT);
            primaryStage.setResizable(false);

            // creamos el controlador de la ventana
            // Tetris ventanaAbierta = loader.getController();
            // ventanaAbierta.setProgramaTetris(this);
            RegistroController controlador = loader.getController();
            controlador.setProgramaPrincipal(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    

    public void abrirJuego() {

        try {

            // Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            root = FXMLLoader.load(Inicio.class.getResource("/VistaBuscaminas/JuegoBuscaminas.fxml"));
            //ImageView BUSCAMINASJUEGO = new ImageView("Imagenes/BUSCAMINASJUEGO.jpg");

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            //primaryStage.getIcons().add(new Image("imgs/mnsw.png"));
           

            MiControlador controlador = loader.getController();
            controlador.setProgramaPrincipal(this);
            primaryStagePausa.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    /*
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
