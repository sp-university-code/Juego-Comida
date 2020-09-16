package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import juegocomida.JuegoComida;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class VistaIngresoController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnJugar;
    public static boolean seguir = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /*
        Al dar clic sobre el boton jugar se abre la pantalla del juego, ademas se identifica al usuario ingresado,
        para en tal caso que tenga prograso, cargarlos automaticamente.
    */
    @FXML
    private void iniciarJuego(ActionEvent event) {
        if(txtUsuario.getText().equals("")){
        mostrarAlerta("Error de Ingreso","Error: No se ha ingresado usuario",AlertType.ERROR);
        }
        else{
            seguir = true;
            JuegoComida.cargarUsuario(txtUsuario.getText());
            
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaJuego.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                Image image = new Image("recursos/cursor.png");  //pass in the image path
                scene.setCursor(new ImageCursor(image));
                stage.getIcons().add(new Image("recursos/Logo-Cooking.png"));
                stage.setTitle("Cooking Craze");
                stage.setMaximized(true);
                stage.setResizable(false);
                stage.show();
                stage.setOnCloseRequest(e -> seguir=false);
            } catch (IOException ex) {
                Logger.getLogger(VistaIngresoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void mostrarAlerta(String titulo, String mensaje, AlertType e){
        Alert alert = new Alert(e);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
