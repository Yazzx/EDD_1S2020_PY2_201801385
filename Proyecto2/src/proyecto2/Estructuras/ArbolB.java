/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;

import proyecto2.Objetos.ObjLibro;

/**
 *
 * @author yasmi
 */
public class ArbolB {

  public int orden = 5;
  public int hojas_min = 2, hojas_max = 5;
  public int libros_min = 2, libros_max = 4;


  // el arbol se ordena con ISBN
  class Nodo {

    public ObjLibro[] libro = new ObjLibro[4];
    long rango_minimo; // t
    public Nodo[] link = new Nodo[5];
    public int orden = 5, contador;

    public boolean esHoja() {
      for (int i = 0; i < orden; i++){
        // si tiene algún hijo no es un nodo hoja
        if (link[i] != null) {
          return false;
        }
      }     
      return true;
    }

    public boolean estaLlenoPunteros() {
      for (int i = 0; i < orden; i++){
        // si alguno es null no esta vacio
        if (link[i] == null) {
          return false;
        }
      }
      return true;
    }

    public boolean estaLlenoLibros() {
      for (int i = 0; i < orden-1; i++){
        // si alguno es null no esta vacio
        if (libro[i] == null) {
          return false;
        }
      }
      return true;
    }

    public Nodo(long rango_minimo){

      this.rango_minimo = rango_minimo;
      this.contador = 0;

    }

    public void imprimir(){
      int j = 0;

      for (int i = 0; i < this.contador; i++) {
        
        j = i;
        if (!this.esHoja()) {
          link[i].imprimir();
        }

        System.out.print(libro[i] + " ");

      }

      if (!this.esHoja()) {
        this.link[j].imprimir();
      }
    }
  

  }

  Nodo raiz;
  Nodo actual, nuevo, auxiliar;

  public ArbolB() {

    this.raiz = null;

  }

  public void iniciarImprimir(){
    if (this.raiz != null){
      this.raiz.imprimir();
    } else {
      System.out.println("El arbol está vacio");
    }
    System.out.println("");
  }

  // se busca por isbn y por nombre
  public Nodo buscar(Nodo nodo, long isbn){

    int i = 0;
    while ((i < nodo.contador) && (isbn > libro[i].)) {
      
    }

  }



}