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
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import proyecto2.Objetos.ObjLibro;

/**
 *
 * @author yasmi
 */
public class ArbolB {

    public int orden = 5;
    public int hojas_min = 2, hojas_max = 5;
    public int libros_min = 2, libros_max = 4;
    public String grafo = "", rutab = "";
    public int contanodos = 0;
    

    // el arbol se ordena con ISBN
    // mañana comento que pex con todo, ahorita solo rezo pa que funcione xd
    class Nodo {

        public ObjLibro[] libro = new ObjLibro[4];
        public Nodo[] link = new Nodo[5];
        public int orden = 5, contador;
        public boolean eshoja_inicial;

        public boolean esHoja() {
            for (int i = 0; i < orden; i++) {
                // si tiene algún hijo no es un nodo hoja
                if (link[i] != null) {
                    return false;
                }
            }
            return true;
        }

        public boolean estaLlenoPunteros() {
            for (int i = 0; i < orden; i++) {
                // si alguno es null no esta vacio
                if (link[i] == null) {
                    return false;
                }
            }
            return true;
        }

        public boolean estaLlenoLibros() {
            for (int i = 0; i < libro.length; i++) {
                // si alguno es null esta vacio
                if (libro[i] == null) {
                    return false;
                }
            }
            return true;
        }

        public Nodo(boolean eshoja_inicial) {

            this.eshoja_inicial = eshoja_inicial;
            this.contador = 0;

        }

        public void imprimir() {
            
            int j = 0;

            for (int i = 0; i <= this.contador; i++) {

                j = i;
                if (!this.esHoja()) {
                    link[i].imprimir();
                }

                if ((i <= 3) && (libro[i] != null)) {
                    System.out.print(libro[i].getTitulo() + " | ");
                }

            }
            System.out.println("");

            if (!this.esHoja()) {
                this.link[j].imprimir();
            }
            
            
            
        }

