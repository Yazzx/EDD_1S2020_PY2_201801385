/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;
import proyecto2.Objetos.ObjEstudiante;

/**
 *
 * @author yasmi
 */
public class ListaSimpleEstudiantes {
    
    // estudiantes que estan en una misma categoría de una tabla hash;
    
    
    public class Nodo {
        
        ObjEstudiante estudiante;
        Nodo siguiente;

        public Nodo(ObjEstudiante estudiante, Nodo siguiente) {
            this.estudiante = estudiante;
            this.siguiente = siguiente;
        }

        public Nodo(ObjEstudiante estudiante) {
            this.estudiante = estudiante;
        }

        public ObjEstudiante getEstudiante() {
            return estudiante;
        }

        public void setEstudiante(ObjEstudiante estudiante) {
            this.estudiante = estudiante;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        @Override
        public String toString() {
            return "estudiante=" + estudiante.getCarnet();
        }

        
        
        
    } 
    
    
   public Nodo primero;
   public Nodo auxiliar, actual;
   int tamaño;

    public ListaSimpleEstudiantes() {
        
        primero = null;
        this.tamaño = 0;
    }
    
    public boolean estaVacia(){
        return primero == null;
    }

    public int getTamaño() {
        return tamaño;
    }
   
    public void insertar(ObjEstudiante elemento){
        
        if (this.estaVacia()) {
            
            this.auxiliar = new Nodo(elemento);
            this.primero = auxiliar;
            tamaño++;
            
            return;
            
        }
        
        this.auxiliar = new Nodo(elemento);
        auxiliar.setSiguiente(this.primero);
        
        this.primero = auxiliar;
        
        tamaño++;        
    }
    
    public String generarGraphviz(){
        
        String hola = "";
        
        if (this.estaVacia()) {
             return hola;
        }
        
        hola += primero.estudiante.getCarnet();
        
        actual = primero.siguiente;
                
        while (actual != null) {
            
            hola += "->" + actual.estudiante.getCarnet();
            
            actual = actual.siguiente;
        }
        
        hola+= ";\n";
        
        actual = primero;
        
        while (actual != null) {
            
            hola += actual.estudiante.getCarnet() 
                    + "[shape = \"box\" label = \"" + actual.estudiante.getCarnet() +"\"];\n";
            
            
            actual = actual.siguiente;
        }
        
        return hola;
    }
    
    public long getPrimerCarnet(){
        
        return this.primero.getEstudiante().getCarnet();
        
    }
    
}
