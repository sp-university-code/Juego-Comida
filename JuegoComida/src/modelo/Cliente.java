package modelo;

import controlador.VistaIngresoController;
import controlador.VistaJuegoController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import juegocomida.JuegoComida;

/**
 *
 * @author Jorge
 */
public class Cliente implements Runnable{
    private VBox cuadroCliente = new VBox();
    private HBox hBxClientela;
    private HBox hComida = new HBox();
    private ImageView imagenCliente = new ImageView("/recursos/cliente.jpg");
    private int paciencia = generarAleatorio(8,3);
    private Label lblPaciencia = new Label(paciencia+"");
    private Button servir = new Button("Servir");
    private HashMap<Comida, ImageView> pedidosCliente = new HashMap<>();
    private List<Comida> comidaPedidos = new ArrayList<>();
    private List<Comida> comidaCocinada = new ArrayList<>();
    private Label lblNivel;
    private Label lblVida;
    private Label lblScore;
    private VBox vBxMenu;
    private VBox vBxCocinando;
    

    public Cliente(HBox hBxClientela, Label lblNivel, Label lblVida, Label lblScore, VBox vBxMenu, VBox vBxCocinando) {
        this.hBxClientela = hBxClientela;
        this.lblNivel = lblNivel;
        this.lblVida = lblVida;
        this.lblScore = lblScore;
        this.vBxMenu=vBxMenu;
        this.vBxCocinando=vBxCocinando;
        lblPaciencia.setStyle("-fx-pref-height: 20px;-fx-pref-width: 10px; -fx-text-alignment:center ;-fx-font-weight:bold;-fx-font-size:14;-fx-text-fill:white;-fx-border-radius: 5;-fx-background-color: dimgray;-fx-font-family:Lucida Console;");
        imagenCliente.setFitWidth(100);
        imagenCliente.setFitHeight(100);
        cuadroCliente.setSpacing(10);
        cuadroCliente.setAlignment(Pos.CENTER);
        hComida.setSpacing(3);
        llenarPedidos();
        cuadroCliente.getChildren().addAll(hComida,imagenCliente,lblPaciencia,servir);
        servir.setOnMouseClicked(e -> servir());
        
    }
    
    public void servir(){
        comidaCocinada=VistaJuegoController.getComidaCocinada();
        vBxCocinando.getChildren().clear();
        if(compararComidas()){
            JuegoComida.usuarioActual.subirScore();
            lblScore.setText(JuegoComida.usuarioActual.getScore()+"");
            removerCliente();
            if(JuegoComida.usuarioActual.getScore()==400 || JuegoComida.usuarioActual.getScore()==800){
                JuegoComida.usuarioActual.subirNivel();
                lblNivel.setText(JuegoComida.usuarioActual.getNivel()+"");
                vBxMenu.getChildren().clear();
                new VistaJuegoController().cargarBotonesComida(vBxMenu,vBxCocinando);
                VistaIngresoController.mostrarAlerta("Level Up","Felicitaciones, ha alcanzado el nivel "+JuegoComida.usuarioActual.getNivel(), Alert.AlertType.INFORMATION);
            }
            if(JuegoComida.usuarioActual.getScore()==1200){
                reiniciarUsuario();
                VistaIngresoController.mostrarAlerta("","Felicitaciones, Ganaste!!", Alert.AlertType.INFORMATION);
                reiniciarPantalla();
            }
        }
        else{
            JuegoComida.usuarioActual.pederVida();
            lblVida.setText(JuegoComida.usuarioActual.getVida()+"");
            if(JuegoComida.usuarioActual.getVida()<=0){
                reiniciarUsuario();
                VistaIngresoController.mostrarAlerta("Game Over","Lo sentimos, perdiste", Alert.AlertType.INFORMATION);
                reiniciarPantalla();
            }
        }
        VistaJuegoController.getComidaCocinada().clear();
    }
    
    
    
    public void reiniciarUsuario(){
        JuegoComida.usuarioActual.setVida(5);
        JuegoComida.usuarioActual.setNivel(1);
        JuegoComida.usuarioActual.setScore(0);
    }
    public void reiniciarPantalla(){
        vBxMenu.getChildren().clear();
        new VistaJuegoController().cargarBotonesComida(vBxMenu,vBxCocinando);
        hComida.getChildren().clear();
        pedidosCliente.clear();
        comidaPedidos.clear();
        llenarPedidos();
        lblVida.setText(JuegoComida.usuarioActual.getVida()+"");
        lblNivel.setText(JuegoComida.usuarioActual.getNivel()+"");
        lblScore.setText(JuegoComida.usuarioActual.getScore()+"");
    }
    public boolean compararComidas(){
       
        if(comidaCocinada.size()!= comidaPedidos.size()){      
            return false;
        }
        for(Comida c:comidaCocinada){
            if(!comidaPedidos.contains(c)){
                
                return false;
            }
                
        }
        return true;
    }
    
    public void removerCliente(){
        this.hBxClientela.getChildren().remove(this.cuadroCliente);
    }
    
    @Override
    public void run() {
        while(paciencia>0){
            try {
                paciencia--;
                Platform.runLater(()->lblPaciencia.setText(paciencia+""));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Platform.runLater(() -> removerCliente());
    }

    public VBox getCuadroCliente() {
        return cuadroCliente;
    }

    public List<Comida> getComidaCocinada() {
        return comidaCocinada;
    }
        
    
    /*
        Comprueba el nivel actual del jugador actual y en base a eso genera las comidas disponibles
    */
    public void generarComida(){
        hComida.getChildren().clear();
        pedidosCliente.clear();
        comidaPedidos.clear();
        for(String categoria:Comida.getCategoriaComida().keySet()){
            for(int nivel : Comida.getCategoriaComida().get(categoria).keySet()){
                if(nivel <= JuegoComida.usuarioActual.getNivel()){
                    for(Comida comida:Comida.getCategoriaComida().get(categoria).get(nivel)){
                        ImageView imagenComida = new ImageView(new Image("/recursos/"+categoria+"/"+comida.getNombreArchivo()));
                        imagenComida.setFitHeight(55);
                        imagenComida.setFitWidth(55);
                        pedidosCliente.putIfAbsent(comida, imagenComida);
                    }
                    
                }
            }
        }
    }
    
    /*
        devuelve un numero aleatorio
        primero genera un numero aleatorio entre 0 y limite-1
        luego a eso le suma inicio
        el resuldo de esa operacion es el retornado
    */
    public int generarAleatorio(int limite, int inicio){
        Random r = new Random();
        return (r.nextInt(limite))+inicio;
    }
    /*
        Llama al metodo generarComida() para obtener las comidas disponibles en el nivel actual y genera los pedidos del 
        cliente de forma aleatoria hasta un maximo de 4 comidas, una condicion agreda es que el usuario no puede repetir
        comida
    */
    public void llenarPedidos(){
        generarComida();
        List<Comida> comidas = new ArrayList<>();
        for(Comida comida:pedidosCliente.keySet()){
            comidas.add(comida);
        }
        for(int i=1; i<=generarAleatorio(4,1);i++){
            int indice = generarAleatorio(comidas.size(),0);
            if(!comidaPedidos.contains(comidas.get(indice))){
                comidaPedidos.add(comidas.get(indice));
                ImageView imagen = pedidosCliente.get(comidas.get(indice));
                Label lbl =new Label();
                lbl.setGraphic(imagen);
                hComida.getChildren().add(lbl);
            }
        }
    }
}
