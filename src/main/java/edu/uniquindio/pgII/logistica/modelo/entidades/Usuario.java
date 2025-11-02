package edu.uniquindio.pgII.logistica.modelo.entidades;

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
    private List<Pago> metodosPago;

    //Constructor completo
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
    //contraseña: Se omite el getPassword por temas de seguridad de la aplicación
    public void setPassword(String password) {
        this.password = password;
    }
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
    //Métodos de pago
    public List<Pago> getMetodosPago() {
        return metodosPago;
    }
    //Historial de envíos
    public List<Envio> getHistorialEnvios() {
        return historialEnvios;
    }
}
