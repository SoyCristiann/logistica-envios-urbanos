package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioAdminDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Tarifa;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IServicioAdicionalService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.EnvioAdminMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.RepartidorMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.patrones.decorator.*;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //  Crear envío para Usuario
    @Override
    public boolean crearEnvio(Envio envio) {

        if (envio != null) {
            envio.setIdEnvio(generarId());
            if (envio.getFechaCreacion() != null) {
                envio.setFechaCreacion(envio.getFechaCreacion());
            } else {
                envio.setFechaCreacion(LocalDate.now());
            }
            envio.setEstado(EstadoEnvio.SOLICITADO);

           // Calcular tarifa
            double costo = calcularCosto(envio);
            envio.setCosto(costo);

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

            if (envio.getOrigen() != null)
                envioExistente.setOrigen(envio.getOrigen());

            if (envio.getDestino() != null)
                envioExistente.setDestino(envio.getDestino());

            if (envio.getPeso() > 0)
                envioExistente.setPeso(envio.getPeso());

            if (envio.getLargo() > 0)
                envioExistente.setLargo(envio.getLargo());

            if (envio.getAncho() > 0)
                envioExistente.setAncho(envio.getAncho());

            if (envio.getAlto() > 0)
                envioExistente.setAlto(envio.getAlto());

            if (envio.getServiciosAdicionales() != null)
                envioExistente.setServiciosAdicionales(envio.getServiciosAdicionales());

            // Recalcular costo
            double nuevoCosto = calcularCosto(envioExistente);
            envioExistente.setCosto(nuevoCosto);

            return true;
        }
        return false;
    }


    // este método es para actualizar el envío desde la vista administrador. Puede modificar el estado, agregar repartidor y recalcular el valor según medidas.
    public Envio actualizarEnvioAdmin(EnvioAdminDTO envio) {
        Envio envioExistente = buscarEnvioPorId(envio.getIdEnvio());
        if (envioExistente != null) {
            envioExistente.setPeso(envio.getPeso());
            envioExistente.setAlto(envio.getAlto());
            envioExistente.setLargo(envio.getLargo());
            envioExistente.setAncho(envio.getAncho());

            IServicioAdicionalService servicioAdicionalService = AdministradorSingleton.getInstance().getServiciosAdicionalesService();
            List<ServicioAdicional> servicioAdicionales = listarServiciosAdicionales(envio.getServiciosAdicionales(),servicioAdicionalService );
            Tarifa tarifa = tarifaService.calcularTarifa(envio.getPeso(), envio.getAlto(), envio.getAncho(), envio.getLargo(), servicioAdicionales);

            envioExistente.setCosto(tarifa.getCostoBase());
            System.out.println("E nuevo costo es: " +  envioExistente.getCosto());
            envioExistente.setEstado(envio.getEstado());
            envioExistente.setFechaEntrega(envio.getFechaEntrega());
            envioExistente.setRepartidor(RepartidorMapper.toEntity(envio.getRepartidor()));
            System.out.println("Repartidor asignado: " +   envioExistente.getRepartidor().getNombre());
            return envioExistente;
        }
        return null;
    }

    private static List<ServicioAdicional> listarServiciosAdicionales(List<String> nombresServicios, IServicioAdicionalService servicioAdicionalService)
    {
        if (nombresServicios == null) {
            return new ArrayList<>();
        }

        List<ServicioAdicional> entidades = new ArrayList<>();

        for (String id : nombresServicios) {
            ServicioAdicional servicio = servicioAdicionalService.buscarServicioPorId(id);
            if (servicio != null) {
                entidades.add(servicio);
            } else {
                System.out.println("No se encoentró el servicio: " + id);
            }
        }
        return entidades;
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
            Tarifa tarifa = tarifaService.calcularTarifa(envio.getPeso(), envio.getAlto(), envio.getAncho(), envio.getLargo(), envio.getServiciosAdicionales());
            double costoFinal = tarifa.getCostoBase();
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
            double costoFinal = calcularCosto(envio);
            envio.setCosto(Math.max(costoFinal, 0));
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Envio> listarEnvios() {
        return null;
    }



    //  Calcular costo base (tarifa inicial)
    @Override
    public double calcularCosto(Envio envio) {
        double peso = envio.getPeso();
        double alto = envio.getAlto();
        double largo = envio.getLargo();
        double ancho = envio.getAncho();



        Tarifa tarifa = tarifaService.calcularTarifa(peso, alto, ancho, largo, envio.getServiciosAdicionales());
        envio.setCosto(tarifa.getTotal());
        return tarifa.getTotal();
    }


    //  Buscar envío por ID
    @Override
    public Envio buscarEnvioPorId(String id) {
        for (Envio e : envios) {
            if (e.getIdEnvio().equals(id)) {
                return e;
            }
        }
        return null;
    }


    @Override
    public List<Envio> getEnvios() {
        return envios;

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


    //Métodos para los gráficos de las métricas:
    /**
     * Calcula la frecuencia de uso de cada servicio adicional en todos los envíos de la lista.
     * @return Un mapa donde la clave es el nombre del servicio y el valor es la cantidad de veces que ha sido usado.
     */
    @Override
    public Map<String, Integer> getServiciosAdicionalesMasUsados() {
        //Se crea el mapa donde se a almacenar el conteo de los servicios.
        Map<String, Integer> conteoServicios = new HashMap<>();
        for (Envio envio : envios) {

            //Guarda los servicios en una lista para validar que existan.
            List<ServicioAdicional> servicios = envio.getServiciosAdicionales();

            if (servicios != null) {
                //Itera sobre los servicios de cada envío.
                for (ServicioAdicional servicio : servicios) {
                    if (servicio != null) {
                        String idServicio = servicio.getIdService();

                        // Si el nombre del servicio ya existe en el mapa, suma 1. Si no, inicializa en 1.
                        conteoServicios.put(
                                idServicio,
                                conteoServicios.getOrDefault(idServicio, 0) + 1
                        );
                    }
                }
            }
        }
        return conteoServicios;
    }

    /**
     * Calcula el tiempo promedio de entrega (en días) para todos los envíos completados.
     *
     * @return El promedio de días o 0.0 si no hay envíos entregados.
     */
    @Override
    public double getTiempoPromedioEntregaGlobal() {
        long totalDias = 0;
        int enviosCompletados = 0;

        for (Envio envio : envios) {
            // Solo se consideran envíos que fueron entregados y tienen ambas fechas
            if (envio.getEstado() == EstadoEnvio.ENTREGADO &&
                    envio.getFechaEntrega() != null &&
                    envio.getFechaCreacion() != null) {

                // Calcula la diferencia en días entre la creación y la entrega
                long diferenciaDias = ChronoUnit.DAYS.between(envio.getFechaCreacion(), envio.getFechaEntrega());

                //Solo se suma si la diferencia no es un valor negativo.
                if (diferenciaDias >= 0) {
                    totalDias += diferenciaDias;
                    enviosCompletados++;
                }
            }
        }

        if (enviosCompletados == 0) {
            return 0.0;
        }

        // Retorna el promedio
        return (double) totalDias / enviosCompletados;
    }


}
