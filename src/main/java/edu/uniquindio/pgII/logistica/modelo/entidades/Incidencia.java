package edu.uniquindio.pgII.logistica.modelo.entidades;

import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoIncidencia;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.TipoIncidencia;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;

import java.time.LocalDateTime;

public class Incidencia {
    private int idIncidencia;
    private Envio envio;
    private LocalDateTime fecha;
    private TipoIncidencia tipo;
    private String descripcion;
    private EstadoIncidencia estado;

    // Constructores
    public Incidencia() {
    }

    public Incidencia(int idIncidencia, Envio envio, LocalDateTime fecha, TipoIncidencia tipo, String descripcion, EstadoIncidencia estado) {
        this.idIncidencia = idIncidencia;
        this.envio = envio;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidencia tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }
}
