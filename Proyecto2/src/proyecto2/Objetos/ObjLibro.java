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
public class ObjLibro{
    
    long usuario_dueño;
    long isbn;
    String titulo, autor, editorial;
    int año;
    String edicion, categoría, idioma;
    public String isbnstring;
    
    public ObjLibro(){
        
    }

    public ObjLibro(long usuario_dueño, long isbn, String titulo, String autor, String editorial, int año, String edicion, String categoría, String idioma) {
        this.usuario_dueño = usuario_dueño;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.año = año;
        this.edicion = edicion;
        this.categoría = categoría;
        this.idioma = idioma;
        this.isbnstring = "" + isbn;
    }


    public long getUsuario_dueño() {
        return usuario_dueño;
    }

    public void setUsuario_dueño(long usuario_dueño) {
        this.usuario_dueño = usuario_dueño;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getCategoría() {
        return categoría;
    }

    public void setCategoría(String categoría) {
        this.categoría = categoría;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    
    
    
    
}
