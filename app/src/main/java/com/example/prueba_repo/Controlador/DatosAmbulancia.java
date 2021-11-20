package com.example.prueba_repo.Controlador;

public class DatosAmbulancia {

    private String nombres,apellidos,email,contasena,placas,ciudad,direccion;
    private int telefono;

    public DatosAmbulancia() {
    }

    public DatosAmbulancia(String nombres, String apellidos, String email,  String contasena, String placas, String ciudad,  String direccion, int telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.contasena = contasena;
        this.placas = placas;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContasena() {
        return contasena;
    }

    public void setContasena(String contasena) {
        this.contasena = contasena;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "datosAmbulancia" +
                "Nombres ='" + nombres + '\'' +
                ", Apellidos ='" + apellidos+ '\'' +
                ", Email='" + email+ '\'' +
                ", Contaseña ='" + contasena+ '\'' +
                ", Telefono ='" + telefono+ '\'' +
                ", Placas='" + placas+ '\'' +
                ", Ciudad ='" + ciudad+ '\'' +
                ", Dirección ='" + direccion+ '\'' +
                '}';
    }
}
