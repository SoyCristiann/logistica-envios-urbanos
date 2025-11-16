package edu.uniquindio.pgII.logistica.modelo.dto;


import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;

import java.util.ArrayList;
import java.util.List;

public class EnvioDTO {
    private String idEnvio;
    private DireccionDTO origen;
    private DireccionDTO destino;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private EstadoEnvio estado;

    private List<ServicioAdicional> serviciosAdicionales;

    public EnvioDTO() {}

    public EnvioDTO(String idUsuario, DireccionDTO origen, DireccionDTO destino, double peso, double largo, double ancho, double alto) {
        this.idEnvio = idUsuario;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.serviciosAdicionales= new ArrayList<>(serviciosAdicionales);
        this.estado = estado;

    }

    // Getters y Setters

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idUsuario) {
        this.idEnvio = idUsuario;
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

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }
    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }
}
