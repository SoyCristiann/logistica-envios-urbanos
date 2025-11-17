package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.EnvioMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.patrones.decorator.*;

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

    //  Generar ID
    public String generarId() {
        String id = "ENV-" + contador;
        contador++;
        return id;
    }

    //  Crear envío
    @Override

    public boolean crearEnvio(Envio envio) {

        if (envio != null) {
            envio.setIdEnvio(generarId());
            envio.setFechaCreacion(LocalDate.now());
            envio.setEstado(EstadoEnvio.SOLICITADO);

//            // Calcular tarifa base
//            double costoBase = calcularCostoBase(envio);
//            envio.setCosto(costoBase);

            // Aplicar servicios adicionales si los tiene

                double costoFinal = calcularCostoTotal(envio);
                envio.setCosto(costoFinal);

            envios.add(envio);
            for(Envio e: envios){
                System.out.println(e);
            }
            return true;
        }
        return false;
    }



    //  Modificar envío (solo si sigue en estado SOLICITADO)
    @Override
    public boolean modificarEnvio(Envio envio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && envioExistente.getEstado() == EstadoEnvio.SOLICITADO) {
            envioExistente.setDestino(envio.getDestino());
            envioExistente.setPeso(envio.getPeso());
            envioExistente.setAlto(envio.getAlto());
            envioExistente.setLargo(envio.getLargo());
            envioExistente.setAncho(envio.getAncho());
            envioExistente.setFechaEstimada(envio.getFechaEstimada());
            return true;
        }
        return false;
    }

    //  Cancelar envío
    @Override
    public boolean cancelarEnvio(Envio envio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && envioExistente.getEstado() == EstadoEnvio.SOLICITADO) {
            envioExistente.setEstado(EstadoEnvio.CANCELADO);
            return true;
        }
        return false;
    }

    //  Asignar repartidor(Lo hace el admin)
    @Override
    public boolean asignarRepartidor(Envio envio, Repartidor repartidor) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null && repartidor != null) {
            envioExistente.setRepartidor(repartidor);
            envioExistente.setEstado(EstadoEnvio.ASIGNADO);
            return true;
        }
        return false;
    }

    //  Actualizar estado
    @Override
    public boolean actualizarEstado(Envio envio, EstadoEnvio nuevoEstado) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null) {
            envioExistente.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    //  Consultar detalle
    @Override
    public Envio consultarDetalle(Envio envio) {
        return buscarEnvioPorId(envio.getIdEnvio());
    }

    //  Consultar historial de envíos de un usuario
    @Override
    public ArrayList<Envio> consultarHistorial(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, EstadoEnvio estado) {
        ArrayList<Envio> listaFiltrada = new ArrayList<>();

        for (Envio envio : envios) {
            if (envio.getUsuario().equals(usuario)) {
                LocalDate fecha = envio.getFechaCreacion();
                if ((fecha.isAfter(fechaInicio) || fecha.isEqual(fechaInicio)) &&
                        (fecha.isBefore(fechaFin) || fecha.isEqual(fechaFin))) {

                    if (estado == null || envio.getEstado() == estado) {
                        listaFiltrada.add(envio);
                    }
                }
            }
        }
        return listaFiltrada;
    }

    //  Agregar servicio adicional
    @Override
    public boolean agregarServicioAdicional(Envio envio, ServicioAdicional servicio) {
        if (envio != null && servicio != null) {
            envio.getServiciosAdicionales().add(servicio);
            double costoFinal = calcularCostoServicio(envio);
            envio.setCosto(costoFinal);
            return true;
        }
        return false;
    }


    //  Eliminar servicio adicional
    @Override
    public boolean eliminarServicioAdicional(Envio envio, ServicioAdicional servicio) {
        if (envio != null && servicio != null) {
            envio.getServiciosAdicionales().remove(servicio);
            double costoFinal = calcularCostoTotal(envio);
            envio.setCosto(Math.max(costoFinal, 0));
            return true;
        }
        return false;
    }


    // estos métodos se usaban cuando se implementaba decorator, al usar builder para agregar un servicio Adicional cambia el proceso
    /*
    //  Calcular costo base (tarifa inicial)
    @Override
    public double calcularCostoBase(Envio envio) {
        double peso = envio.getPeso();
        double volumen = 1; // simplificado
        double distancia = 10; // simulacion
        double prioridad = 1;

        var tarifa = tarifaService.calcularTarifa(peso, volumen, distancia, prioridad);
        envio.setCosto(tarifa.getTotal());
        return tarifa.getTotal();
    }

    //  Calcular costo total decorado
    @Override
    public double calcularCostoDecorado(Envio envio) {
        // Si el costo base aun no ha sido calculado, lo hace
        if (envio.getCosto() == 0) {
            double base = calcularCostoBase(envio);
            envio.setCosto(base);
        }

        EnvioComponente envioDecorado = new EnvioBase(envio.getCosto(), "Envío base");


        for (ServicioAdicional servicio : envio.getServiciosAdicionales()) {
            switch (servicio.getNombre().toLowerCase()) {
                case "seguro":
                    envioDecorado = new EnvioConSeguro(envioDecorado);
                    break;
                case "firma requerida":
                    envioDecorado = new EnvioConFirma(envioDecorado);
                    break;
                case "frágil":
                case "fragil":
                    envioDecorado = new EnvioFragil(envioDecorado);
                    break;
                case "prioritario":
                    envioDecorado = new EnvioPrioritario(envioDecorado);
                    break;
            }
        }

        //  Retorna el costo final decorado
        return envioDecorado.getCosto();

    } */



    //  Buscar envío por ID
    private Envio buscarEnvioPorId(String id) {
        for (Envio e : envios) {
            if (e.getIdEnvio().equals(id)) {
                return e;
            }
        }
        return null;
    }

    //  Listar todos
    @Override
    public ArrayList<Envio> listarEnvios() {
        return new ArrayList<>(envios);
    }

    @Override
    public double calcularCostoCotizacion(Envio envio) {

        return 0;
    }

    public  double calcularCostoServicio(Envio envio) {

        return 0;
    }

    @Override
    public double calcularCostoTotal(Envio envio) {

        return 0;
    }

    @Override
    // para datos dummy
    public boolean crearEnvio(EnvioDTO envioDTO) {
        return false;
    }



    @Override
    public boolean registrarServicio(ServicioAdicional servicioAdicionalNuevo) {
        return false;
    }


    @Override
    public List<Envio> getEnvios() {
        return envios;
    }
}
