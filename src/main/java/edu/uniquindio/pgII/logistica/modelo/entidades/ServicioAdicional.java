package edu.uniquindio.pgII.logistica.modelo.entidades;

public class ServicioAdicional {

    public String idService;
    public String nombre;
    public String descripcion;
    public double costo;

    // CONSTRUCTOR

    public ServicioAdicional(String idService, String nombre, String descripcion, double costo) {

        this.idService = idService;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    // GETTERS AND SETTERS


    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