        // se busca por isbn y por nombre
        public Nodo buscar(long isbn) {

            int i = 0;
            while ((i < contador) && (isbn > libro[i].getIsbn())) {
                i++;
            }

            try {

                if (libro[i].getIsbn() == isbn) {
                    return this;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ALERTA!!" + e);
            }

            if (this.esHoja()) {
                return null;
            }

            return link[i].buscar(isbn);

        }

        public Nodo buscarNombre(String nombre) {

            int i = 0;

            String nuevo = nombre.toLowerCase();
            String auxiliar = libro[i].getTitulo().toLowerCase();

            while ((i < contador) && (nuevo.compareTo(auxiliar) > 0)) {
                i++;
                auxiliar = libro[i].getTitulo().toLowerCase();
            }

            try {

                if (nuevo.compareTo(auxiliar) == 0) {
                    return this;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ALERTA!!" + e);
            }

            if (this.esHoja()) {
                return null;
            }

            return link[i].buscarNombre(nuevo);

        }

        // metodos para insertar
        public void partir(int i, Nodo nodo) {

            Nodo nuevo = new Nodo(nodo.esHoja());
            nuevo.contador = 2;

            // copio los ultimos dos libros a otro nodo
            for (int j = 0; j < 2; j++) {
                nuevo.libro[j] = nodo.libro[j + 2];
            }
            
            // elimino los ultimos dos del nodo
            for (int j = 0; j < 2; j++) {
                nodo.libro[j + 2] = null;
            }

            // si no es hoja
            if (!nodo.esHoja()) {
                for (int j = 0; j < 3; j++) {
                    nuevo.link[j] = nodo.link[j + 3];
                }
                
                // elimino los links que pasé de la raiz
                for (int j = 0; j < 3; j++) {
                    nodo.link[j+3] = null;
                }
            }

            nodo.contador = 2;

            // muevo los links para hacer espacio al otro
            // si algo da error es ESTO
            for (int j = this.contador; j >= i + 1; j--) {
                this.link[j + 1] = this.link[j];
            }

            this.link[i + 1] = nuevo;

            // esto puede dar error
            // si algo da error es ESTO
            for (int j = this.contador - 1; j >= i; j--) {
                this.libro[j + 1] = this.libro[j];
            }

            // esto puede dar error
            libro[i] = nodo.libro[1];
            
            // elimino el libro uno 
            nodo.libro[1] = null;

            this.contador++;

        }

        public void insertarChido(ObjLibro libro) {

            int i = this.contador - 1;

            if (this.esHoja()) {

                // sorteo lo que ya está
                while ((i >= 0) && (this.libro[i].getIsbn() > libro.getIsbn())) {
                    this.libro[i + 1] = this.libro[i];
                    i--;

                }

                this.libro[i + 1] = libro;

                this.contador++;

            } else {
                // Si el nodo no es hoja tengo que ver en las hojas si 
                // puedo meter el valor chido

                while ((i >= 0) && (this.libro[i].getIsbn() > libro.getIsbn())) {
                    i--;
                }

                // si el hijo está lleno
                if (this.link[i + 1].contador == 4 || this.link[i + 1].estaLlenoLibros()) {

                    this.partir(+1, this.link[i + 1]);
                    if (this.libro[i + 1].getIsbn() < libro.getIsbn()) {
                        i++;
                    }
                }

                this.link[i + 1].insertarChido(libro);

            }

        }
        
        

    }

    Nodo raiz;
    Nodo actual, auxiliar;

    public ArbolB() {

        this.raiz = null;

    }

    public void iniciarImprimir() {
        if (this.raiz != null) {
            this.raiz.imprimir();
        } else {
            System.out.println("El arbol está vacio");
        }
        System.out.println("");
    }

    public void iniciarBuscar(long isbn) {
        if (this.raiz != null) {
            this.raiz.buscar(isbn);
        } else {
            System.out.println("El arbol está vacio");
        }
        System.out.println("");
    }

    public void insertar(ObjLibro libro) {

        // si la raiz esta nula, inserto en el primer cosito del primer nodo
        if (this.raiz == null) {

            this.raiz = new Nodo(true);
            this.raiz.libro[0] = libro;
            this.raiz.contador = 1;

        } else {

            // si el array está lleno
            if (this.raiz.estaLlenoLibros() || this.raiz.contador == 4) {

                Nodo nuevo = new Nodo(false);

                nuevo.link[0] = this.raiz;
                nuevo.partir(0, this.raiz);

                // shamalama
                int i = 0;
                if (nuevo.libro[0].getIsbn() < libro.getIsbn()) {
                    i++;
                }

                nuevo.link[i].insertarChido(libro);
                this.raiz = nuevo;

            } else {
                // si el array aun no esta lleno
                this.raiz.insertarChido(libro);
            }

        }

    }
    
    public String generarGraphviz(Nodo raiz){
        
        
        if (raiz == null) {
            grafo += "";
        } else {
            
            // si no está vacío
            //A [shape=record    label="1 |2 | 3 |4 "];
            
            contanodos++;
            String str = Integer.toString(contanodos);
                        
            grafo += str + " [shape=record    label=\"";
            
            for (int i = 0; i < 4; i++) {
                if (raiz.libro[i] != null) {
                    
                    String isbn = Long.toString(raiz.libro[i].getIsbn());
                    
                    grafo += isbn +"\n" + raiz.libro[i].getTitulo();
                } else {
                    grafo += " . ";
                }
                if (i != 3) {
                    grafo += " | ";
                }
            }
            
            grafo += "\"];\n}";
            
            int auxiliar = contanodos+1;
            // miro si tiene hijos
            for (int i = 0; i < 5; i++) {
                
                if (raiz.link[i] != null) {
                    
                    int aux2 = auxiliar+i;
                    String dooos = Integer.toString(aux2);
                    
                    grafo += str + "->" + dooos + ";\n";
                    generarGraphviz(raiz.link[i]);
                    
                    
                    
                }               
                
            }   
            
            
        }
        
        
        
        return grafo;
    }

    public void iniciarGenerarGraphviz() throws IOException {
        
        this.grafo = "";
        String alo = this.generarGraphviz(this.raiz);
        
        String holiwi = "";
        holiwi = "digraph G {\n";
        holiwi += this.grafo;
        holiwi += "\n}";
        
        System.out.println("generando dot");

        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "ArbolB.dot");
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
            this.rutab = arg2;

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
