package juegocomida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Comida;

/**
 *
 * @author Jorge
 */
public class JuegoComida extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        leerAchivo();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaIngreso.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("recursos/Logo-Cooking.png"));
        stage.setTitle("Cooking Craze");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void leerAchivo(){
        try {
            List<String> lineas = Files.readAllLines(Paths.get("src/recursos/catalogoImagenes.csv"));
            lineas.remove(0);
            for(String linea:lineas){
                String[] separacion = linea.split(",");
                Comida c = new Comida(separacion[2],separacion[0]);
                Comida.getNivelesComida().putIfAbsent(Integer.parseInt(separacion[3]), new HashMap<String, ArrayList<Comida>>());
                Comida.getNivelesComida().get(Integer.parseInt(separacion[3])).putIfAbsent(separacion[1], new ArrayList<Comida>());
                Comida.getNivelesComida().get(Integer.parseInt(separacion[3])).get(separacion[1]).add(c);
            }
            System.out.println(Comida.getNivelesComida());
        } catch (IOException ex) {
            Logger.getLogger(JuegoComida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
