package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioAdminDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import java.time.LocalDate;
import java.util.ArrayList;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import java.util.List;



public interface IEnvioService {
    public abstract boolean crearEnvio(EnvioDTO envioDTO);
    public abstract boolean registrarServicio(ServicioAdicional servicioAdicionalNuevo);

    public abstract boolean crearEnvio(Envio envio);
    public abstract boolean modificarEnvio(Envio envio);
    public abstract boolean cancelarEnvio(Envio envio);

    public abstract boolean asignarRepartidor(Envio envio, Repartidor repartidor);
    public abstract boolean actualizarEstado(Envio envio, EstadoEnvio nuevoEstado);

    public abstract Envio consultarDetalle(Envio envio);
    public abstract ArrayList<Envio> consultarHistorial(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, EstadoEnvio estado);
    public abstract boolean agregarServicioAdicional(Envio envio, ServicioAdicional servicio);
    public abstract boolean eliminarServicioAdicional(Envio envio, ServicioAdicional servicio);
    // public abstract double calcularCostoBase(Envio envio);
    // public abstract double calcularCostoDecorado(Envio envio);
    public abstract ArrayList<Envio> listarEnvios();

    public abstract double calcularCostoCotizacion(Envio envio);
    public abstract double calcularCostoTotal(Envio envio);
    public abstract List<Envio> getEnvios();
    public abstract Envio actualizarEnvioAdmin(EnvioAdminDTO envio);
    public abstract Envio buscarEnvioPorId(String Id);

}
