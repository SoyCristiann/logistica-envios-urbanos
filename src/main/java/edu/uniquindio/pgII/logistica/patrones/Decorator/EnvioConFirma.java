package edu.uniquindio.pgII.logistica.patrones.Decorator;

public class EnvioConFirma extends EnvioDecorator {
    public EnvioConFirma(IEnvio envio) {
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

