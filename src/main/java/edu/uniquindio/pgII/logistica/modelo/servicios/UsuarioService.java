package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService implements IUsuarioService {
    List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public Usuario iniciarSesion(String correo, String pass) {

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
            return usuarioSesion; //Si encuentra la contraseña, retorna el usuario. Esto sería un inicio de sesión exitoso.
        } else {
            return null; //Si al final no encuentra la contraseña, retorna null por contraseña no encoentrada.
        }
    }

    @Override
    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    @Override
    public Usuario actualizarPerfil(Usuario usuarioActualizado, String password) {
        if (usuarioActualizado != null) {
            Usuario usuarioEncontrado = buscarUsuario(usuarioActualizado);
            if (usuarioEncontrado != null) {
                usuarioEncontrado.setNombreCompleto(usuarioActualizado.getNombreCompleto());
                usuarioEncontrado.setCorreo(usuarioActualizado.getCorreo());
                usuarioEncontrado.setTelefono(usuarioActualizado.getTelefono());

                usuarioEncontrado.setPassword(password); // Cambio de contrasena
                // el rol del usuario no se puede modificar desde aquí
                return usuarioEncontrado;
            }
        }
        return null;
    }

    @Override
    public void agregarDireccionFrecuente(Usuario usuario, Direccion direccion) {

    }

    public Usuario buscarUsuario(Usuario usuario) {
        if (usuario != null) {
            for (Usuario u : usuarios) {
                if (u.getIdUsuario().equals(usuario.getIdUsuario())) {
                    return u;
                }
            }

        }
        return null;
    }
}