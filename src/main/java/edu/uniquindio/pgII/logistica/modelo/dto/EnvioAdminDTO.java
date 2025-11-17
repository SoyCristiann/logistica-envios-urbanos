package edu.uniquindio.pgII.logistica.modelo.dto;

import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;

import java.time.LocalDate;
import java.util.List;

/**
 * Este DTO es creado para poder actualizar un envío desde la vista administrador. Los campos son específicos de dicha vista.
 * */
public class EnvioAdminDTO {
    private String idEnvio;
    private DireccionDTO origen;
    private DireccionDTO destino;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private String nombreUsuario;
    private RepartidorDTO repartidor;
    private List<String> serviciosAdicionales;

    public EnvioAdminDTO() {}
    public EnvioAdminDTO(String idEnvio, DireccionDTO origen, DireccionDTO destino, double peso, double alto, double largo, double ancho, EstadoEnvio estado, LocalDate fechaCreacion, LocalDate fechaEntrega, String nombreUsuario, RepartidorDTO repartidor, List<String> serviciosAdicionales) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.nombreUsuario = nombreUsuario;
        this.repartidor = repartidor;
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public DireccionDTO getOrigen() {
        return origen;
    }

    public void setOrigen(DireccionDTO origen) {
        this.origen = origen;
    }

    public DireccionDTO getDestino() {
        return destino;
    }

    public void setDestino(DireccionDTO destino) {
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public RepartidorDTO getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(RepartidorDTO repartidor) {
        this.repartidor = repartidor;
    }

    public List<String> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<String> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
}
