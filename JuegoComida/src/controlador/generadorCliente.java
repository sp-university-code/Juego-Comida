package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import juegocomida.JuegoComida;
import static juegocomida.JuegoComida.usuarioActual;
import modelo.Cliente;
import modelo.Usuario;

/**
 *
 * @author Jorge
 */
public class generadorCliente implements Runnable{
    private HBox hBxClientela;
    private Label lblNivel;
    private Label lblVida;
    private Label lblScore;
    private VBox vBxMenu;
    private VBox vBxCocinando;


    public generadorCliente(HBox hBxClientela, Label lblNivel, Label lblVida, Label lblScore, VBox vBxMenu, VBox vBxCocinando) {
        this.hBxClientela = hBxClientela;
        this.lblNivel = lblNivel;
        this.lblVida = lblVida;
        this.lblScore = lblScore;
        this.vBxMenu=vBxMenu;
        this.vBxCocinando=vBxCocinando;
    }
    
    
    /*
    Cuerpo del hilo, los clientes iran apareciendo mientras la ventana siga abierta
    al cerrar se guardan los progresos
    */
    @Override
    public void run() {
        while(VistaIngresoController.seguir){
            
            try {
                Cliente cliente = new Cliente(hBxClientela,lblNivel,lblVida,lblScore,vBxMenu,vBxCocinando);
                Thread c = new Thread(cliente);
                c.start();
                Platform.runLater(()->hBxClientela.getChildren().add(cliente.getCuadroCliente()));
                Thread.sleep(2400);
            } catch (InterruptedException ex) {
                Logger.getLogger(generadorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        for(Usuario u : Usuario.getUsuarios()){
            if(u.equals(JuegoComida.usuarioActual)){
                u.setNivel(JuegoComida.usuarioActual.getNivel());
                u.setVida(JuegoComida.usuarioActual.getVida());
                u.setScore(JuegoComida.usuarioActual.getScore());
            }
        }
        JuegoComida.usuarioActual=null;
    }
    
    
}
