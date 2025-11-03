package edu.uniquindio.pgII.logistica.modelo.dto;

public class ServicioAdicionalDTO {

    private String idService;
    private String nombre;
    private String descripcion;
    private double costo;

    public ServicioAdicionalDTO() {}

    public ServicioAdicionalDTO(String idService, String nombre, String descripcion, double costo) {
        this.idService = idService;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    // GETTERS Y SETTERS

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
