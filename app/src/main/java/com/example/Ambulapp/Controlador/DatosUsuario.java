package com.example.Ambulapp.Controlador;

public class DatosUsuario {

    private String name,l_name, email, password, eps, born_date, city, direccion, fhone,placas,roll;


    public DatosUsuario() {
    }

    public DatosUsuario(String name, String l_name, String email, String password, String fhone,
                        String born_date, String eps, String direccion, String city){

        this.name = name;
        this.l_name = l_name;
        this.email = email;
        this.password = password;
        this.eps = eps;
        this.born_date = born_date;
        this.city = city;
        this.direccion = direccion;
        this.fhone = fhone;


    }

    public DatosUsuario(String name, String l_name, String email, String password, String city,
                        String direccion, String fhone,String placas) {

        this.name = name;
        this.l_name = l_name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.direccion = direccion;
        this.fhone = fhone;
        this.placas = placas;
        //this.roll=roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getBorn_date() {
        return born_date;
    }

    public void setBorn_date(String born_date) {
        this.born_date = born_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFhone() {
        return fhone;
    }

    public void setFhone(String fhone) {
        this.fhone = fhone;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    @Override
    public String toString() {
        return "datosAmbulancia" +

                "Nombres ='" + name + '\'' +
                ", Apellidos ='" + l_name+ '\'' +
                ", Email='" + email+ '\'' +
                ", Contaseña ='" + password+ '\'' +
                ", Telefono ='" + fhone+ '\'' +
                ", EPS='" + eps+ '\'' +
                ", Fecha de nacimiento ='" + born_date+ '\'' +
                ", Ciudad ='" + city+ '\'' +
                ", Dirección ='" + direccion+ '\'' +

                ", Placas ='" + placas+ '\'' +
                ", Roll ='" + roll+ '\'' +

                '}';
    }
}


