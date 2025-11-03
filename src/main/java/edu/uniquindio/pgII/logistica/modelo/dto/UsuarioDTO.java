package edu.uniquindio.pgII.logistica.modelo.dto;

import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;

import java.util.List;

public class UsuarioDTO {

    private String idUsuario;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String password;
    private RolUsuario rol;
    private List<DireccionDTO> direccionesFrecuentes;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String idUsuario, String nombreCompleto, String correo,
                      String telefono, String password, RolUsuario rol, List<DireccionDTO> direccionesFrecuentes) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.rol = RolUsuario.USUARIO;
        this.direccionesFrecuentes = direccionesFrecuentes;
    }

    // Getters y Setters
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }

    public List<DireccionDTO> getDireccionesFrecuentes() { return direccionesFrecuentes; }
    public void setDireccionesFrecuentes(List<DireccionDTO> direccionesFrecuentes) {
        this.direccionesFrecuentes = direccionesFrecuentes;
    }
}
