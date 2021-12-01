package com.example.prueba_repo.Entidades;

public class tiposAccidentes {

    private String nombre_accidente;
    private  int imagen_accidente;

    public tiposAccidentes() { }

    public tiposAccidentes(String nombre_accidente, int imagen_accidente) {
        this.nombre_accidente = nombre_accidente;
        this.imagen_accidente = imagen_accidente;
    }

    public String getNombre_accidente() {
        return nombre_accidente;
    }

    public void setNombre_accidente(String nombre_accidente) {
        this.nombre_accidente = nombre_accidente;
    }

    public int getImagen_accidente() {
        return imagen_accidente;
    }

    public void setImagen_accidente(int imagen_accidente) {
        this.imagen_accidente = imagen_accidente;
    }

}

