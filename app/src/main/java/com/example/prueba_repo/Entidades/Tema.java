package com.example.prueba_repo.Entidades;

public class Tema {
    private String nombre;
    private  int imagenId;

    public Tema(){}

    public Tema(String nombre, int imagenId) {
        this.nombre = nombre;
        this.imagenId = imagenId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
