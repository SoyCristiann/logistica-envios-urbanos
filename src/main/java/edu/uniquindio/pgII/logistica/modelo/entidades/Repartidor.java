package edu.uniquindio.pgII.logistica.modelo.entidades;

import edu.uniquindio.pgII.logistica.modelo.util.EstadoRepartidor;

import java.util.ArrayList;
import java.util.List;

public class Repartidor {
    private String idRepartidor;
    private String nombre;
    private String documento;
    private String telefono;

    private EstadoRepartidor estadoDisponibilidad;
    private String zonaCobertura;

    private List<Envio> enviosAsignados;

    public Repartidor(String idRepartidor, String nombre, String documento, String telefono) {
        this.idRepartidor = idRepartidor;
        this.nombre= nombre;
        this.documento= documento;
        this.telefono= telefono;
        this.estadoDisponibilidad= EstadoRepartidor.DISPONIBLE;
        this.enviosAsignados= new ArrayList<Envio>();
    }

    //Métodos Get y Set para CRUD de repartidor

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getdocumento(){
        return documento;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public EstadoRepartidor getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(EstadoRepartidor estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }
}
