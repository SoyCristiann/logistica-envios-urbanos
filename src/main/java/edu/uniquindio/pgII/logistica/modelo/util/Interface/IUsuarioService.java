package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.util.List;

public interface IUsuarioService {

    // Se crean los métodos (contrato) que deberán desarrollar las clases que implementen esta interfaz

    public abstract UsuarioDTO iniciarSesion(String user, String pass);
    public abstract UsuarioDTO registrarUsuario(UsuarioDTO nuevoUsuario);
    public abstract boolean actualizarPerfil(Usuario usuarioActualizado);
    public abstract void agregarDireccionFrecuente(Usuario usuario, Direccion direccion);
    public abstract List<UsuarioDTO> getUsuarios();
    public abstract UsuarioDTO buscarUsuarioPorId(String id);
    public abstract boolean eliminarUsuario(UsuarioDTO usuario);
    public abstract UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, String  idUsuarioAnterior);
}
