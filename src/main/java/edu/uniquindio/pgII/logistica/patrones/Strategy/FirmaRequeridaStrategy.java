package edu.uniquindio.pgII.logistica.patrones.Strategy;

public class FirmaRequeridaStrategy implements ServicioCostoStrategy{
    @Override
    public double calcularCosto(double peso, double alto, double ancho, double largo) {
        return 5000;
    }
}
