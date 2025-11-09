package edu.uniquindio.pgII.logistica.patrones.decorator;

public class EnvioConSeguro extends EnvioDecorator {
    public EnvioConSeguro(EnvioComponente envio) {
        super(envio);
    }

    @Override
    public double getCosto() {
        return envio.getCosto() + 5000; // recargo fijo o % del valor
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion() + ", con seguro";
    }
}

