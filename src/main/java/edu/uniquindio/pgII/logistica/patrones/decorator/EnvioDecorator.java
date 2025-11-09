package edu.uniquindio.pgII.logistica.patrones.decorator;

public abstract class EnvioDecorator implements EnvioComponente {
    protected EnvioComponente envio; // composición

    public EnvioDecorator(EnvioComponente envio) {
        this.envio = envio;
    }

    @Override
    public double getCosto() {
        return envio.getCosto();
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion();
    }
}

