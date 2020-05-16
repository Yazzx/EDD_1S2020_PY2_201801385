/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import proyecto2.Objetos.ObjEstudiante;
import proyecto2.Estructuras.TablaHash;
import proyecto2.Estructuras.ArbolAVL;
import proyecto2.Estructuras.ArbolB;


/**
 *
 * @author yasmi
 */
public class Proyecto2 {

    public static TablaHash tablaHash = new TablaHash();
    public static ArbolAVL arbolAVL = new ArbolAVL();
    
    public static ObjEstudiante estudianteEnUso;
    
    public static DefaultTableModel tablaCategorias;
    
    public static Inicial ini = new Inicial();
    
    public static void main(String[] args) {
       ini.setVisible(true);
    }
    
    
    public static String llavesecreta = "EstaClaseSiSale";
    public static String encode(String llavesecreta, String cadena){
        
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
    
    public static String decode(String llavesecreta, String cadena_encriptada){
    
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
    
}
