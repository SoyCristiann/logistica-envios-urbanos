package edu.uniquindio.pgII.logistica.patrones.Strategy;

public class FragilStrategy implements ServicioCostoStrategy{
    @Override
    public double calcularCosto(double peso, double alto, double ancho, double largo) {
        double volumen = alto * ancho * largo;

        return volumen * 0.03;
    }
}
