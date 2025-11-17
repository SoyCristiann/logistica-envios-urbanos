package edu.uniquindio.pgII.logistica.patrones.builder.envios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Envio {

    private String idEnvio;
    private Direccion origen;
    private Direccion destino;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private double costo;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private Usuario usuario;
    private Repartidor repartidor;

    private List<ServicioAdicional> serviciosAdicionales;



    // CONSTRUCTOR

    public Envio() {}

    public Envio(EnvioBuilder builder) {
        this.idEnvio=builder.idEnvio;
        this.origen = builder.origen;
        this.destino = builder.destino;
        this.peso = builder.peso;
        this.largo = builder.largo;
        this.alto = builder.alto;
        this.ancho = builder.ancho;
        this.costo = builder.costo;
        this.estado = builder.estado;
        this.fechaCreacion = builder.fechaCreacion;
        this.fechaEntrega = builder.fechaEstimada;
        this.usuario = builder.usuario;
        if(builder.serviciosAdicionales!=null){
            this.serviciosAdicionales = builder.serviciosAdicionales;
        }else{
            this.serviciosAdicionales = new ArrayList<>();
        }
    }



    // GETTERS AND SETTERS


    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
}
