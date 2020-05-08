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
public class ArbolB {

  private int[] keys;
  private ArbolB[] subs;
  private int order;

  public ArbolB (int n) {
    keys = new int[n-1];
    subs = new ArbolB[n];
    order = n;
  }

  private boolean leaf () {
   for (int i=0;i<order;i++)
      if (subs[i] != null)
	{return false;};
   return true;
  }

  private boolean full () {
   for (int i=0;i<order;i++)
      if (subs[i] == null)
	{return false;};
   return true;
  }  
   
    
}
