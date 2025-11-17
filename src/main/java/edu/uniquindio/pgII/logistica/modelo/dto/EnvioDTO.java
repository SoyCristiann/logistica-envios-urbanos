
package edu.uniquindio.pgII.logistica.modelo.dto;


import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;

import java.util.List;

public class EnvioDTO {
    private String idUsuario;
    private DireccionDTO origen;
    private DireccionDTO destino;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private EstadoEnvio estado;

    private List<String> serviciosAdicionales;

    public EnvioDTO() {}

    public EnvioDTO(String idUsuario, DireccionDTO origen, DireccionDTO destino, double peso, double largo, double ancho, double alto, List<String> serviciosAdicionales) {
        this.idUsuario = idUsuario;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.serviciosAdicionales= serviciosAdicionales;

    }

    // Getters y Setters

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<String> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<String> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }
    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

}
