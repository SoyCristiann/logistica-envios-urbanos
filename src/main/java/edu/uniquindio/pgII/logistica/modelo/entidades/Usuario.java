package edu.uniquindio.pgII.logistica.modelo.entidades;

import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    // Atributos de la entidad, todos son obligatorios.

    private String idUsuario; //Será el documento, restringido en la interfaz a solo números
    private String nombreCompleto; // Solo un campo para nombre incluyendo apellido.
    private String correo;
    private String telefono;
    private String password; //Contraseña para inicio de sesión.
    private RolUsuario rolUsuario;

    //Listas de relaciones con otros clases
    private List<Direccion> direccionesFrecuentes;
    private List<Envio> historialEnvios;
    private List<MetodoPago> metodosPago;

    //Constructor completo
    public Usuario(String idUsuario, String nombreCompleto, String correo, String telefono, String password, RolUsuario rolUsuario) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.rolUsuario = rolUsuario;
        this.direccionesFrecuentes = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
        this.historialEnvios = new ArrayList<>();
    }



    public Usuario() {
        this.direccionesFrecuentes = new ArrayList<>();
        this.historialEnvios = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
    }

    //Métodos Get y Set para gestionar el perfil

    //Id
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    //Nombre
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    //Correo
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //Teléfono
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //contraseña
    public  String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Rol
    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }
    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    //Direcciones
    public List<Direccion> getDireccionesFrecuentes() {
        return direccionesFrecuentes;
    }
    public void setDireccionesFrecuentes(List<Direccion> direccionesFrecuentes) {
        this.direccionesFrecuentes = direccionesFrecuentes;
    }
    public void agregarDireccion(Direccion direccion) {
        direccionesFrecuentes.add(direccion);
    }
    public void eliminarDireccion(Direccion direccion) {
        direccionesFrecuentes.remove(direccion);
    }

    //Métodos de pago
    public List<MetodoPago> getMetodosPago() {
        return metodosPago;
    }
    public  void setMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }
    public void agregarMetodoPago(MetodoPago metodoPago) {
        metodosPago.add(metodoPago);
    }
    public void eliminarMetodoPago(MetodoPago metodoPago) {
        metodosPago.remove(metodoPago);
    }

    //Historial de envíos
    public List<Envio> getHistorialEnvios() {
        return historialEnvios;
    }
    public void setHistorialEnvios(List<Envio> historialEnvios) {
        this.historialEnvios = historialEnvios;
    }
}
