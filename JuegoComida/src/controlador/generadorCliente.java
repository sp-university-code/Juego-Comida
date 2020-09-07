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
    private HBox hBxClientela;

    public generadorCliente(HBox hBxClientela) {
        this.hBxClientela = hBxClientela;
    }

    @Override
    public void run() {
        while(!VistaIngresoController.salir){
            
            try {
                Cliente cliente = new Cliente(hBxClientela);
                Thread c = new Thread(cliente);
                c.start();
                Platform.runLater(()->hBxClientela.getChildren().add(cliente.getCuadroCliente()));
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(generadorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
}
