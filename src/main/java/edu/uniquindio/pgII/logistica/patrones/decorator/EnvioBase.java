package edu.uniquindio.pgII.logistica.patrones.decorator;

public class EnvioBase implements EnvioComponente {
    private double costoBase;
    private String descripcion;

    public EnvioBase(double costoBase, String descripcion) {
        this.costoBase = costoBase;
        this.descripcion = descripcion;
    }

    @Override
    public double getCosto() {
        return costoBase;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }
}

