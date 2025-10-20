package edu.uniquindio.pgII.logistica.modelo.entidades;

import edu.uniquindio.pgII.logistica.modelo.util.EstadoEnvio;

import java.time.LocalDate;

import static edu.uniquindio.pgII.logistica.modelo.util.EstadoEnvio.PREDETERMINADO;

public class Envio {

    private String idEnvio;
    private String origen;
    private String destino;
    private double peso;
    private String dimensiones;
    private double costo;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaEstimada;
    private Usuario usuario;
    private Repartidor repartidor;


    // CONSTRUCTOR

    public Envio(String idEnvio, String origen, String destino, double peso, double costo, String estado, LocalDate fechaCreacion, LocalDate fechaEstimada, Usuario usuario, Repartidor repartidor) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.costo = costo;
        this.estado = PREDETERMINADO;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimada = fechaEstimada;
        this.usuario = usuario;
        this.repartidor = repartidor;
    }

    // GETTERS AND SETTERS

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDimensiones(){
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
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

    public LocalDate getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
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

}
