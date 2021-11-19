package com.example.prueba_repo;

public class DatosAmbulancia {

    private String nombre;
    private String Apellido;

    public DatosAmbulancia(String nombre, String apellido) {
        this.nombre = nombre;
        Apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    @Override
    public String toString() {
        return "datosAmbulancia" +
                "Nombre='" + nombre + '\'' +
                ", Apellido='" + Apellido+ '\'' +
                '}';
    }
}
