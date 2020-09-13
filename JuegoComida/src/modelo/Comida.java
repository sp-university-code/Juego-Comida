package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Jorge
 */
public class Comida {
    private String nombre;
    private String nombreArchivo;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comida other = (Comida) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "Comida{" + "nombre=" + nombre + ", nombreArchivo=" + nombreArchivo + '}';
    }
    
 
}
