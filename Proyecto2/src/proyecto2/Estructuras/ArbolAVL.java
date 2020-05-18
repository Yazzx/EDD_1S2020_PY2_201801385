/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;
import proyecto2.Objetos.ObjCategoría;
import proyecto2.Proyecto2;

/**
 *
 * @author yasmi
 */
public class ArbolAVL {

    public class Nodo {

        public ObjCategoría categoria;
        public int altura;
        public Nodo derecha, izquierda;
        // public int correlativo;

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

    public int contanodos = 0;

    public boolean yaesta = false;
    public String grafo = "", pre = "", en = "", post = "";
    public String wenas = "";
    public int contagraphviz = 0, supercontador = 0;
    public String pathimg;
    public String rutanormal = "", rutapre = "", rutaen = "", rutapost = "";
    public Nodo devolver = null;
    public ObjCategoría devolver_cate = null;
    public boolean yaestaenb = false;

    Nodo raiz;
    Nodo nuevo, actual, auxiliar;

    public ArbolAVL() {
        this.raiz = null;
    }

    public ArrayList listaCates = new ArrayList();

    // tablear
    public void iniciarTablear(DefaultTableModel modeloTabla) {
        this.tablear(modeloTabla, raiz);
    }

    public void tablear(DefaultTableModel modeloTabla, Nodo raiz) {

        if (raiz == null) {
            return;
        } else {

            modeloTabla.addRow(new Object[]{
                raiz.categoria.getNombre(), raiz.categoria.getContalibros()});

            listar(raiz.derecha);
            listar(raiz.izquierda);

        }

    }

    public void tablearFiltroUsuarioActual(DefaultTableModel modeloTabla, Nodo raiz) {

        if (raiz == null) {
            return;
        } else {

            if (raiz.categoria.carnetPrimerDueño == Proyecto2.estudianteEnUso.getCarnet()) {

                modeloTabla.addRow(new Object[]{
                    raiz.categoria.getNombre(), raiz.categoria.getContalibros()});

            }

            listar(raiz.derecha);
            listar(raiz.izquierda);

        }

    }
    
