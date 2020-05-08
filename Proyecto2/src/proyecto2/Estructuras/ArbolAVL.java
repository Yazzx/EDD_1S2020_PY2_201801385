/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import proyecto2.Objetos.ObjCategoría;

/**
 *
 * @author yasmi
 */
public class ArbolAVL {

    public class Nodo {

        public ObjCategoría categoria;
        public int altura;
        public Nodo derecha, izquierda;

        Nodo(ObjCategoría categoria) {
            this.categoria = categoria;
            this.altura = 0;
            derecha = null;
            izquierda = null;
        }

        Nodo() {
            this.categoria = null;
            this.altura = 0;
            derecha = null;
            izquierda = null;
        }

    }

    public boolean yaesta = false;
    public String grafo = "";
    public String wenas = "";
    public int contagraphviz = 0;

    Nodo raiz;
    Nodo nuevo, actual, auxiliar;

    public ArbolAVL() {
        this.raiz = null;
    }

    // vacio
    public boolean estaVacio() {

        if (raiz == null) {
            return true;
        } else {
            return false;
        }

    }

    public void vaciar() {
        this.raiz = null;
    }

    // buscar
    public void iniciarBuscar(String categoria) {

        this.buscar(raiz, categoria);

    }

    public void buscar(Nodo raiz, String categoria) {

        if (raiz == null) {
            yaesta = false;
            System.out.println(categoria + "si esta");
        } else {

            String valor_raiz = raiz.categoria.getNombre();

            if (categoria.compareTo(valor_raiz) < 0) {
                buscar(raiz.izquierda, categoria);
            } else if (categoria.compareTo(valor_raiz) > 0) {
                buscar(raiz.derecha, categoria);
            } else if (categoria.compareTo(valor_raiz) == 0) {
                yaesta = true;
                System.out.println(categoria + "si esta");
            } else {
                yaesta = false;
                System.out.println(categoria + "no esta");
            }

        }

    }

    // insertar
    public void iniciarInsertar(ObjCategoría cate) {

        raiz = insertar(cate, raiz);
    }

    public Nodo insertar(ObjCategoría cate, Nodo raizl) {

        if (raizl == null) {
            // si no existe
            nuevo = new Nodo(cate);
            raizl = nuevo;

        } else if (cate.getNombre().compareTo(raizl.categoria.getNombre()) < 0) {
            // si va antes en el abecedario            
            raizl.izquierda = insertar(cate, raizl.izquierda);

            if (getAltura(raizl.izquierda) - getAltura(raizl.derecha) == 2) {

                if (cate.getNombre().compareTo(raizl.izquierda.categoria.getNombre()) < 0) {
                    raizl = rotarIzquierdo(raizl);
                } else {
                    raizl = rotarDobleIzquierdo(raizl);
                }

            }

        } else if (cate.getNombre().compareTo(raizl.categoria.getNombre()) > 0) {
            // si va después en el abecedario
            raizl.derecha = insertar(cate, raizl.derecha);

            if (getAltura(raizl.derecha) - getAltura(raizl.izquierda) == 2) {

                if (cate.getNombre().compareTo(raizl.derecha.categoria.getNombre()) > 0) {
                    raizl = rotarDerecho(raizl);
                } else {
                    raizl = rotarDobleDerecho(raizl);
                }

            }

        } else {
            // si ya está no hago na'
        }

        raizl.altura = getMaximo(getAltura(raizl.izquierda), getAltura(raizl.derecha)) + 1;

        return raizl;
    }

    // rotaciones
    public Nodo rotarIzquierdo(Nodo dos) {

        Nodo uno = dos.izquierda;
        dos.izquierda = uno.derecha;
        uno.derecha = dos;

        dos.altura = getMaximo(getAltura(dos.izquierda), getAltura(dos.derecha)) + 1;
        uno.altura = getMaximo(getAltura(uno.izquierda), getAltura(dos)) + 1;

        return uno;
    }

    public Nodo rotarDobleIzquierdo(Nodo tres) {

        tres.izquierda = rotarDerecho(tres.izquierda);
        return rotarIzquierdo(tres);
    }

    public Nodo rotarDerecho(Nodo uno) {

        Nodo dos = uno.derecha;
        uno.derecha = dos.izquierda;
        dos.izquierda = uno;

        uno.altura = getMaximo(getAltura(uno.izquierda), getAltura(uno.derecha)) + 1;
        dos.altura = getMaximo(getAltura(dos.derecha), getAltura(uno)) + 1;

        return dos;
    }

    public Nodo rotarDobleDerecho(Nodo uno) {

        uno.derecha = rotarIzquierdo(uno.derecha);
        return rotarDerecho(uno);

    }

    // otros
    public int getAltura(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        // else
        // la altura inicial de un nodo es 0;
        return nodo.altura;

    }

    public int getMaximo(int uno, int dos) {

        if (uno > dos) {
            return uno;
        } else {
            return dos;
        }

    }

    // imprimir
    public String generarGraphviz(Nodo raiz, int contador) {
        if (raiz == null) {
            grafo += "";
        } else {

            if (raiz.izquierda != null) {

                grafo += raiz.categoria.getNombre() + " -> "
                        + raiz.izquierda.categoria.getNombre() + ";\n";
                generarGraphviz(raiz.izquierda, contador + 1);
            }
            if (raiz.derecha != null) {
                grafo += raiz.categoria.getNombre() + " -> "
                        + raiz.derecha.categoria.getNombre() + ";\n";
                generarGraphviz(raiz.derecha, contador + 1);
            }

        }

        return grafo;
    }

    public void iniciargenerarGraphviz() throws IOException {

        wenas = "";
        wenas = this.generarGraphviz(raiz, contagraphviz);

        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "TablaHash.dot");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {
            
            out.append(wenas);
            
        } finally {
            out.close();
        }

    }
}
