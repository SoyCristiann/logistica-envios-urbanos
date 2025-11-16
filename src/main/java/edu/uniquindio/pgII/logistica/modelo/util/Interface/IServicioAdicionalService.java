package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;

import java.util.ArrayList;

public interface IServicioAdicionalService {

    boolean registrarServicioAdicional(ServicioAdicional servicioAdicionalNuevo);
    boolean actualizarServicio(ServicioAdicional servicioAdicionalActualizado);
    boolean eliminarServicio(ServicioAdicional servicioAdicional);
    ServicioAdicional buscarPorId(String idService);
    ArrayList<ServicioAdicional> listarServicios();
    ServicioAdicional buscarPorNombre(String nombre);

}
