package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios;

    public UsuarioService() {
        usuarios = new ArrayList<Usuario>();
    }


    // LOGICA


    public boolean gestionarPerfil (Usuario usuarioActualizado, String password) {
        if (usuarioActualizado!=null){
            Usuario usuarioEncontrado = buscarUsuario(usuarioActualizado);
            if (usuarioEncontrado!=null){
                usuarioEncontrado.setNombreCompleto(usuarioActualizado.getNombreCompleto());
                usuarioEncontrado.setCorreo(usuarioActualizado.getCorreo());
                usuarioEncontrado.setTelefono(usuarioActualizado.getTelefono());

                usuarioEncontrado.setPassword(password); // Cambio de contrasena
                // el rol del usuario no se puede modificar desde aquí
                return true;

            }

            return true;
        }

        return false;
    }





    public Usuario buscarUsuario (Usuario usuario){
        if (usuario!=null){
            for (Usuario u : usuarios){
                if (u.getIdUsuario().equals(usuario.getIdUsuario())){
                    return u;
                }
            }

        }
        return null;
    }


}
