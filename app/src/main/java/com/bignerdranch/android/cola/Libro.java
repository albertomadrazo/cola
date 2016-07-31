package com.bignerdranch.android.cola;

public class Libro {
    String titulo;
    String autor;
    String idioma;

    public Libro(String elTitulo, String elAutor, String elIdioma){
        this.titulo = elTitulo;
        this.autor = elAutor;
        this.idioma = elIdioma;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
