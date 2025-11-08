package edu.uniquindio.pgII.logistica.patrones.Decorator;

public class EnvioPrioritario extends EnvioDecorator {
    public EnvioPrioritario(IEnvio envio) {
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
