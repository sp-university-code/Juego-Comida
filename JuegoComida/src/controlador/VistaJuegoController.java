package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import juegocomida.JuegoComida;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Comida;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class VistaJuegoController implements Initializable {
    @FXML
    private Label lblNivel;
    @FXML
    private VBox vBxMenu;
    @FXML
    private VBox vBxCocinando;
    @FXML
    private Label lblCocinando;
    @FXML
    private HBox hBxClientela;
    
    private static List<Comida> comidaCocinada = new ArrayList<>();
    @FXML
    private Label lblVida;
    @FXML
    private Label lblScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Thread gc = new Thread(new generadorCliente(hBxClientela,lblNivel,lblVida,lblScore,vBxMenu,vBxCocinando));
        gc.start();
        lblNivel.setText(JuegoComida.usuarioActual.getNivel()+"");
        lblVida.setText(JuegoComida.usuarioActual.getVida()+"");
        lblScore.setText(JuegoComida.usuarioActual.getScore()+"");
        
        
        
        vBxCocinando.setAlignment(Pos.CENTER);
        lblNivel.setText(JuegoComida.usuarioActual.getNivel()+"");
        cargarBotonesComida(vBxMenu,vBxCocinando);
        
    } 

    public static List<Comida> getComidaCocinada() {
        return comidaCocinada;
    }
    
    public void cargarBotonesComida(VBox Menu,VBox cocinando){
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
                        b.setOnAction(e -> cocinar(categoria, comida, cocinando));
                        Hcategoria.getChildren().add(b);
                    }
                    
                }
            }
            Hcategoria.setAlignment(Pos.CENTER);
            Menu.getChildren().addAll(nombreCategoria,Hcategoria);
        }
    }
    
    public void cocinar(String categoria, Comida comida,VBox cocinando){
        comidaCocinada.add(comida);
        ImageView imagenComida = new ImageView(new Image("/recursos/"+categoria+"/"+comida.getNombreArchivo()));
        imagenComida.setFitHeight(60);
        imagenComida.setFitWidth(60);
        Label lblComida = new Label();
        lblComida.setGraphic(imagenComida);
        
        cocinando.getChildren().add(lblComida);
    }
  
}
