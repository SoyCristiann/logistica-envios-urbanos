package edu.uniquindio.pgII.logistica.patrones.builder.envios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

public class EnvioBuilder implements IEnvioBuilder{

    protected Direccion origen;
    protected Direccion destino;
    protected double peso;
    protected double largo;
    protected double ancho;
    protected double alto;
    protected Usuario usuario;

    protected String idEnvio;
    protected double costo;
    protected EstadoEnvio estado;
    protected LocalDate fechaCreacion;
    protected LocalDate fechaEstimada;
    protected Repartidor repartidor;
    protected List<ServicioAdicional> serviciosAdicionales;


    public EnvioBuilder(Direccion origen, Direccion destino, double peso, double largo, double ancho, double alto, Usuario usuario){
        this.origen=origen;
        this.destino=destino;
        this.peso=peso;
        this.largo=largo;
        this.ancho=ancho;
        this.alto=alto;
        this.usuario=usuario;
    }

    @Override
    public IEnvioBuilder withIdEnvio(String idEnvio) {
        this.idEnvio=idEnvio;
        return this;
    }

    @Override
    public IEnvioBuilder withCosto(double costo) {
        this.costo=costo;
        return this;
    }

    @Override
    public IEnvioBuilder withEstado(EstadoEnvio estado) {
        this.estado=estado;
        return this;
    }

    @Override
    public IEnvioBuilder withFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion=fechaCreacion;
        return this;
    }

    @Override
    public IEnvioBuilder withFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada=fechaEstimada;
        return this;
    }

    @Override
    public IEnvioBuilder withRepartidor(Repartidor repartidor) {
        this.repartidor=repartidor;
        return this;
    }

    @Override
    public IEnvioBuilder withServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales=serviciosAdicionales;
        return this;
    }

    @Override
    public Envio build(){
        return new Envio(this);
    }
}
