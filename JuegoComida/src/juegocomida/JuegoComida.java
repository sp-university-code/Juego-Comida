/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocomida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Jorge
 */
public class JuegoComida extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaIngreso.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("recursos/Logo-Cooking.png"));
        stage.setTitle("Cooking Craze");
        stage.show();
    }
    
    public static void cambiarScene(Stage stage, Scene scene){
        stage.setScene(scene);
    }
}
