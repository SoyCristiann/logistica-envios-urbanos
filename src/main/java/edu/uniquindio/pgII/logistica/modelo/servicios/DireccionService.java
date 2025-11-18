package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IDireccionService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.DireccionMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.UsuarioMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DireccionService implements IDireccionService {

    private List<Direccion> direcciones; // lista de direcciones

    public DireccionService() {
        this.direcciones = new ArrayList<>(); // crear lista
    }

    @Override
    public boolean registrarDireccion(UsuarioDTO usuarioDTO, DireccionDTO direccionDTO) {

        // convertir dto a entidad
        Usuario usuarioExistente = UsuarioMapper.toEntity(usuarioDTO);
        Direccion direccionNueva = DireccionMapper.toEntity(direccionDTO);

        if (usuarioExistente == null || direccionNueva == null) {
            return false;
        }

        // generar id si falta
        if (direccionNueva.getIdDireccion() == null || direccionNueva.getIdDireccion().isBlank()) {
            direccionNueva.setIdDireccion(UUID.randomUUID().toString());
        }

        // agregar dirección
        usuarioExistente.agregarDireccion(direccionNueva);
        direcciones.add(direccionNueva);

        return true;
    }

    @Override
    public boolean registrarDireccion(Usuario usuarioExistente, Direccion direccionNueva) {

        // agregar dirección directa
        if (usuarioExistente != null && direccionNueva != null) {

            usuarioExistente.agregarDireccion(direccionNueva);
            direcciones.add(direccionNueva);

            return true;
        }
        return false;
    }

    public boolean actualizarDireccion(Direccion direccionActualizada) {

        // actualizar datos de una dirección
        if (direccionActualizada == null) return false;

        Direccion direccionVieja =
                DireccionMapper.toEntity(buscarDireccionPorId(direccionActualizada.getIdDireccion()));

        if (direccionVieja != null) {

            direccionVieja.setCalle(direccionActualizada.getCalle());
            direccionVieja.setNumero(direccionActualizada.getNumero());
            direccionVieja.setBarrio(direccionActualizada.getBarrio());
            direccionVieja.setCiudad(direccionActualizada.getCiudad());
            direccionVieja.setCodigoPostal(direccionActualizada.getCodigoPostal());
            direccionVieja.setDescripcion(direccionActualizada.getDescripcion());

            return true;
        }

        return false;
    }

    public boolean eliminarDireccion(Usuario usuario, Direccion direccionAEliminar) {

        // eliminar una dirección
        if (usuario == null || direccionAEliminar == null) return false;

        Direccion direccionEncontrada =
                DireccionMapper.toEntity(buscarDireccionPorId(direccionAEliminar.getIdDireccion()));

        if (direccionEncontrada != null) {

            direcciones.remove(direccionEncontrada); // eliminar global
            usuario.getDireccionesFrecuentes().remove(direccionEncontrada); // eliminar del usuario

            return true;
        }

        return false;
    }

    @Override
    public DireccionDTO buscarDireccionPorId(String idDireccion) {

        // buscar dirección por id
        for (Direccion direccion : direcciones) {
            if (direccion.getIdDireccion().equals(idDireccion)) {
                return DireccionMapper.toDTO(direccion);
            }
        }
        return null;
    }

    public ArrayList<Direccion> listarDirecciones() {
        // devolver todas las direcciones
        return new ArrayList<>(direcciones);
    }
}
