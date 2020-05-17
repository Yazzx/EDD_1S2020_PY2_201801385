/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;

/**
 *
 * @author yasmi
 */
public class ListaSimpleInt {
    
    public class Nodo {

        int estudiante;
        Nodo siguiente, anterior;

        public Nodo getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo anterior) {
            this.anterior = anterior;
        }

        public Nodo(int estudiante, Nodo siguiente) {
            this.estudiante = estudiante;
            this.siguiente = siguiente;
        }

        public Nodo(int estudiante) {
            this.estudiante = estudiante;
        }

        public int getEstudiante() {
            return estudiante;
        }

        public void setEstudiante(int estudiante) {
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
            return "estudiante=" + estudiante;
        }

    }
    
    public Nodo primero;
    public Nodo auxiliar, actual;
    public int carnetEncontrado;
    int tamaño;
    
    public ListaSimpleInt() {

        primero = null;
        this.tamaño = 0;
    }
    
    public boolean estaVacia() {
        return primero == null;
    }

    public int getTamaño() {
        return tamaño;
    }
    
    public boolean buscarCarnet(long carnet) {
        // esta contraseña es la que no está encriptada

        boolean holi = false;

        actual = primero;
        while (actual != null) {

            if (carnet == actual.getEstudiante()) {

                this.carnetEncontrado = actual.getEstudiante();

                holi = true;
                return holi;

            }

            actual = actual.siguiente;
        }

        return false;
    }
    
    public boolean hayOtros(long carnet) {
        // esta contraseña es la que no está encriptada

        boolean holi = false;

        actual = primero;
        while (actual != null) {

            if (carnet != actual.getEstudiante()) {

                holi = true;
                return holi;

            }

            actual = actual.siguiente;
        }

        return false;
    }
    
    public void insertar(int elemento) {

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
