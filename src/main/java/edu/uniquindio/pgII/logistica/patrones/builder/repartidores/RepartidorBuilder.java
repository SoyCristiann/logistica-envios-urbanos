package edu.uniquindio.pgII.logistica.patrones.builder.repartidores;

import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;

import java.util.List;

public class RepartidorBuilder implements IRepartidorBuilder {
    protected String idRepartidor;
    protected String nombre;
    protected String documento;
    protected String telefono;

    protected DisponibilidadRepartidor estadoDisponibilidad;
    protected String zonaCobertura;
    protected List<Envio> enviosAsignados;

    public RepartidorBuilder(String nombre, String documento, String telefono) {
        this.idRepartidor= documento;
        this.nombre= nombre;
        this.documento= documento;
        this.telefono= telefono;
    }

    @Override
    public IRepartidorBuilder withEstadoDisponibilidad(DisponibilidadRepartidor estadoDisponibilidad) {
        this.estadoDisponibilidad= estadoDisponibilidad;
        return this;
    }

    @Override
    public IRepartidorBuilder withZonaCobertura(String zonaCobertura) {
        this.zonaCobertura= zonaCobertura;
        return this;
    }

    @Override
    public IRepartidorBuilder withEnviosAsignados(List<Envio> enviosAsignados) {
        this.enviosAsignados = enviosAsignados;
        return this;
    }


    public Repartidor build(){
        return new Repartidor(this);
    }
}
