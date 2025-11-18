package edu.uniquindio.pgII.logistica.modelo.entidades;

import edu.uniquindio.pgII.logistica.patrones.Strategy.ServicioCostoStrategy;

public class ServicioAdicional {

    private String idService;
    private String nombreServicio;
    private ServicioCostoStrategy strategy;
    private double costo;

    // CONSTRUCTOR

    public ServicioAdicional(String idService, String nombreServicio, ServicioCostoStrategy strategy, double costo) {

        this.idService = idService;
        this.nombreServicio = nombreServicio;
        this.strategy = strategy;
        this.costo = costo;
    }

    // GETTERS AND SETTERS


    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public ServicioCostoStrategy getStrategy() {
        return strategy;
    }
    public void setStrategy(ServicioCostoStrategy strategy) {
        this.strategy = strategy;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }



}
