package edu.uniquindio.pgII.logistica.patrones.Decorator;

public class EnvioFragil extends EnvioDecorator {
    public EnvioFragil(IEnvio envio) {
        super(envio);
    }

    @Override
    public double getCosto() {
        return envio.getCosto() + 3000;
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion() + ", frágil";
    }
}

