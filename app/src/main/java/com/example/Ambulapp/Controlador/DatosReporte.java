package com.example.Ambulapp.Controlador;

public class DatosReporte {
    private String telefono, tipo,   comentario;
    private double latitud, longitud;

    public DatosReporte() {    }

    public DatosReporte(String telefono,  double latitud, double longitud, String comentario) {

        this.telefono = telefono;
        //this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.comentario = comentario;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getComentario() {
        return comentario;
    }
}
