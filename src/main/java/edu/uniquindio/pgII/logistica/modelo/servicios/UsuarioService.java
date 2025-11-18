package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.UsuarioMapper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService implements IUsuarioService {

    private List<Usuario> usuarios; // lista de usuarios

    public UsuarioService() {
        this.usuarios = new ArrayList<>(); // crear lista
    }

    @Override
    public UsuarioDTO iniciarSesion(String correo, String pass) {

        // buscar usuario por correo
        Usuario usuarioSesion = null;
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                usuarioSesion = u;
                break;
            }
        }

        // no existe
        if (usuarioSesion == null) {
            return null;
        }

        // validar contraseña
        if (usuarioSesion.getPassword().equals(pass)) {
            return UsuarioMapper.toDTO(usuarioSesion);
        }

        return null; // contraseña incorrecta
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO nuevoUsuario) {

        // agregar usuario nuevo
        usuarios.add(UsuarioMapper.toEntity(nuevoUsuario));

        // buscar para devolver el DTO recién agregado
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(nuevoUsuario.getIdUsuario())) {
                return UsuarioMapper.toDTO(usuario);
            }
        }
        return null;
    }

    @Override
    public boolean actualizarPerfil(Usuario usuarioActualizado) {

        // buscar usuario existente
        Usuario usuarioEnLista = buscarUsuario(usuarioActualizado);

        if (usuarioEnLista == null) {
            return false; // no existe
        }

        // validar datos obligatorios
        if (usuarioActualizado.getNombreCompleto() == null ||
                usuarioActualizado.getCorreo() == null ||
                usuarioActualizado.getTelefono() == null) {

            return false;
        }

        // actualizar datos
        usuarioEnLista.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        usuarioEnLista.setCorreo(usuarioActualizado.getCorreo());
        usuarioEnLista.setTelefono(usuarioActualizado.getTelefono());
        usuarioEnLista.setDireccionesFrecuentes(usuarioActualizado.getDireccionesFrecuentes());

        return true;
    }

    @Override
    public void agregarDireccionFrecuente(Usuario usuario, Direccion direccion) {
        // agregar dirección al usuario
        usuario.agregarDireccion(direccion);
    }

    private Usuario buscarUsuario(Usuario usuario) {
        // buscar por ID
        if (usuario != null) {
            for (Usuario u : usuarios) {
                if (u.getIdUsuario().equals(usuario.getIdUsuario())) {
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> getUsuarios() {
        // devolver lista de usuarios DTO
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario u : usuarios) {
            usuariosDTO.add(UsuarioMapper.toDTO(u));
        }
        return usuariosDTO;
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(String id) {
        // buscar usuario por id
        for (Usuario u : usuarios) {
            if (u.getIdUsuario().equals(id)) {
                return UsuarioMapper.toDTO(u);
            }
        }
        return null;
    }

    @Override
    public boolean eliminarUsuario(UsuarioDTO usuario) {

        // eliminar usuario
        if (usuario != null) {
            for (Usuario u : usuarios) {
                if (u.getIdUsuario().equals(usuario.getIdUsuario())) {
                    usuarios.remove(u);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, String idUsuarioAnterior) {

        // actualizar datos desde admin
        if (usuarioDTO != null) {
            for (Usuario u : usuarios) {
                if (u.getIdUsuario().equals(idUsuarioAnterior)) {

                    // actualizar campos
                    u.setIdUsuario(usuarioDTO.getIdUsuario());
                    u.setNombreCompleto(usuarioDTO.getNombreCompleto());
                    u.setCorreo(usuarioDTO.getCorreo());
                    u.setTelefono(usuarioDTO.getTelefono());
                    u.setRolUsuario(RolUsuario.valueOf(usuarioDTO.getRolUsuario()));

                    return UsuarioMapper.toDTO(u);
                }
            }
        }
        return null;
    }
}
