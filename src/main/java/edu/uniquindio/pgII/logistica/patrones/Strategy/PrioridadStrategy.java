package edu.uniquindio.pgII.logistica.patrones.Strategy;

public class PrioridadStrategy implements ServicioCostoStrategy{
    @Override
    public double calcularCosto(double peso, double alto, double ancho, double largo) {
        return 10000;
    }
}
