package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioAdminDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IServicioAdicionalService;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnvioAdminMapper {

    private EnvioAdminMapper() {}

    public static Envio toEntity(EnvioAdminDTO dto) {
        if (dto == null) {
            return null;
        }

        IEnvioService envioService = AdministradorSingleton.getInstance().getEnvioService();
        IServicioAdicionalService servicioAdicionalService = AdministradorSingleton.getInstance().getServiciosAdicionalesService();

        Envio envioOriginal = envioService.buscarEnvioPorId(dto.getIdEnvio());
        if (envioOriginal == null) {
            return null;
        }

        Direccion origenEntidad = DireccionMapper.toEntity(dto.getOrigen());
        Direccion destinoEntidad = DireccionMapper.toEntity(dto.getDestino());
        Repartidor repartidorEntidad = RepartidorMapper.toEntity(dto.getRepartidor());
        List<ServicioAdicional> serviciosAdicionalesEntities = listarServiciosAdicionales(dto.getServiciosAdicionales(), servicioAdicionalService);

        Envio nuevaEntidad = new Envio();
        nuevaEntidad.setIdEnvio(envioOriginal.getIdEnvio());
        nuevaEntidad.setCosto(envioOriginal.getCosto());
        nuevaEntidad.setFechaCreacion(envioOriginal.getFechaCreacion());
        nuevaEntidad.setUsuario(envioOriginal.getUsuario());

        nuevaEntidad.setOrigen(origenEntidad);
        nuevaEntidad.setDestino(destinoEntidad);
        nuevaEntidad.setPeso(dto.getPeso());
        nuevaEntidad.setAlto(dto.getAlto());
        nuevaEntidad.setLargo(dto.getLargo());
        nuevaEntidad.setAncho(dto.getAncho());
        nuevaEntidad.setEstado(dto.getEstado());
        nuevaEntidad.setFechaEntrega(dto.getFechaEntrega());
        nuevaEntidad.setRepartidor(repartidorEntidad);
        nuevaEntidad.setServiciosAdicionales(serviciosAdicionalesEntities);

        return nuevaEntidad;
    }



    public static EnvioAdminDTO toDTO(Envio envio) {
        if (envio == null) {
            return null;
        }

        String nombreUsuario;
        Usuario usuario = envio.getUsuario();
        if (usuario != null) {
            nombreUsuario = usuario.getNombreCompleto();
        } else {
            nombreUsuario = null;
        }

        List<String> serviciosNombres = new ArrayList<>();
        if (envio.getServiciosAdicionales() != null) {
            serviciosNombres = envio.getServiciosAdicionales().stream()
                    .map(ServicioAdicional::getNombreServicio)
                    .collect(Collectors.toList());
        }

        DireccionDTO origenDTO = DireccionMapper.toDTO(envio.getOrigen());
        DireccionDTO destinoDTO = DireccionMapper.toDTO(envio.getDestino());
        RepartidorDTO repartidorDTO = RepartidorMapper.toDTO(envio.getRepartidor());

        return new EnvioAdminDTO(
                envio.getIdEnvio(),
                origenDTO,
                destinoDTO,
                envio.getPeso(),
                envio.getAlto(),
                envio.getLargo(),
                envio.getAncho(),
                envio.getEstado(),
                envio.getFechaCreacion(),
                envio.getFechaEntrega(),
                nombreUsuario,
                repartidorDTO,
                serviciosNombres
        );
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
}