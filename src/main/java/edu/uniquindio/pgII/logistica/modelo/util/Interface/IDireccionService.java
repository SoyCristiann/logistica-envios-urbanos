package edu.uniquindio.pgII.logistica.modelo.util.Interface;
import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.util.ArrayList;

public interface IDireccionService {

    boolean registrarDireccion(Usuario usuarioExistente, Direccion direccionNueva);
    boolean actualizarDireccion(Direccion direccionActualizada);
    boolean eliminarDireccion(Usuario usuario, Direccion direccionAEliminar);
    DireccionDTO buscarDireccionPorId(String idDireccion);
    ArrayList<Direccion> listarDirecciones();


    public abstract boolean registrarDireccion(UsuarioDTO usuarioExistente, DireccionDTO direccionNueva);


}
