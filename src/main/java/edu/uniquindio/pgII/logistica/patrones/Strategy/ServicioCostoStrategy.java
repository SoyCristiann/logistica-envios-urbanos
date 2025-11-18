package edu.uniquindio.pgII.logistica.patrones.Strategy;

public interface ServicioCostoStrategy {
    double calcularCosto(double peso, double alto, double ancho, double largo);
}
