/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;
import proyecto2.Estructuras.ArbolB;
import proyecto2.Estructuras.ListaSimpleEstudiantes;
import proyecto2.Estructuras.ListaSimpleInt;
import proyecto2.Proyecto2;

/**
 *
 * @author yasmi
 */
public class ObjCategoría {
    
    String nombre;
    public ArbolB arbolB;
    public int contalibros;
    public ListaSimpleInt listaDueños;
    public long carnetPrimerDueño;
    
    public ObjCategoría(String nombre) {
        this.nombre = nombre;
        this.contalibros = 0;
        
        // TODO acá va el new arbol b o así 
        this.arbolB = new ArbolB();
        this.listaDueños = new ListaSimpleInt();
        
        // el primer dueño 
        this.carnetPrimerDueño = Proyecto2.estudianteEnUso.getCarnet();
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
        
        this.listaDueños.insertar((int) Proyecto2.estudianteEnUso.carnet);     
        
        
        this.arbolB.iniciarInsertar(libro);
        //this.arbolB.insertar(libro);
        contalibros++;
        System.out.println("Inserción exitosa de: " + libro.getTitulo());
    }
    
    public void imprimirB(){
        this.arbolB.iniciarImprimir();
    }
    
    
}
