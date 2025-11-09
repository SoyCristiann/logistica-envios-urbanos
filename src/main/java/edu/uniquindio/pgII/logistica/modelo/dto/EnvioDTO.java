package edu.uniquindio.pgII.logistica.modelo.dto;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import java.time.LocalDate;
import java.util.List; // <-- nuevo import

public class EnvioDTO {

    private String idEnvio;
    private String origen;
    private String destino;
    private double peso;
    private String dimensiones;
    private double costo;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaEstimada;
    private String idUsuario;
    private String idRepartidor;

    // Campos para los servicios adicionales
    private boolean seguro;
    private boolean fragil;
    private boolean firmaRequerida;
    private boolean prioritario;

    // NUEVO: lista de ids de servicios adicionales seleccionados
    private List<String> servicioAdicionalIds;
    private List<ServicioAdicional> serviciosAdicionales;

    public EnvioDTO() {

    }

    public EnvioDTO(String idEnvio, String origen, String destino, double peso, String dimensiones,
                    double costo, EstadoEnvio estado, LocalDate fechaCreacion,
                    LocalDate fechaEstimada, String idUsuario, String idRepartidor,
                   boolean seguro, boolean fragil, boolean firmaRequerida, boolean prioritario)
        {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.costo = costo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimada = fechaEstimada;
        this.idUsuario = idUsuario;
        this.idRepartidor = idRepartidor;

        this.seguro = seguro;
        this.fragil = fragil;
        this.firmaRequerida = firmaRequerida;
        this.prioritario = prioritario;

        this.servicioAdicionalIds = servicioAdicionalIds;
        this.serviciosAdicionales = serviciosAdicionales;
    }

    // Getters y Setters

    public String getIdEnvio() { return idEnvio; }
    public void setIdEnvio(String idEnvio) { this.idEnvio = idEnvio; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getDimensiones() { return dimensiones; }
    public void setDimensiones(String dimensiones) { this.dimensiones = dimensiones; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public EstadoEnvio getEstado() { return estado; }
    public void setEstado(EstadoEnvio estado) { this.estado = estado; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDate getFechaEstimada() { return fechaEstimada; }
    public void setFechaEstimada(LocalDate fechaEstimada) { this.fechaEstimada = fechaEstimada; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdRepartidor() { return idRepartidor; }
    public void setIdRepartidor(String idRepartidor) { this.idRepartidor = idRepartidor; }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public boolean isFirmaRequerida() {
        return firmaRequerida;
    }

    public void setFirmaRequerida(boolean firmaRequerida) {
        this.firmaRequerida = firmaRequerida;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }


    public List<String> getServicioAdicionalIds() {
        return servicioAdicionalIds;
    }

    public void setServicioAdicionalIds(List<String> servicioAdicionalIds) {
        this.servicioAdicionalIds = servicioAdicionalIds;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
}
