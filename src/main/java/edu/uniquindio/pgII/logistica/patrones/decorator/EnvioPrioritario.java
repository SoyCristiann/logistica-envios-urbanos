package edu.uniquindio.pgII.logistica.patrones.decorator;

public class EnvioPrioritario extends EnvioDecorator {
    public EnvioPrioritario(EnvioComponente envio) {
        super(envio);
    }

    @Override
    public double getCosto() {
        return envio.getCosto() + 7000;
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion() + ", prioritario";
    }
}
