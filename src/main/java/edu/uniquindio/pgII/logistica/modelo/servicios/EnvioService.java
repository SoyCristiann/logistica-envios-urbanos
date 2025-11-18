package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioAdminDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.EnvioAdminMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.RepartidorMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.patrones.decorator.*;

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

    //  Crear envío
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
            envioExistente.setFechaEntrega(envio.getFechaEntrega());
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
            envioExistente.setCosto(calcularCostoDecorado(EnvioAdminMapper.toEntity(envio)));
            System.out.println("E nuevo costo es: " +  envioExistente.getCosto());
            envioExistente.setEstado(envio.getEstado());
            envioExistente.setFechaEntrega(envio.getFechaEntrega());
            envioExistente.setRepartidor(RepartidorMapper.toEntity(envio.getRepartidor()));
            System.out.println("Repartidor asignado: " +   envioExistente.getRepartidor().getNombre());
            return envioExistente;
        }
        return null;
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

    @Override
    public ArrayList<Envio> listarEnvios() {
        return null;
    }


    // estos métodos se usaban cuando se implementaba decorator, al usar builder para agregar un servicio Adicional cambia el proceso

    //  Calcular costo base (tarifa inicial)
    @Override
    public double calcularCostoBase(Envio envio) {
        double peso = envio.getPeso();
        double alto = envio.getAlto();
        double largo = envio.getLargo();
        double ancho = envio.getAncho();



        var tarifa = tarifaService.calcularTarifa(peso, alto, ancho, largo, envio.getServiciosAdicionales());
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
            switch (servicio.getNombreServicio().toLowerCase()) {
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
