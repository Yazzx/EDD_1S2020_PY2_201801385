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
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import proyecto2.Objetos.ObjLibro;

/**
 *
 * @author yasmi
 */
public class ArbolB {

    public String grafo = "", rutab = "";
    public int contanodos = 0, contagrafos;
    public boolean yaesta;
    

    class Nodo {

        public int correlativo;
        public ObjLibro[] libro = new ObjLibro[4];
        public Nodo[] link = new Nodo[5];
        public int contador;
        public boolean hoja_inicial;

        public Nodo(boolean eshoja_inicial) {
            this.contador = 0;
            this.hoja_inicial = eshoja_inicial;
        }

        public Nodo() {
            this.contador = 0;
        }

        public boolean isHoja_inicial() {
            return hoja_inicial;
        }

        public void setHoja_inicial(boolean hoja_inicial) {
            this.hoja_inicial = hoja_inicial;
        }

        public int getContador() {
            return contador;
        }

        public void setContador(int contador) {
            this.contador = contador;
        }

        public int encontrar(long isbn) {
            for (int i = 0; i < this.contador; i++) {
                if (this.libro[i].getIsbn() == isbn) {
                    return i;
                }
            }
            return -1;
        }

        // Otros
        public void imprimir() {

            int j = 0;

            for (int i = 0; i <= this.contador; i++) {

                j = i;
                if (!this.esHoja()) {
                    link[i].imprimir();
                }

                if ((i <= 3) && (libro[i] != null)) {
                    System.out.print(libro[i].getIsbn() + " | ");
                }

            }
            System.out.println("");

            if (!this.esHoja()) {
                this.link[j].imprimir();
            }

        }

