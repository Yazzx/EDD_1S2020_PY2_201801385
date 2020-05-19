/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;

import proyecto2.Objetos.ObjCategoría;

/**
 *
 * @author yasmi
 */
public class ListaCategorías {
    
    public class Nodo {

        ObjCategoría estudiante;
        Nodo siguiente, anterior;

        public Nodo getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo anterior) {
            this.anterior = anterior;
        }

        public Nodo(ObjCategoría estudiante, Nodo siguiente) {
            this.estudiante = estudiante;
            this.siguiente = siguiente;
        }

        public Nodo(ObjCategoría estudiante) {
            this.estudiante = estudiante;
        }

        public ObjCategoría getEstudiante() {
            return estudiante;
        }

        public void setEstudiante(ObjCategoría estudiante) {
            this.estudiante = estudiante;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }
    
    public String llavesecreta = "EstaClaseSiSale";
    public Nodo primero;
    public Nodo auxiliar, actual;
    int tamaño;
    ObjCategoría estudianteEncontrado, estudianteEncontradoCate;

    public ListaCategorías() {

        primero = null;
        this.tamaño = 0;
    }

    public ObjCategoría getEstudianteEncontrado() {
        return estudianteEncontrado;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int getTamaño() {
        return tamaño;
    }
    
    public void vaciar(){
        this.primero = null;
    }
    
      public boolean buscar(String nombre) {
        // esta contraseña es la que no está encriptada

        boolean holi = false;

        actual = primero;
        while (actual != null) {

            if (nombre.compareTo(actual.estudiante.getNombre()) == 0) {
                holi = true;

                this.estudianteEncontrado = actual.getEstudiante();

                return holi;

            }

            actual = actual.siguiente;
        }

        return false;
    }

    public void insertar(ObjCategoría elemento) {

        if (this.estaVacia()) {

            this.auxiliar = new Nodo(elemento);
            auxiliar.anterior = null;
            auxiliar.siguiente = null;
            this.primero = auxiliar;
            tamaño++;

            return;

        }

        this.auxiliar = new Nodo(elemento);
        auxiliar.setSiguiente(this.primero);
        primero.setAnterior(auxiliar);

        this.primero = auxiliar;

        tamaño++;
    }
    
}
