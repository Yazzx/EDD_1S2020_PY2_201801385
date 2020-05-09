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
import proyecto2.Objetos.ObjEstudiante;

/**
 *
 * @author yasmi
 */
public class TablaHash {

    ListaSimpleEstudiantes[] tabla = new ListaSimpleEstudiantes[45];
    int tamaño = 45;
    int elementos_almacenados = 0;
    public ObjEstudiante estudianteEncontrado;

    

    public TablaHash() {
        tamaño = 45;
        elementos_almacenados = 0;

        for (int i = 0; i < tabla.length; i++) {

            ListaSimpleEstudiantes hola = new ListaSimpleEstudiantes();
            tabla[i] = hola;
        }

    }
    
    public ObjEstudiante getEstudianteEncontrado() {
        return estudianteEncontrado;
    }

    public int getElementos_almacenados() {
        return elementos_almacenados;
    }

    public void setElementos_almacenados(int elementos_almacenados) {
        this.elementos_almacenados = elementos_almacenados;
    }

    public void insertar(ObjEstudiante estudiante) {

        long carnet = estudiante.getCarnet();

        long lugar = carnet % tamaño;
        int posicion = (int) lugar;

        System.out.println("El estudiante con carnet " + estudiante.getCarnet() + " va al lugar: " + posicion);

        tabla[posicion].insertar(estudiante);

        this.elementos_almacenados++;

    }

    public String generarGraphviz() {

        if (tabla == null) {

            String nula = "digraph G {\n}";

            return nula;

        }

        String hola = "digraph G {\n"
                + "graph [pad=\"0.5\", nodesep=\"0.5\", ranksep=\"2\"];\n"
                + "    node [shape=plain];\n"
                + "    rankdir=LR;";

        // primero van todas las listas con los carnets;
        for (int i = 0; i < tabla.length; i++) {

            hola += tabla[i].generarGraphviz();

        }

        // inicializo la tabla
        hola += "Tabla [label=<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" cellpadding=\"20\">\n"
                + "  <tr><td><i>Hash</i></td></tr>\n";

        for (int i = 0; i < tabla.length; i++) {
            hola += "<tr><td port=\"" + i + "\">" + i + "</td></tr>\n";
        }

        hola += "</table>>];";

        // linkeando listas con lo demás
        for (int i = 0; i < tabla.length; i++) {

            if (tabla[i].estaVacia()) {

            } else {
                hola += "Tabla:" + i + " ->" + tabla[i].getPrimerCarnet() + ";\n";
            }

        }

        hola += "\n}";
        return hola;

    }

    public void iniciargenerarGraphviz() throws IOException {

        String wenas = this.generarGraphviz();

        
        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder, "TablaHash.dot");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {

            out.append(wenas);

        } finally {
            out.close();
            //Desktop.getDesktop().open(textFile);
        }
        
        //acá genero el png
        try {
        String arg1 = textFile.getAbsolutePath(); 
        String arg2 = arg1 + ".png"; 
        String[] c = {"dot", "-Tpng", arg1, "-o", arg2};
        Process p = Runtime.getRuntime().exec(c); 
        
        File imagen = new File(arg2);
        Desktop.getDesktop().open(imagen);
        int err = p.waitFor(); 
        
        } catch (Exception e) {
            
        }

    }

    public boolean buscarUsuario(long carnet, String contraseña) {

        boolean holi = false;

        long lugar = carnet % tamaño;
        int posicion = (int) lugar;

        if (tabla[posicion].estaVacia()) {
            return false;
        }

        holi = tabla[posicion].buscar(carnet, contraseña);
        
        if (holi) {
            this.estudianteEncontrado = tabla[posicion].getEstudianteEncontrado();
        }

        return holi;
    }
    

}
