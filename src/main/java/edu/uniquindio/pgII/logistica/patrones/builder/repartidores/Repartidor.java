package edu.uniquindio.pgII.logistica.patrones.builder.repartidores;

import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;

import java.util.ArrayList;
import java.util.List;

public class Repartidor {
    private String idRepartidor;
    private String nombre;
    private String documento;
    private String telefono;

    private DisponibilidadRepartidor estadoDisponibilidad;
    private String zonaCobertura;
    private List<Envio> enviosAsignados;

    public Repartidor(RepartidorBuilder builder) {
        this.idRepartidor = builder.documento;
        this.nombre= builder.nombre;
        this.documento= builder.documento;
        this.telefono= builder.telefono;
        this.zonaCobertura= builder.zonaCobertura;
        this.estadoDisponibilidad= builder.estadoDisponibilidad;

        if(builder.enviosAsignados!=null){
            this.enviosAsignados = builder.enviosAsignados;
        }else{
            this.enviosAsignados = new ArrayList<>();
        }
    }

    //Métodos Get y Set para CRUD de repartidor

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento(){
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

    public List<Envio> getEnviosAsignados() {
        return enviosAsignados;
    }

    public void setEnviosAsignados(List<Envio> enviosAsignados) {
        this.enviosAsignados = enviosAsignados;
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
