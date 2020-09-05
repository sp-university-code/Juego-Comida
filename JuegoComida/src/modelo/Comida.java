/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jorge
 */
public class Comida {
    private String nombre;
    private String nombreArchivo;
    //private String nombreCarpeta;
    private static HashMap<Integer, HashMap<String,ArrayList<Comida>>> nivelesComida = new HashMap<>();
    
    public Comida(String nombre, String nombreArchivo) {
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public static HashMap<Integer, HashMap<String, ArrayList<Comida>>> getNivelesComida() {
        return nivelesComida;
    }

    public static void setNivelesComida(HashMap<Integer, HashMap<String, ArrayList<Comida>>> nivelesComida) {
        Comida.nivelesComida = nivelesComida;
    }

    

    @Override
    public String toString() {
        return "Comida{" + "nombre=" + nombre + ", nombreArchivo=" + nombreArchivo + '}';
    }
    
    
    
    
    
    
    
    
}
