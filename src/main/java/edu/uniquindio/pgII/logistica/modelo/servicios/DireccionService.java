package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IDireccionService;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DireccionService implements IDireccionService {
    private List<Direccion> direcciones;

    public DireccionService() {
        this.direcciones = new ArrayList<>();
    }

    public boolean registrarDireccion(Usuario usuarioExistente, Direccion direccionNueva) {
        if (usuarioExistente == null || direccionNueva == null) {
            return false;
        }


        if (direccionNueva.getIdDireccion() == null || direccionNueva.getIdDireccion().isBlank()) {
            direccionNueva.setIdDireccion(UUID.randomUUID().toString()); // Si no tiene ID, se genera uno automáticamente
        }

        usuarioExistente.agregarDireccion(direccionNueva); // se agrega al usuario
        direcciones.add(direccionNueva);                   // y también a la lista general
        return true;
    }



    public boolean actualizarDireccion(Direccion direccionActualizada) {
        if (direccionActualizada == null) return false;

        Direccion direccionVieja = buscarDireccionPorId(direccionActualizada.getIdDireccion());
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
        if (usuario == null || direccionAEliminar == null) return false;

        Direccion direccionEncontrada = buscarDireccionPorId(direccionAEliminar.getIdDireccion());
        if (direccionEncontrada != null) {
            direcciones.remove(direccionEncontrada); // se elimina del listado general
            usuario.getDireccionesFrecuentes().remove(direccionEncontrada); // y del usuario
            return true;
        }

        return false;
    }


    public Direccion buscarDireccionPorId(String idDireccion) {
        for (Direccion direccion : direcciones) {
            if (direccion.getIdDireccion().equals(idDireccion)) {
                return direccion;
            }
        }
        return null;
    }


    public ArrayList<Direccion> listarDirecciones() {
        return new ArrayList<>(direcciones);
    }

}
