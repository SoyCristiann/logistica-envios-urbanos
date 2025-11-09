package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;

import java.util.List;

public interface IUsuarioService {

    // Se crean los métodos (contrato) que deberán desarrollar las clases que implementen esta interfaz

    public abstract Usuario iniciarSesion(String user, String pass);
    public abstract Usuario registrarUsuario(Usuario nuevoUsuario);
    public abstract boolean actualizarPerfil(Usuario usuarioActualizado, String password);
    public abstract void agregarDireccionFrecuente(Usuario usuario, Direccion direccion);
    public abstract List<Usuario> getUsuarios();
    public abstract boolean eliminarUsuario(UsuarioDTO usuario);
    public abstract UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, String  idUsuarioAnterior);
}
