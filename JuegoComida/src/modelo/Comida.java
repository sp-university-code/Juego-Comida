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
    private static HashMap<String, HashMap<Integer,ArrayList<Comida>>> categoriaComida = new HashMap<>();
    
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

    public static HashMap<String, HashMap<Integer, ArrayList<Comida>>> getCategoriaComida() {
        return categoriaComida;
    }

    public static void setCategoriaComida(HashMap<String, HashMap<Integer, ArrayList<Comida>>> categoriaComida) {
        Comida.categoriaComida = categoriaComida;
    }
    
    @Override
    public String toString() {
        return "Comida{" + "nombre=" + nombre + ", nombreArchivo=" + nombreArchivo + '}';
    }
    
    
    
    
    
    
    
    
}
