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
    public int contalibros;
    public int usuario_creador;
    
    public ObjCategoría(String nombre) {
        this.nombre = nombre;
        this.contalibros = 0;
        
        // TODO acá va el new arbol b o así 
        this.arbolB = new ArbolB();
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
    
    public void insertarenB(ObjLibro libro){
        this.arbolB.iniciarInsertar(libro);
        //this.arbolB.insertar(libro);
        contalibros++;
        System.out.println("Inserción exitosa de: " + libro.getTitulo());
    }
    
    public void imprimirB(){
        this.arbolB.iniciarImprimir();
    }
    
    
}
