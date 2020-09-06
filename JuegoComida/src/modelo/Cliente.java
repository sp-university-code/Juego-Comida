/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    

    public Cliente(HBox hBxClientela) {
        this.hBxClientela = hBxClientela;
        imagenCliente.setFitWidth(30);
        imagenCliente.setFitWidth(30);
        cuadroCliente.getChildren().addAll(hComida,imagenCliente,lblPaciencia,servir);
        servir.setOnMouseClicked(e -> removerCliente());
        
    }
    
    public void removerCliente(){
        this.hBxClientela.getChildren().remove(this.cuadroCliente);
        System.out.println("Cliente se fue");
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
 
    public int generarPaciencia(){
        Random r = new Random();
        return r.nextInt(6-3)+3;
    }
    
}
