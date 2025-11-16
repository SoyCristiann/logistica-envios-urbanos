package edu.uniquindio.pgII.logistica.modelo.dto;

import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;

import java.util.ArrayList;
import java.util.List;

public class RepartidorDTO {
    private String idRepartidor;
    private String nombre;
    private String documento;
    private String telefono;

    private DisponibilidadRepartidor estadoDisponibilidad;
    private String zonaCobertura;
    private List<EnvioDTO> enviosAsignados;

    public RepartidorDTO(){
        this.enviosAsignados=new ArrayList<>();
    }

    public RepartidorDTO(String nombre, String documento, String telefono, String zonaCobertura, DisponibilidadRepartidor estadoDisponibilidad) {
        this.idRepartidor = documento;
        this.nombre= nombre;
        this.documento= documento;
        this.telefono= telefono;
        this.zonaCobertura= zonaCobertura;
        this.estadoDisponibilidad= estadoDisponibilidad;
        this.enviosAsignados = new ArrayList<>();
    }

    //Métodos Get y Set para CRUD de repartidor

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
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

    public DisponibilidadRepartidor getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(DisponibilidadRepartidor estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public List<EnvioDTO> getEnviosAsignadosDTO() {
        return enviosAsignados;
    }

    public void setEnviosAsignadosDTO(List<EnvioDTO> enviosAsignadosDTO) {
        this.enviosAsignados = enviosAsignadosDTO;
    }

    @Override
    public String toString() {
        return "Id:" + idRepartidor + "\n" +
                "Nombre:" + nombre + "\n" +
                "Documento:" + documento + "\n" +
                "Telefono:" + telefono + "\n" +
                "Disponibilidad:" + estadoDisponibilidad + "\n" +
                "Zona Cobertura:" + zonaCobertura + "\n";
    }
}

