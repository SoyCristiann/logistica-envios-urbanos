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
    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public UsuarioDTO iniciarSesion(String correo, String pass) {

        Usuario usuarioSesion = null;
        //Busca dentro del listado de usuarios, si el correo ingresado corresponde a alguno de los usuarios registrados.
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                usuarioSesion = u; //Una vez encuentra el usuario lo asigna a usuarioSesion y finaliza el for con break;
                break;
            }
        }

        if (usuarioSesion == null) {
            return null; // Si no encuentra el correo en la lista de usuarios, retorna null.
        }

        if (usuarioSesion.getPassword().equals(pass)) {
            return UsuarioMapper.toDTO(usuarioSesion); //Si encuentra la contraseña, retorna el usuario. Esto sería un inicio de sesión exitoso.
        } else {
            return null; //Si al final no encuentra la contraseña, retorna null por contraseña no encoentrada.
        }
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO nuevoUsuario) {
        usuarios.add(UsuarioMapper.toEntity(nuevoUsuario));
        for(Usuario usuario : usuarios){
            if(usuario.getIdUsuario().equals(nuevoUsuario.getIdUsuario())){
                return UsuarioMapper.toDTO(usuario);
            }
        }
        return null;
    }

    @Override
    public boolean actualizarPerfil(Usuario usuarioActualizado) {
        if (usuarioActualizado != null) {
            Usuario usuarioEncontrado = buscarUsuario(usuarioActualizado);
            if (usuarioEncontrado != null) {
                usuarioEncontrado.setNombreCompleto(usuarioActualizado.getNombreCompleto());
                usuarioEncontrado.setCorreo(usuarioActualizado.getCorreo());
                usuarioEncontrado.setTelefono(usuarioActualizado.getTelefono());
                // no se puede modificar la contrasena
                // el rol del usuario no se puede modificar desde aquí
                return true;
            }
        }
        return false;
    }

    @Override
    public void agregarDireccionFrecuente(Usuario usuario, Direccion direccion) {

    }

    private Usuario buscarUsuario(Usuario usuario) {
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
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario u : usuarios) {
            usuariosDTO.add(UsuarioMapper.toDTO(u));
        }
        return usuariosDTO;
    }

    @Override
    public boolean eliminarUsuario(UsuarioDTO usuario) {
        if (usuario != null) {
            for (Usuario u : usuarios) {
                if (u.getIdUsuario().equals(usuario.getIdUsuario())) {
                    usuarios.remove(u);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    //Este metodo actualiza un usuario desde la vista del administrador. No puede actualizar la contraseña pero sí modificar el rol.
    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, String idUsuarioAnterior) {
        if (usuarioDTO != null) {
            for (Usuario u : usuarios) {
                if (u.getIdUsuario().equals(idUsuarioAnterior)) {
                    u.setIdUsuario(usuarioDTO.getIdUsuario());
                    u.setNombreCompleto(usuarioDTO.getNombreCompleto());
                    u.setCorreo(usuarioDTO.getCorreo());
                    u.setTelefono(usuarioDTO.getTelefono());
                    u.setRolUsuario(RolUsuario.valueOf(usuarioDTO.getRolUsuario()));
                    return UsuarioMapper.toDTO(u);
                }
            }
            return null;
        }
        return null;
    }


}