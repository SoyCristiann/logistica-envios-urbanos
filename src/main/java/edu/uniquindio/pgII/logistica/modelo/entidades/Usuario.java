package edu.uniquindio.pgII.logistica.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String numerotelefono;

    //Listas de relaciones con otros clases

    private List<Direccion> direccionesFrecuentes;
    private List<Envio> historialEnvios;
    private List<String> metodosPago;

    //Constructor completo
    public Usuario(String idUsuario, String nombreCompleto, String correoElectronico, String numerotelefono) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numerotelefono = numerotelefono;
        this.direccionesFrecuentes = new ArrayList<>();
        this.historialEnvios = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
    }

    //Métodos Get y Set para gestionar el perfil

    public String getIdUsuario(){
        return idUsuario;
    }

    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public String getCorreoelectronico(){
        return correoElectronico;
    }

    public String getNumerotelefono(){
        return numerotelefono;
    }

    public void setIdUsuario(String idUsuario){
        this.idUsuario= idUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreoelectronico(String correoElectronico){
        this.correoElectronico= correoElectronico;
    }

    public void setNumerotelefono(String numeroTelefono){
        this.numerotelefono= numeroTelefono;
    }

    /**
     * Agrega una nueva dirección a la lista de direcciones frecuentes del usuario.
     * @param direccion La nueva dirección a añadir.
     */

    public void agregarDireccionFrecuente(Direccion direccion){
        this.direccionesFrecuentes.add(direccion);
    }

    /**
     * Agrega un nuevo envío a la lista de envíos del usuario.
     * @param envio El envío a añadir.
     */
    public void agregarEnvio(Envio envio){
        this.historialEnvios.add(envio);
    }

    /**
     * Agrega un nuevo método de pago (simulado) al usuario.
     * @param metodoPago El método de pago (ej: "Visa **** 1234").
     */
    public void agregarMetodoPago(String metodoPago){
        this.metodosPago.add(metodoPago);
    }

}