    public void iniciarTablearFiltroUsuarioActual(DefaultTableModel modeloTabla){
        this.tablearFiltroUsuarioActual(modeloTabla, raiz);
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

    public int getTamaño() {
        return this.contanodos;
    }

    // buscar
    public void iniciarBuscar(String categoria) {
        this.yaesta = false;
        this.devolver = null;
        this.devolver_cate = null;

        this.buscar(raiz, categoria);

    }

    public void buscar(Nodo raiz, String categoria) {

        if (raiz == null) {
            yaesta = false;
            this.devolver = null;
            this.devolver_cate = null;
            //System.out.println(categoria + " no esta");
        } else {

            String valor_raiz = raiz.categoria.getNombre();

            if (categoria.compareTo(valor_raiz) < 0) {
                buscar(raiz.izquierda, categoria);
            } else if (categoria.compareTo(valor_raiz) > 0) {
                buscar(raiz.derecha, categoria);
            } else if (categoria.compareTo(valor_raiz) == 0) {
                yaesta = true;
                this.devolver = raiz;
                this.devolver_cate = raiz.categoria;
                System.out.println(categoria + " si esta");
            } else {
                yaesta = false;
                //  System.out.println(categoria + " no esta");
            }

        }

    }

    public void iniciarListar() {
        this.listar(this.raiz);
    }

    public void listar(Nodo raiz) {

        if (raiz == null) {
            return;
        } else {

            this.listaCates.add(raiz.categoria);

            listar(raiz.derecha);
            listar(raiz.izquierda);

        }
    }

    // insertar
    public void iniciarInsertar(ObjCategoría cate) {

        raiz = insertar(cate, raiz);
        System.out.println("terminó de insertar :D");
    }

    public Nodo insertar(ObjCategoría cate, Nodo raizl) {

        if (raizl == null) {
            // si no existe
            nuevo = new Nodo(cate);
            this.contanodos++;
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

    // eliminar
    public void iniciarEliminar(ObjCategoría cate) {
        raiz = eliminar(cate, raiz);
        this.contanodos--;
        System.out.println("terminó de eliminar :D");
    }

    public Nodo eliminar(ObjCategoría cate, Nodo raiz) {

        if (raiz == null) {
            return raiz;
        }

        if (cate.getNombre().compareTo(raiz.categoria.getNombre()) < 0) {
            // si va antes en el abecedario            
            raiz.izquierda = eliminar(cate, raiz.izquierda);

        } else if (cate.getNombre().compareTo(raiz.categoria.getNombre()) > 0) {
            // si va después en el abecedario
            raiz.derecha = eliminar(cate, raiz.derecha);

        } else if (cate.getNombre().compareTo(raiz.categoria.getNombre()) == 0) {

            // si es la categoría que busco
            System.out.println("Si existe el nodo " + cate.getNombre());

            // si tiene solo un hijo o no tiene hijos
            if ((raiz.izquierda == null) || (raiz.derecha == null)) {
                Nodo auxiliar = null;

                if (raiz.izquierda == null) {
                    auxiliar = raiz.derecha;
                } else {
                    auxiliar = raiz.izquierda;
                }

                // Si no hay hijos
                if (auxiliar == null) {
                    //auxiliar = raiz;
                    raiz = null;
                } else {
                    raiz = auxiliar;
                }
            } // si el nodo tiene dos hijos
            else {
                Nodo auxiliar = this.getValorinimo(raiz.derecha);
                raiz.categoria = auxiliar.categoria;

                raiz.derecha = this.eliminar(auxiliar.categoria, raiz.derecha);
            }

        } else {
            // si no existe esta cosita no hago nada;
            return raiz;
        }

        if (raiz == null) {
            return raiz;
        }

        // Balanceando
        raiz.altura = this.getMaximo(this.getAltura(raiz.izquierda), this.getAltura(raiz.derecha)) + 1;
        int balance = this.getAltura(raiz.izquierda) - this.getAltura(raiz.derecha);

        if (balance > 1 && this.getBalance(raiz.izquierda) >= 0) {
            return this.rotarIzquierdo(raiz);
        }

        if (balance > 1 && this.getBalance(raiz.derecha) < 0) {
            raiz.izquierda = this.rotarDerecho(raiz.izquierda);
            return this.rotarIzquierdo(raiz);
        }

        if (balance < -1 && this.getBalance(raiz.derecha) <= 0) {
            return this.rotarDerecho(raiz);
        }

        if (balance < -1 && this.getBalance(raiz.derecha) > 0) {
            raiz.derecha = this.rotarIzquierdo(raiz.derecha);
            return this.rotarDobleDerecho(raiz);
        }

        return raiz;
    }

    // rotaciones
    // singleRightRotation
    public Nodo rotarIzquierdo(Nodo dos) {

        Nodo uno = dos.izquierda;
        dos.izquierda = uno.derecha;
        uno.derecha = dos;

        dos.altura = getMaximo(getAltura(dos.izquierda), getAltura(dos.derecha)) + 1;
        uno.altura = getMaximo(getAltura(uno.izquierda), getAltura(dos)) + 1;

        return uno;
    }

    // doubleLeftRoatation
    public Nodo rotarDobleIzquierdo(Nodo tres) {

        tres.izquierda = rotarDerecho(tres.izquierda);
        return rotarIzquierdo(tres);
    }

    // singleLEftRotation
    public Nodo rotarDerecho(Nodo uno) {

        Nodo dos = uno.derecha;
        uno.derecha = dos.izquierda;
        dos.izquierda = uno;

        uno.altura = getMaximo(getAltura(uno.izquierda), getAltura(uno.derecha)) + 1;
        dos.altura = getMaximo(getAltura(dos.derecha), getAltura(uno)) + 1;

        return dos;
    }

    // doubleRightRotation
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

    public Nodo getValorinimo(Nodo nodo) {
        Nodo actual = nodo;

        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    public int getBalance(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        return this.getAltura(nodo.izquierda) - this.getAltura(nodo.derecha);
    }

    // imprimir
    public void iniciarMostrarArbol() {
        this.mostrarArbol(raiz, supercontador);
        System.out.println("\n");
    }

    public void mostrarArbol(Nodo raiz, int contador) {
        if (raiz == null) {
            return;
        } else {
            mostrarArbol(raiz.derecha, contador + 1);
            for (int i = 0; i < contador; ++i) {
                System.out.print("     ");
            }
            System.out.println(raiz.categoria.getNombre());
            mostrarArbol(raiz.izquierda, contador + 1);
        }

    }

    public String generarGraphviz(Nodo raiz) {
        if (raiz == null) {
            grafo += "";
        } else {

            grafo += raiz.categoria.getNombre() + ";\n";

            if (raiz.izquierda != null) {

                grafo += raiz.categoria.getNombre() + " -> "
                        + raiz.izquierda.categoria.getNombre() + ";\n";
                generarGraphviz(raiz.izquierda);
            }
            if (raiz.derecha != null) {
                grafo += raiz.categoria.getNombre() + " -> "
                        + raiz.derecha.categoria.getNombre() + ";\n";
                generarGraphviz(raiz.derecha);
            }

        }

        return grafo;
    }

    public void iniciargenerarGraphviz() throws IOException {

        this.grafo = "";
        String hola = this.generarGraphviz(raiz);

        String holiwi = "";
        holiwi = "digraph G {\n";
        holiwi += "node [shape=box];\n\n";
        holiwi += hola;
        holiwi += "\n}";

        System.out.println("generando dot");

        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "ArbolAVL.dot");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {

            out.append(holiwi);

        } finally {
            out.close();
        }

        //acá genero el png
        try {
            String arg1 = textFile.getAbsolutePath();
            String arg2 = arg1 + ".png";
            this.pathimg = arg2;
            System.out.println("generando png");
            String[] c = {"dot", "-Tpng", arg1, "-o", arg2};
            Process p = Runtime.getRuntime().exec(c);
            this.rutanormal = arg2;

            TimeUnit.SECONDS.sleep(2);

            this.generarPNG(arg2);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    public String generarPreorder(Nodo raiz) {

        // raiz izq der
        if (raiz == null) {
            pre += "";
        } else {
            pre += raiz.categoria.getNombre() + " -> ";
            if (raiz.izquierda != null) {
                generarPreorder(raiz.izquierda);
            }
            if (raiz.derecha != null) {
                generarPreorder(raiz.derecha);
            }
        }

        return pre;

    }

    public void iniciargenerarPreorder() throws IOException {

        this.pre = "";
        String hola = this.generarPreorder(raiz);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola += ";";

        String holiwi = "";
        holiwi = "digraph G {\n";
        holiwi += "rankdir=\"LR\";\n\nnode [shape=box];\n\n";
        holiwi += hola;
        holiwi += "\n}";

        System.out.println("generando dot");

        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "ArbolPreorder.dot");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {

            out.append(holiwi);

        } finally {
            out.close();
        }

        //acá genero el png
        try {
            String arg1 = textFile.getAbsolutePath();
            String arg2 = arg1 + ".png";
            System.out.println("generando png");
            String[] c = {"dot", "-Tpng", arg1, "-o", arg2};
            Process p = Runtime.getRuntime().exec(c);
            this.rutapre = arg2;

            TimeUnit.SECONDS.sleep(2);

            this.generarPNG(arg2);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    public String generarEnorder(Nodo raiz) {

        //  izq raiz der
        if (raiz == null) {
            en += "";
        } else {

            if (raiz.izquierda != null) {
                generarEnorder(raiz.izquierda);
            }
            en += raiz.categoria.getNombre() + " -> ";

            if (raiz.derecha != null) {
                generarEnorder(raiz.derecha);
            }
        }

        return en;

    }

    public void iniciargenerarEnorder() throws IOException {

        this.en = "";
        String hola = this.generarEnorder(raiz);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola += ";";

        String holiwi = "";
        holiwi = "digraph G {\n";
        holiwi += "rankdir=\"LR\";\n\nnode [shape=box];\n\n";
        holiwi += hola;
        holiwi += "\n}";

        System.out.println("generando dot");

        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "ArbolEnorder.dot");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {

            out.append(holiwi);

        } finally {
            out.close();
        }

        //acá genero el png
        try {
            String arg1 = textFile.getAbsolutePath();
            String arg2 = arg1 + ".png";
            this.pathimg = arg2;
            System.out.println("generando png");
            String[] c = {"dot", "-Tpng", arg1, "-o", arg2};
            Process p = Runtime.getRuntime().exec(c);
            this.rutaen = arg2;

            TimeUnit.SECONDS.sleep(2);

            this.generarPNG(arg2);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    public String generarPostorder(Nodo raiz) {

        if (raiz == null) {
            post += "";
        } else {

            if (raiz.izquierda != null) {
                generarPostorder(raiz.izquierda);
            }

            if (raiz.derecha != null) {
                generarPostorder(raiz.derecha);
            }
            post += raiz.categoria.getNombre() + " -> ";
        }

        return post;

    }

    public void iniciargenerarPostorder() throws IOException {

        this.post = "";
        String hola = this.generarPostorder(raiz);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola = hola.substring(0, hola.length() - 1);
        hola += ";";

        String holiwi = "";
        holiwi = "digraph G {\n";
        holiwi += "rankdir=\"LR\";\n\nnode [shape=box];\n\n";
        holiwi += hola;
        holiwi += "\n}";

        System.out.println("generando dot");

        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "ArbolPostorder.dot");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {

            out.append(holiwi);

        } finally {
            out.close();
        }

        //acá genero el png
        try {
            String arg1 = textFile.getAbsolutePath();
            String arg2 = arg1 + ".png";
            this.pathimg = arg2;
            System.out.println("generando png");
            String[] c = {"dot", "-Tpng", arg1, "-o", arg2};
            Process p = Runtime.getRuntime().exec(c);
            this.rutapost = arg2;

            TimeUnit.SECONDS.sleep(2);

            this.generarPNG(arg2);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    public void generarPNG(String arg2) {

        try {

            System.out.println("abriendo");
            File imagen = new File(arg2);
            Desktop.getDesktop().open(imagen);
            //System.out.println("fin de abrir");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
