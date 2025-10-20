package edu.uniquindio.pgII.logistica.modelo.entidades;

public class Tarifa {

    private String idTarifa;
    private double costoBase;
    private double costoPorPeso;
    private double costoPorVolumen;
    private double recargoPrioridad;
    private double recargoDistancia;
    private double total;

    // CONSTRUCTOR


    public Tarifa(String idTarifa, double costoBase, double costoPorPeso, double costoPorVolumen, double recargoPrioridad, double recargoDistancia, double total) {
        this.idTarifa = idTarifa;
        this.costoBase = costoBase;
        this.costoPorPeso = costoPorPeso;
        this.costoPorVolumen = costoPorVolumen;
        this.recargoPrioridad = recargoPrioridad;
        this.recargoDistancia = recargoDistancia;
        this.total = total;
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

    public double getRecargoPrioridad() {
        return recargoPrioridad;
    }

    public void setRecargoPrioridad(double recargoPrioridad) {
        this.recargoPrioridad = recargoPrioridad;
    }

    public double getRecargoDistancia() {
        return recargoDistancia;
    }

    public void setRecargoDistancia(double recargoDistancia) {
        this.recargoDistancia = recargoDistancia;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
