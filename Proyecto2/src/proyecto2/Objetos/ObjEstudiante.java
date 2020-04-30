/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

/**
 *
 * @author yasmi
 */
public class ObjEstudiante {
    
    long carnet;
    String nombre, apellido, carrera, contraseña;

    public ObjEstudiante(long carnet, String nombre, String apellido, String carrera, String contraseña) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.contraseña = contraseña;
    }

    public long getCarnet() {
        return carnet;
    }

    public void setCarnet(long carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "ObjEstudiante{" + "carnet=" + carnet + ", nombre=" + nombre + ", apellido=" + apellido + ", carrera=" + carrera + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    
    
}
