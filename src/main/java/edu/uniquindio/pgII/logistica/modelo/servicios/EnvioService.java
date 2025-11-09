package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnvioService implements IEnvioService {

    private List<Envio> envios;
    private TarifaService tarifaService;
    private int contador;

    public EnvioService() {
        this.envios = new ArrayList<>();
        this.tarifaService = new TarifaService();
        this.contador = 1;
    }

    // Genera un id
    public String generarId() {
        String id = "ENV-" + contador;
        contador++;
        return id;
    }

    // Crear un envío
    public boolean crearEnvio(Envio envio) {
        if (envio != null) {
            envio.setIdEnvio(generarId());
            envio.setFechaCreacion(LocalDate.now());
            envio.setEstado(EstadoEnvio.SOLICITADO);
            envios.add(envio);
            return true;
        }
        return false;
    }

    // Modificar un envío
    public boolean modificarEnvio(Envio envio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null) {
            if(envio.getEstado() == EstadoEnvio.SOLICITADO) {
                envioExistente.setDestino(envio.getDestino());
                envioExistente.setPeso(envio.getPeso());
                envioExistente.setDimensiones(envio.getDimensiones());
                envioExistente.setFechaEstimada(envio.getFechaEstimada());
                return true;
            }
        }
        return false;
    }


    // Cancelar un envío
    public boolean cancelarEnvio(Envio envio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && envioExistente.getEstado() == EstadoEnvio.SOLICITADO) {
            envioExistente.setEstado(EstadoEnvio.INCIDENCIA);
            return true;
        }
        return false;
    }

    // Asignar un repartidor
    public boolean asignarRepartidor(Envio envio, Repartidor repartidor) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && repartidor != null) {
            envioExistente.setRepartidor(repartidor);
            envioExistente.setEstado(EstadoEnvio.ASIGNADO);
            return true;
        }
        return false;
    }

    // Actualizar el estado
    public boolean actualizarEstado(Envio envio, EstadoEnvio nuevoEstado) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null) {
            envioExistente.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    // Consultar un envío
    public Envio consultarDetalle(Envio envio) {
        return buscarEnvioPorId(envio.getIdEnvio());
    }

    // Consultar historial por usuario y filtro
    public ArrayList<Envio> consultarHistorial(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, EstadoEnvio estado) {
        ArrayList<Envio> listaFiltrada = new ArrayList<>();

        for (Envio envio : envios) {
            // Verificar si el envio pertenece al usuario
            if (envio.getUsuario().equals(usuario)) {

                // Verificar si la fecha del envio esta entre las fechas dadas
                LocalDate fecha = envio.getFechaCreacion();
                if ((fecha.isAfter(fechaInicio) || fecha.isEqual(fechaInicio)) &&
                        (fecha.isBefore(fechaFin) || fecha.isEqual(fechaFin))) {

                    // Verificar si el estado coincide o si no se filtró por estado
                    if (estado == null || envio.getEstado() == estado) {
                        listaFiltrada.add(envio);
                    }
                }
            }
        }

        return listaFiltrada;
    }


    // Agregar servicio adicional
    public boolean agregarServicioAdicional(Envio envio, ServicioAdicional servicio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && servicio != null) {
            double nuevoCosto = envioExistente.getCosto() + servicio.getCosto();
            envioExistente.setCosto(nuevoCosto);
            return true;
        }
        return false;
    }

    // Eliminar servicio adicional (solo resta el costo)
    public boolean eliminarServicioAdicional(Envio envio, ServicioAdicional servicio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && servicio != null) {
            double nuevoCosto = envioExistente.getCosto() - servicio.getCosto();
            envioExistente.setCosto(Math.max(nuevoCosto, 0)); // no dejar costo negativo
            return true;
        }
        return false;
    }

    // Calcular costo total
    public double calcularCostoTotal(Envio envio) {
        if (envio == null) return 0;

        double peso = envio.getPeso();
        double volumen = 1; // por ahora se puede dejar fijo
        double distancia = 10; // ejemplo simple
        double prioridad = 1;  // ejemplo simple

        var tarifa = tarifaService.calcularTarifa(peso, volumen, distancia, prioridad);
        envio.setCosto(tarifa.getTotal());
        return tarifa.getTotal();
    }

    // Buscar envío por id
    private Envio buscarEnvioPorId(String id) {
        for (Envio e : envios) {
            if (e.getIdEnvio().equals(id)) {
                return e;
            }
        }
        return null;
    }

    // Listar todos los envíos
    public ArrayList<Envio> listarEnvios() {
        return new ArrayList<>(envios);
    }
}
