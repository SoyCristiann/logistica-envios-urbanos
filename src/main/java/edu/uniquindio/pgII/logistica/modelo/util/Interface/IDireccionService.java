package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;

import java.util.ArrayList;

public interface IDireccionService {

    boolean registrarDireccion(Usuario usuarioExistente, Direccion direccionNueva);
    boolean actualizarDireccion(Direccion direccionActualizada);
    boolean eliminarDireccion(Usuario usuario, Direccion direccionAEliminar);
    Direccion buscarDireccionPorId(String idDireccion);
    ArrayList<Direccion> listarDirecciones();
}
