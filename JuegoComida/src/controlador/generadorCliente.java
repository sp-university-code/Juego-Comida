/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modelo.Cliente;

/**
 *
 * @author Jorge
 */
public class generadorCliente implements Runnable{
    private Pane paneClientela;

    public generadorCliente(Pane paneClientela) {
        this.paneClientela = paneClientela;
    }


    @Override
    public void run() {
        while(!VistaIngresoController.salir){
            
            try {
                Cliente cliente = new Cliente(paneClientela);
                Thread c = new Thread(cliente);
                c.start();
                Platform.runLater(()->paneClientela.getChildren().add(cliente.getCuadroCliente()));
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(generadorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
}
