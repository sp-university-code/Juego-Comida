/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jorge
 */
public class Comida {
    private String nombre;
    private String nombreArchivo;
    private String nombreCarpeta;

    public Comida(String nombre, String nombreArchivo, String nombreCarpeta) {
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
        this.nombreCarpeta = nombreCarpeta;
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

    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }

    @Override
    public String toString() {
        return "Comida{" + "nombre=" + nombre + ", nombreArchivo=" + nombreArchivo + ", nombreCarpeta=" + nombreCarpeta + '}';
    }
    
    
    
    
}