        public boolean esHoja() {
            for (int i = 0; i < 5; i++) {
                // si tiene algún hijo no es un nodo hoja
                if (link[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    Nodo raizt;

    public ArbolB() {

        this.raizt = new Nodo(true);
        this.raizt.correlativo = this.contanodos;
        this.contanodos++;
        this.contagrafos = 0;

    }

    // insertar
    public void partir(Nodo uno, Nodo dos, int posicion) {

        // el nodo dos es el lleno
        Nodo tres = new Nodo(dos.isHoja_inicial());
        tres.correlativo = this.contanodos;
        contanodos++;
        // cambio de contador
        tres.setContador(1);

        // copio los ultimos dos al nodo 3
//        for (int i = 0; i < 2; i++) {
//            tres.libro[i] = dos.libro[i + 2];
//            //dos.libro[i+2] = null;
//        }
        for (int i = 0; i < 1; i++) {
            tres.libro[i] = dos.libro[i + 3];
            //dos.libro[i+2] = null;
        }

        for (int i = 0; i < 1; i++) {
            dos.libro[i + 3] = null;
        }

        // si el nodo tiene hijos
        if (!dos.isHoja_inicial()) {

            // copio los ultimos dos links al nodo tres
            for (int i = 0; i < 2; i++) {
                tres.link[i] = dos.link[i + 3];
                //dos.link[i+3] = null;
            }
            for (int i = 0; i < 2; i++) {
                dos.link[i + 3] = null;
            }

        }

        // ver si es dos o tres
        dos.setContador(2);

        // para esto estoy debugueando
        // no pasa nada? 
        for (int i = uno.getContador(); i >= posicion + 1; i--) {
            uno.link[i + 1] = uno.link[i];
        }

        uno.link[posicion + 1] = tres;

        for (int j = uno.getContador() - 1; j >= posicion; j--) {
            uno.libro[j + 1] = uno.libro[j];
        }

        // estoo lo cambio a unooooo
        uno.libro[posicion] = dos.libro[2];

        uno.contador++;
    }

    public void iniciarInsertar(ObjLibro libro) {
        
        
        if (this.buscarIsbn(raizt, libro.getIsbn()) != null) {
            return;
        }
        
        Nodo raiz = this.raizt;
        if (raiz.getContador() == 4) {
            Nodo auxiliar = new Nodo();

            auxiliar.correlativo = this.contanodos;
            contanodos++;

            this.raizt = auxiliar;
            auxiliar.setHoja_inicial(false);
            auxiliar.setContador(0);
            auxiliar.link[0] = raiz;
            this.partir(auxiliar, raiz, 0);

            insertar(auxiliar, libro);
        } else {
            insertar(raiz, libro);
        }
    }

    final public void insertar(Nodo uno, ObjLibro libro) {

        if (uno.isHoja_inicial()) {
            int i = 0;
            for (i = uno.getContador() - 1; i >= 0 && libro.getIsbn() < uno.libro[i].getIsbn(); i--) {
                uno.libro[i + 1] = uno.libro[i];
            }
            uno.libro[i + 1] = libro;
            uno.contador++;
        } else {
            int i = 0;
            for (i = uno.getContador() - 1; i >= 0 && libro.getIsbn() < uno.libro[i].getIsbn(); i--) {
                // no hago nada, solo es para ver en donde queda el i
            }
            ;
            i++;
            Nodo aux = uno.link[i];
            if (aux.getContador() == 4) {

                this.partir(uno, aux, i);
                if (libro.getIsbn() > uno.libro[i].getIsbn()) {
                    i++;
                }
            }
            insertar(uno.link[i], libro);
        }

    }

    // otros insertar
    public void showear(Nodo uno) {
        assert (uno == null);
        for (int i = 0; i < uno.getContador(); i++) {
            System.out.print(uno.libro[i].getIsbn() + " |");
        }
        System.out.println("");
        if (!uno.isHoja_inicial()) {
            for (int i = 0; i < uno.getContador() + 1; i++) {
                showear(uno.link[i]);
            }
        }
    }

    public void iniciarImprimir() {

        this.showear(raizt);

    }

    // eliminar
    public void eliminar(Nodo uno, long isbn) {

        int posicion = uno.encontrar(isbn);
        if (posicion != -1) {

            if (uno.isHoja_inicial()) {
                int i = 0;
                for (i = 0; i < uno.getContador() && uno.libro[i].getIsbn() != isbn; i++) {
                    // solo es para ver hasta donde llega el i
                }
                for (; i < uno.contador; i++) {

                    // manchester 2 * T - 2
                    if (i != 3) {
                        uno.libro[i] = uno.libro[i + 1];
                    }
                }
                uno.contador--;
                return;
            } 
            
            if(!uno.isHoja_inicial()) {

                Nodo auxiliar = uno.link[posicion];
                ObjLibro auxiliar_llave = null;
                
                // manchester T
                if (auxiliar.getContador() >= 3) {
                    while(true) {
                        if (auxiliar.isHoja_inicial()) {
                            //System.out.println(pred.n);
                            auxiliar_llave = auxiliar.libro[auxiliar.getContador() - 1];
                            break;
                        } else {
                            auxiliar = auxiliar.link[auxiliar.getContador()];
                        }
                    }
                    eliminar(auxiliar, auxiliar_llave.getIsbn());
                    uno.libro[posicion] = auxiliar_llave;
                    return;

                }
                
            }

            }

        }
    
        // otros eliminar
    public void encontrar2Isbn() {
    }

    public void encontrar2Titulo() {
    }

    public Nodo buscarIsbn(Nodo uno, long isbn) {
        this.yaesta = false;
        int i = 0;

        if (uno == null) {
            return null;
        }

        for (i = 0; i < uno.getContador(); i++) {
            if (isbn < uno.libro[i].getIsbn()) {
                break;
            }
            if (isbn == uno.libro[i].getIsbn()) {
                this.yaesta = true;
                return uno;
            }
        }
        if (uno.isHoja_inicial()) {
            this.yaesta = false;
            return null;
            
        } else {
            return buscarIsbn(uno.link[i], isbn);
        }

    }

    public Nodo buscarTitulo(Nodo uno, String titulo) {
        int i = 0;

        if (uno == null) {
            return null;
        }

        for (i = 0; i < uno.getContador(); i++) {

            if (titulo.compareTo(uno.libro[i].getTitulo()) < 0) {
                break;
            }
            if (titulo.compareTo(uno.libro[i].getTitulo()) == 0) {
                return uno;
            }
        }
        if (uno.isHoja_inicial()) {
            return null;
        } else {
            return buscarTitulo(uno.link[i], titulo);
        }

    }

    public boolean isbnEsta(long isbn) {

        if (this.buscarIsbn(this.raizt, isbn) != null) {
            return true;
        }
        return false;

    }

    public boolean tituloEsta(String titulo) {

        if (this.buscarTitulo(this.raizt, titulo) != null) {
            return true;
        }
        return false;

    }

    // lbear
    public void iniciarLB() {
        this.pasarlb(raizt);
    }

    public void pasarlb(Nodo uno) {
        assert (uno == null);
        for (int i = 0; i < uno.getContador(); i++) {
            this.listaB.add(uno.libro[i]);
        }
        if (!uno.isHoja_inicial()) {
            for (int i = 0; i < uno.getContador() + 1; i++) {
                pasarlb(uno.link[i]);
            }
        }
    }

    // graphviz
    
    public ArrayList<ObjLibro> listaB = new ArrayList<>();
    
    public String grafiz(Nodo uno) {

        if (uno == null) {
            grafo += "";
            return grafo;
        } else {

            contagrafos++;
            String str = Integer.toString(uno.correlativo);

            grafo += str + " [shape=record    label=\" ";

            String holiwi = "";

            for (int i = 0; i < uno.getContador(); i++) {

                String isbn = Long.toString(uno.libro[i].getIsbn());
                holiwi += isbn + "\\n" + uno.libro[i].getAutor();

                if (i != uno.getContador() - 1) {
                    holiwi += " | ";
                }
            }

            grafo += holiwi + "\"];\n";

            if (!uno.isHoja_inicial()) {

//                int nodos = uno.getContador() + 1;
//                for (int i = contagrafos; i < contagrafos + nodos; i++) {
//
//                    String dooos = Integer.toString(contagrafos);
//
//                    int hola = i + 1;
//                    grafo += dooos + "->" + hola + ";\n";
//
//                }
                for (int i = 0; i < uno.getContador() + 1; i++) {

                    grafo += str + "->" + uno.link[i].correlativo + ";\n";
                }

                for (int i = 0; i < uno.getContador() + 1; i++) {
                    grafiz(uno.link[i]);
                }
            }

        }

        return grafo;

    }

    public void iniciarGenerarGraphviz() throws IOException {

        this.grafo = "";
        String alo = this.grafiz(this.raizt);

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

            // cambiar para que abra el png
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
