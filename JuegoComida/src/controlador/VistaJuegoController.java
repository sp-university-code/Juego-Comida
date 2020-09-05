/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private HBox hBxClientela;
    @FXML
    private VBox vBxCocinando;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
