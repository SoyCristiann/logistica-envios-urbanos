package edu.uniquindio.pgII.logistica.patrones.Decorator;

public abstract class EnvioDecorator implements IEnvio {
    protected IEnvio envio; // composición

    public EnvioDecorator(IEnvio envio) {
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

