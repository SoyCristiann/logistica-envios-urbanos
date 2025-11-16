package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;

import java.util.List;

public interface IServicioAdicionalService {
    public abstract boolean registrarServicioAdicional(ServicioAdicional servicioAdicionalNuevo);
    public abstract boolean actualizarServicio(ServicioAdicional servicioAdicionalActualizado);
    public abstract boolean eliminarServicio(ServicioAdicional servicioAdicional);
    public abstract ServicioAdicional buscarServicioPorId(String idServicio);
    public abstract List<ServicioAdicional> listarServicios();
    public abstract ServicioAdicional buscarPorNombre(String nombre);
}
