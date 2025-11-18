package edu.uniquindio.pgII.logistica.patrones.builder.envios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Envio {

    private String idEnvio;
    private Direccion origen;
    private Direccion destino;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private double costo;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private Usuario usuario;
    private Repartidor repartidor;

    private List<ServicioAdicional> serviciosAdicionales;



    // CONSTRUCTOR

    public Envio() {}

    public Envio(EnvioBuilder builder) {
        this.idEnvio=builder.idEnvio;
        this.origen = builder.origen;
        this.destino = builder.destino;
        this.peso = builder.peso;
        this.largo = builder.largo;
        this.alto = builder.alto;
        this.ancho = builder.ancho;
        this.costo = builder.costo;
        this.estado = builder.estado;
        this.fechaCreacion = builder.fechaCreacion;
        this.fechaEntrega = builder.fechaEstimada;
        this.usuario = builder.usuario;
        if(builder.serviciosAdicionales!=null){
            this.serviciosAdicionales = builder.serviciosAdicionales;
        }else{
            this.serviciosAdicionales = new ArrayList<>();
        }
    }



    // GETTERS AND SETTERS


    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
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

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }


    @Override
    public String toString() {
        List<String> serviciosNombres = new ArrayList<>();
        if (this.serviciosAdicionales != null) {
            for (ServicioAdicional servicio : this.serviciosAdicionales) {
                if (servicio != null) {
                    serviciosNombres.add(servicio.getNombreServicio());
                }
            }
        }

        String serviciosString = "[";
        for (int i = 0; i < serviciosNombres.size(); i++) {
            serviciosString += serviciosNombres.get(i);
            if (i < serviciosNombres.size() - 1) {
                serviciosString += ", ";
            }
        }
        serviciosString += "]";


        String usuarioInfo = (this.usuario != null ? this.usuario.getIdUsuario() + " (" + this.usuario.getNombreCompleto() + ")" : "N/A");
        String repartidorInfo = (this.repartidor != null ? this.repartidor.getNombre() + " (ID: " + this.repartidor.getIdRepartidor() + ")" : "Sin asignar");
        String origenInfo = (this.origen != null ? this.origen.getAlias() : "N/A");
        String destinoInfo = (this.destino != null ? this.destino.getAlias() : "N/A");

        return " Envio {" +
                "\n  ID Envío: " + this.idEnvio +
                "\n  Estado: " + this.estado +
                "\n  Costo Total: $" + String.format("%.2f", this.costo) +
                "\n  --- Fechas ---" +
                "\n  Creación: " + this.fechaCreacion +
                "\n  Estimada: " + this.fechaEntrega +
                "\n  --- Usuario y Repartidor ---" +
                "\n  Usuario: " + usuarioInfo +
                "\n  Repartidor: " + repartidorInfo +
                "\n  --- Ubicaciones ---" +
                "\n  Origen: " + origenInfo +
                "\n  Destino: " + destinoInfo +
                "\n  --- Dimensiones ---" +
                "\n  Peso: " + this.peso + " kg" +
                "\n  Dimensiones: " + this.largo + "x" + this.ancho + "x" + this.alto + " cm" +
                "\n  --- Servicios Adicionales ---" +
                "\n  Servicios: " + serviciosString +
                "\n}";
    }


}
