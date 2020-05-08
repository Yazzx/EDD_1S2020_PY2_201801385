/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;
import proyecto2.Estructuras.ArbolB;

/**
 *
 * @author yasmi
 */
public class ObjCategoría {
    
    String nombre;
    public ArbolB arbolB;
    int contalibros;

    public ObjCategoría(String nombre) {
        this.nombre = nombre;
        this.contalibros = 0;
        this.arbolB = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContalibros() {
        return contalibros;
    }

    public void setContalibros(int contalibros) {
        this.contalibros = contalibros;
    }
    
    
    
    
}
