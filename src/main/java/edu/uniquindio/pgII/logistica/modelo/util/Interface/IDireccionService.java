package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

public interface IDireccionService {
    public abstract boolean registrarDireccion(UsuarioDTO usuarioExistente, DireccionDTO direccionNueva);
    public abstract DireccionDTO buscarDireccionPorId(String idDireccion);
}
