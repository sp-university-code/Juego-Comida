package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import juegocomida.JuegoComida;
import modelo.Comida;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class VistaJuegoController implements Initializable {

    @FXML
    private Label lblNivel;
    @FXML
    private Label lblTotal;
    @FXML
    private VBox vBxMenu;
    @FXML
    private VBox vBxCocinando;
    @FXML
    private Label lblCocinando;
    @FXML
    private HBox hBxClientela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Thread gc = new Thread(new generadorCliente(hBxClientela));
        gc.start();
        vBxCocinando.setAlignment(Pos.CENTER);
        for(String categoria:Comida.getCategoriaComida().keySet()){
            HBox Hcategoria = new HBox();
            Hcategoria.setSpacing(8);
            Label nombreCategoria = new Label(categoria);
            for(int nivel : Comida.getCategoriaComida().get(categoria).keySet()){
                if(nivel <= JuegoComida.usuarioActual.getNivel()){
                    for(Comida comida:Comida.getCategoriaComida().get(categoria).get(nivel)){
                        ImageView imagenComida = new ImageView(new Image("/recursos/"+categoria+"/"+comida.getNombreArchivo()));
                        imagenComida.setFitHeight(60);
                        imagenComida.setFitWidth(60);
                        Button b = new Button("",imagenComida);
                        b.setStyle("-fx-pref-height:120px; -fx-pref-width:105px; -fx-base:coral; -fx-border-radius:30; -fx-background-radius:30; -fx-body-shadow-highlight-color:f0f0f0;");
                        b.setOnAction(e -> cocinar(categoria, comida));
                        Hcategoria.getChildren().add(b);
                    }
                    
                }
            }
            Hcategoria.setAlignment(Pos.CENTER);
            vBxMenu.getChildren().addAll(nombreCategoria,Hcategoria);
        }
        
    }    
    
    public void cocinar(String categoria, Comida comida){
        ImageView imagenComida = new ImageView(new Image("/recursos/"+categoria+"/"+comida.getNombreArchivo()));
        imagenComida.setFitHeight(60);
        imagenComida.setFitWidth(60);
        Label lblComida = new Label();
        lblComida.setGraphic(imagenComida);
        lblComida.setOnMouseClicked(e->comidaSeleccionada(lblComida));
        vBxCocinando.getChildren().add(lblComida);
    }
    
    public void comidaSeleccionada(Label lbl){
        lbl.setStyle("-fx-border-color:blue;");
    }
}
