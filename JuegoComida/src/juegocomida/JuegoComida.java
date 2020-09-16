package juegocomida;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
import modelo.Usuario;

/**
 *
 * @author Jorge
 */
public class JuegoComida extends Application{
    public static Usuario usuarioActual;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        deserealizar("usuarios");
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
        stage.setOnCloseRequest(e -> {
            serializar("usuarios");
            terminarRun();
            });
    }
    
    public static void terminarRun(){
            System.exit(0);
    }
    
    public static void leerAchivo(){
        try {
            List<String> lineas = Files.readAllLines(Paths.get("src/recursos/catalogoImagenes.csv"));
            lineas.remove(0);
            for(String linea:lineas){
                String[] separacion = linea.split(",");
                Comida c = new Comida(separacion[2],separacion[0]);
                Comida.getCategoriaComida().putIfAbsent(separacion[1], new HashMap<Integer, ArrayList<Comida>>());
                Comida.getCategoriaComida().get(separacion[1]).putIfAbsent(Integer.parseInt(separacion[3]), new ArrayList<Comida>());
                Comida.getCategoriaComida().get(separacion[1]).get(Integer.parseInt(separacion[3])).add(c);
            }
            System.out.println(Comida.getCategoriaComida());
        } catch (IOException ex) {
            Logger.getLogger(JuegoComida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cargarUsuario(String nick){
        if(Usuario.getUsuarios().size()>0){
            try{
                for(Usuario u : Usuario.getUsuarios()){
                    if(u.equals(new Usuario(nick))){
                        usuarioActual=u;
                        break;
                    }
                    else{
                        usuarioActual = new Usuario(nick); 
                    }
                }
                if(!Usuario.getUsuarios().contains(new Usuario(nick))){
                    Usuario.getUsuarios().add(new Usuario(nick));
                }
            }
            catch(ConcurrentModificationException e){
                System.out.println(e);
            }
        }
        else{
            usuarioActual = new Usuario(nick);
            Usuario.getUsuarios().add(new Usuario(nick));
        }
    }
    
    public static void serializar(String nombrearchivo){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/recursos/"+nombrearchivo+".dat"));) {
                os.writeObject(Usuario.getUsuarios());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        catch (IOException ex) {
               System.out.println(ex);
        }
    }
    
    public static void deserealizar(String nombrearchivo){
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/recursos/"+nombrearchivo+".dat"));) {
                //productos= (ArrayList<Producto>)is.readObject();
                Usuario.setUsuarios((List<Usuario>)is.readObject());
                System.out.println("Proceso de deserializacion culminado con exito");
        } catch (FileNotFoundException ex) {
            System.out.println("Aun no se ha serializado la lista!");
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        catch (ClassNotFoundException ex) {
               System.out.println(ex);
            }
        
    }
}
