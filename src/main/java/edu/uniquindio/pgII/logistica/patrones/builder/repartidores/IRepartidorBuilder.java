package edu.uniquindio.pgII.logistica.patrones.builder.repartidores;

import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;

import java.util.List;

public interface IRepartidorBuilder {
    public abstract IRepartidorBuilder withEstadoDisponibilidad(DisponibilidadRepartidor estadoDisponibilidad);
    public abstract IRepartidorBuilder withZonaCobertura(String zonaCobertura);
    public abstract IRepartidorBuilder withEnviosAsignados(List<Envio> enviosAsignados);
}
