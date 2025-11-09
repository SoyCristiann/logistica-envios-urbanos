package edu.uniquindio.pgII.logistica.patrones.decorator;

public class EnvioConFirma extends EnvioDecorator {
    public EnvioConFirma(EnvioComponente envio) {
        super(envio);
    }

    @Override
    public double getCosto() {
        return envio.getCosto() + 2000;
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion() + ", firma requerida";
    }
}

