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
    

    public Cliente(HBox hBxClientela) {
        this.hBxClientela = hBxClientela;
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
        System.out.println(VistaJuegoController.getComidaCocinada());
        System.out.println(comidaPedidos);
        comidaCocinada=VistaJuegoController.getComidaCocinada();
        System.out.println(comidaCocinada);
        if(compararComidas()){
            VistaIngresoController.mostrarAlerta("", "ClienteComplacido", Alert.AlertType.INFORMATION);
        }
        VistaJuegoController.getComidaCocinada().clear();
        System.out.println("Se vacio lista: "+VistaJuegoController.getComidaCocinada());
    }
    
    public boolean compararComidas(){
        if(comidaCocinada.size()!= comidaPedidos.size()){
            System.out.println("comidaCocinada :"+comidaCocinada.size());
            System.out.println("comidaPedidos :"+comidaPedidos.size());
            System.out.println(comidaCocinada.size()!= comidaPedidos.size());
            System.out.println(comidaCocinada.size()!= comidaPedidos.size());
            
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
        return r.nextInt(12-3)+3;
    }
    
    public void generarComida(){
        for(String categoria:Comida.getCategoriaComida().keySet()){
            for(int nivel : Comida.getCategoriaComida().get(categoria).keySet()){
                if(nivel <= JuegoComida.usuarioActual.getNivel()){
                    for(Comida comida:Comida.getCategoriaComida().get(categoria).get(nivel)){
                        ImageView imagenComida = new ImageView(new Image("/recursos/"+categoria+"/"+comida.getNombreArchivo()));
                        imagenComida.setFitHeight(60);
                        imagenComida.setFitWidth(60);
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
