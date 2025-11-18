package edu.uniquindio.pgII.logistica.modelo.dto;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;

import java.time.LocalDate;
import java.util.List;

public class EnvioUsuarioDTO {
    private String idEnvio;
    private DireccionDTO origen;
    private DireccionDTO destino;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private List<ServicioAdicional> serviciosAdicionales;
    private double costo;
    private UsuarioDTO usuario;

    public EnvioUsuarioDTO() {}

    public EnvioUsuarioDTO(String idEnvio, DireccionDTO origen, DireccionDTO destino, double peso, double largo, double ancho, double alto, LocalDate fechaCreacion, List<ServicioAdicional> serviciosAdicionales, double costo, UsuarioDTO usuario) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.fechaCreacion = fechaCreacion;
        this.serviciosAdicionales= serviciosAdicionales;
        this.costo = costo;
        this.usuario = usuario;
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

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
