/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Estructuras;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import proyecto2.Objetos.ObjEstudiante;

/**
 *
 * @author yasmi
 */
public class ListaSimpleEstudiantes {
    
    // estudiantes que estan en una misma categoría de una tabla hash;
    
    
    public class Nodo {
        
        ObjEstudiante estudiante;
        Nodo siguiente;

        public Nodo(ObjEstudiante estudiante, Nodo siguiente) {
            this.estudiante = estudiante;
            this.siguiente = siguiente;
        }

        public Nodo(ObjEstudiante estudiante) {
            this.estudiante = estudiante;
        }

        public ObjEstudiante getEstudiante() {
            return estudiante;
        }

        public void setEstudiante(ObjEstudiante estudiante) {
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
            return "estudiante=" + estudiante.getCarnet();
        }

        
        
        
    } 
    
   public String llavesecreta = "EstaClaseSiSale";
   public Nodo primero;
   public Nodo auxiliar, actual;
   int tamaño;

    public ListaSimpleEstudiantes() {
        
        primero = null;
        this.tamaño = 0;
    }
    
    public boolean estaVacia(){
        return primero == null;
    }

    public int getTamaño() {
        return tamaño;
    }
    
    public String encode(String llavesecreta, String cadena){
        
        String holi = "";
        
        try {
            
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llaveConstraseña = md5.digest(llavesecreta.getBytes("utf-8"));
            byte[] llavebytes = Arrays.copyOf(llaveConstraseña, 24);
            SecretKey key = new SecretKeySpec(llavebytes, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            
            byte [] plainTextBytes = cadena.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte [] base64 = Base64.encodeBase64(buf);
            holi = new String(base64);    
                
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        
        return holi;
        
    }
    
    public String decode(String llavesecreta, String cadena_encriptada){
    
        String adios = "";
    
        try {
            
            byte[] mensaje = Base64.decodeBase64(cadena_encriptada.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestcontraseña = md5.digest(llavesecreta.getBytes("utf-8"));
            byte[] llavebytes = Arrays.copyOf(digestcontraseña, 24);
            SecretKey llave = new SecretKeySpec(llavebytes, "DESede");
            Cipher nocifrado = Cipher.getInstance("DESede");
            nocifrado.init(Cipher.DECRYPT_MODE, llave);
            
            byte[] textoplano = nocifrado.doFinal(mensaje);
            adios = new String(textoplano, "UTF-8");
            
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
    
        return adios;
    }
    
    public boolean buscar(long carnet, String contraseña){
        // esta contraseña es la que no está encriptada
        
        boolean holi = false;
        
        actual = primero;
        while (actual != null) {
            
            if (carnet == actual.getEstudiante().getCarnet()) {
                
                String cadena_encriptada = this.encode(this.llavesecreta, contraseña);
                
                
                if (cadena_encriptada.compareTo(actual.getEstudiante().getContraseña()) == 0) {
                    
                    holi = true;
                    return holi;
                    
                }
                
                
            }
            
            actual = actual.siguiente;
        }
        
        return false;
    }
   
    public void insertar(ObjEstudiante elemento){
        
        if (this.estaVacia()) {
            
            this.auxiliar = new Nodo(elemento);
            this.primero = auxiliar;
            tamaño++;
            
            return;
            
        }
        
        this.auxiliar = new Nodo(elemento);
        auxiliar.setSiguiente(this.primero);
        
        this.primero = auxiliar;
        
        tamaño++;        
    }
    
    public String generarGraphviz(){
        
        String hola = "";
        
        if (this.estaVacia()) {
             return hola;
        }
        
        hola += primero.estudiante.getCarnet();
        
        actual = primero.siguiente;
                
        while (actual != null) {
            
            hola += "->" + actual.estudiante.getCarnet();
            
            actual = actual.siguiente;
        }
        
        hola+= ";\n";
        
        actual = primero;
        
        while (actual != null) {
            
            hola += actual.estudiante.getCarnet() 
                    + "[shape = \"box\" label = \"" + actual.estudiante.getCarnet() +"\n" +
                    actual.estudiante.getNombre() + "\n" +
                    actual.estudiante.getContraseña()+"\"];\n";
            
            
            actual = actual.siguiente;
        }
        
        return hola;
    }
    
    public long getPrimerCarnet(){
        
        return this.primero.getEstudiante().getCarnet();
        
    }
    
}
