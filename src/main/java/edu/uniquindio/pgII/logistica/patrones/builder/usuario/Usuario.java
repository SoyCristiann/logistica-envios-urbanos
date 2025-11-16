package edu.uniquindio.pgII.logistica.patrones.builder.usuario;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
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

    protected Usuario(UsuarioBuilder builder){
        this.idUsuario= builder.idUsuario;
        this.nombreCompleto= builder.nombreCompleto;
        this.correo= builder.correoElectronico;
        this.telefono= builder.numerotelefono;
        this.password= builder.password;
        this.rolUsuario= builder.rolUsuario;

        if(builder.direccionesFrecuentes!=null){
            this.direccionesFrecuentes = builder.direccionesFrecuentes;
        }else{
            this.direccionesFrecuentes = new ArrayList<>();
        }

        if(builder.historialEnvios!=null){
            this.historialEnvios = builder.historialEnvios;
        }else{
            this.historialEnvios = new ArrayList<>();
        }

        if(builder.metodosPago!=null){
            this.metodosPago=builder.metodosPago;
        }else{
            this.metodosPago=new ArrayList<>();
        }
    }

    //Métodos Get y Set para gestionar el perfil

    //Id
    public String getIdUsuario() {
        return idUsuario;
    }

    //Nombre
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    //Correo
    public String getCorreo() {
        return correo;
    }

    //Teléfono
    public String getTelefono() {
        return telefono;
    }

    //contraseña
    public  String getPassword() {
        return password;
    }

    //Rol
    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    //Direcciones
    public List<Direccion> getDireccionesFrecuentes() {
        return direccionesFrecuentes;
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

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public void setDireccionesFrecuentes(List<Direccion> direccionesFrecuentes) {
        this.direccionesFrecuentes = direccionesFrecuentes;
    }

    public void setHistorialEnvios(List<Envio> historialEnvios) {
        this.historialEnvios = historialEnvios;
    }

    public void setMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }
}
