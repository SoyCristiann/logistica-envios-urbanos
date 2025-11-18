package edu.uniquindio.pgII.logistica.patrones.Strategy;

public class SeguroStrategy implements ServicioCostoStrategy{

    @Override
    public double calcularCosto(double peso, double alto, double ancho, double largo) {
        return 10000;
    }
}
