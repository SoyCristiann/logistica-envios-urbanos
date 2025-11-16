package edu.uniquindio.pgII.logistica.patrones.builder.envios;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;

import java.time.LocalDate;
import java.util.List;

public interface IEnvioBuilder {
    public abstract IEnvioBuilder withIdEnvio(String idEnvio);
    public abstract IEnvioBuilder withCosto(double costo);
    public abstract IEnvioBuilder withEstado(EstadoEnvio estado);
    public abstract IEnvioBuilder withFechaCreacion(LocalDate fechaCreacion);
    public abstract IEnvioBuilder withFechaEstimada(LocalDate fechaEstimada);
    public abstract IEnvioBuilder withRepartidor(Repartidor repartidor);
    public abstract IEnvioBuilder withServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales);
    public abstract Envio build();

}
