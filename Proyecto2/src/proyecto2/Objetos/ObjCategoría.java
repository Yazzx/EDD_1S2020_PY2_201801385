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

    public ObjCategoría(String nombre) {
        this.nombre = nombre;
        this.arbolB = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
