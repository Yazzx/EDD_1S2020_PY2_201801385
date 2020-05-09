/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import proyecto2.Objetos.ObjEstudiante;
import proyecto2.Estructuras.TablaHash;
import proyecto2.Estructuras.ArbolAVL;


/**
 *
 * @author yasmi
 */
public class Proyecto2 {

    public static TablaHash tablaHash = new TablaHash();
    public static ArbolAVL arbolAVL = new ArbolAVL();
    
    public static ObjEstudiante estudianteEnUso;
    
    public static Inicial ini = new Inicial();
    
    public static void main(String[] args) {
       ini.setVisible(true);
    }
    
}
