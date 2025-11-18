package edu.uniquindio.pgII.logistica.modelo.dto;

import edu.uniquindio.pgII.logistica.patrones.Strategy.ServicioCostoStrategy;

public class ServicioAdicionalDTO {

    private String idService;
    private String nombre;
    private ServicioCostoStrategy strategy;



    public ServicioAdicionalDTO() {}

    public ServicioAdicionalDTO(String idService, String nombre, ServicioCostoStrategy strategy ) {
        this.idService = idService;
        this.nombre = nombre;
        this.strategy = strategy;

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


//
//    public double getCosto() {
//        return costo;
//    }
//
//    public void setCosto(double costo) {
//        this.costo = costo;
//    }

    public ServicioCostoStrategy getStrategy() {
        return strategy;
    }
    public void setStrategy(ServicioCostoStrategy strategy) {
        this.strategy = strategy;
    }
}
