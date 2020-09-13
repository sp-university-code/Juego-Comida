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
    private int paciencia = generarPaciencia();
    private Label lblPaciencia = new Label(paciencia+"");
    private Button servir = new Button("Servir");
    private static HashMap<Comida, ImageView> pedidosCliente = new HashMap<>();
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
        imagenCliente.setFitWidth(100);
        imagenCliente.setFitHeight(100);
        cuadroCliente.setSpacing(3);
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
        System.out.println("Se vacio lista: "+VistaJuegoController.getComidaCocinada());
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
            System.out.println("Hola1");
            return false;
        }
        for(Comida c:comidaCocinada){
            if(!comidaPedidos.contains(c)){
                System.out.println("Hola2");
                return false;
            }
                
        }
        return true;
    }
    
    public void removerCliente(){
        this.hBxClientela.getChildren().remove(this.cuadroCliente);
        System.out.println("Cliente se fue ");
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
    
    public int generarPaciencia(){
        Random r = new Random();
        return r.nextInt(17-3)+3;
    }
    
    public void generarComida(){
        for(String categoria:Comida.getCategoriaComida().keySet()){
            for(int nivel : Comida.getCategoriaComida().get(categoria).keySet()){
                if(nivel <= JuegoComida.usuarioActual.getNivel()){
                    System.out.println(nivel+" "+JuegoComida.usuarioActual.getNivel());
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
    
    public int generarAleatorio(int limite, int inicio){
        Random r = new Random();
        return (r.nextInt(limite))+inicio;
    }
    
    public void llenarPedidos(){
        hComida.getChildren().clear();
        generarComida();
        List<Comida> comidas = new ArrayList<>();
        for(Comida comida:pedidosCliente.keySet()){
            comidas.add(comida);
        }
        for(int i=0; i<generarAleatorio(4,1);i++){
            int indice = generarAleatorio(comidas.size(),0);
             comidaPedidos.add(comidas.get(indice));
             Label lbl =new Label();
             ImageView imagen = pedidosCliente.get(comidas.get(indice));
             lbl.setGraphic(imagen);
             hComida.getChildren().add(lbl);
        }
    }
}
