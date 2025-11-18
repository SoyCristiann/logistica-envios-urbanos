package edu.uniquindio.pgII.logistica.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Tarifa {

    private String idTarifa;
    private double costoBase;
    private double costoPorPeso;
    private double costoPorVolumen;
    private double total;
    private List<ServicioAdicional> serviciosIncluidos;

    // CONSTRUCTOR

    public Tarifa(String idTarifa, double costoBase, double costoPorPeso, double costoPorVolumen, double total,  List<ServicioAdicional> serviciosIncluidos) {
        this.idTarifa = idTarifa;
        this.costoBase = costoBase;
        this.costoPorPeso = costoPorPeso;
        this.costoPorVolumen = costoPorVolumen;
        this.total = total;
        this.serviciosIncluidos =  serviciosIncluidos;
    }

    // GETTERS AND SETTERS

    public String getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(String idTarifa) {
        this.idTarifa = idTarifa;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    public double getCostoPorPeso() {
        return costoPorPeso;
    }

    public void setCostoPorPeso(double costoPorPeso) {
        this.costoPorPeso = costoPorPeso;
    }

    public double getCostoPorVolumen() {
        return costoPorVolumen;
    }

    public void setCostoPorVolumen(double costoPorVolumen) {
        this.costoPorVolumen = costoPorVolumen;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ServicioAdicional> getServiciosIncluidos() {
        return serviciosIncluidos;
    }

    public void setServiciosIncluidos(List<ServicioAdicional> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }



}
