/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JuegoBuscaminas extends Application {
    private Stage primaryStage;
    private Stage primaryStagePausa;
    public static Scene scenePausa;
    private BorderPane rootLaoyut; // Es el borderPane
    public static Scene sceneT;
    
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStagePausa = new Stage();
        primaryStage.setTitle("Buscaminas"); // Titulo

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JuegoBuscaminas.class.getResource("/VistaBuscaminas/JuegoBuscaminas.fxml"));
            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

