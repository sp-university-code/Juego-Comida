package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jorge
 */
public class Usuario implements Serializable{
    private String nUsuario;
    private int nivel=1;
    private int score=0;
    private int vida=5;
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String nUsuario) {
        this.nUsuario = nUsuario;
    }

    public String getnUsuario() {
        return nUsuario;
    }

    public void setnUsuario(String nUsuario) {
        this.nUsuario = nUsuario;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    public void subirNivel(){
        this.nivel+=1;
    }
    
    public void subirScore(){
        this.score+=100;
    }
    
    public void pederVida(){
        this.vida-=1;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(List<Usuario> usuarios) {
        Usuario.usuarios = usuarios;
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nUsuario, other.nUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nUsuario=" + nUsuario + ", nivel=" + nivel + ", score=" + score + '}';
    }
    
}
