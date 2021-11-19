package com.example.prueba_repo;

public class DatosAmbulancia {

    private String nombres,email,apellidos,eps,contasena,direccion,ciudad;
    private int telefono,fecha_nacimiento;

    public DatosAmbulancia(String nombres, String apellidos, String email, String contasena, String direccion, String ciudad, int telefono, int fecha_nacimiento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.eps = eps;
        this.contasena = contasena;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getContasena() {
        return contasena;
    }

    public void setContasena(String contasena) {
        this.contasena = contasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(int fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "datosAmbulancia" +
                "Nombres ='" + nombres + '\'' +
                ", Apellidos ='" + apellidos+ '\'' +
                ", Email='" + email+ '\'' +
                ", Contaseña ='" + contasena+ '\'' +
                ", Telefono ='" + telefono+ '\'' +
                ", Eps='" + eps+ '\'' +
                ", Fecha de nacimiento ='" + fecha_nacimiento+ '\'' +
                ", Dirección ='" + direccion+ '\'' +
                ", Ciudad ='" + ciudad+ '\'' +
                '}';
    }
}
