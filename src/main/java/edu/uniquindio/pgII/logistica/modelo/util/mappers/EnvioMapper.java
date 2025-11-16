package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IServicioAdicionalService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.EnvioBuilder;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

import java.util.ArrayList;
import java.util.List;

public class EnvioMapper {

    private EnvioMapper(){}

    public static Envio toEntity(EnvioDTO dto) {
        IUsuarioService usuarioService = AdministradorSingleton.getInstance().getUsuarioService();
        IServicioAdicionalService serviciosAdicionales= AdministradorSingleton.getInstance().getServiciosAdicionalesService();
        IServicioAdicionalService servicioAdicionalService= AdministradorSingleton.getInstance().getServiciosAdicionalesService();

        Usuario usuario= UsuarioMapper.toEntity(usuarioService.buscarUsuarioPorId(dto.getIdUsuario()));
        if (usuario==null){
            return null;
        }

        Direccion origen= DireccionMapper.toEntity(dto.getOrigen());
        Direccion destino= DireccionMapper.toEntity(dto.getDestino());

        List<ServicioAdicional> serviciosAdicionalesEntities = listarServiciosAdicionales(dto.getServiciosAdicionales(), servicioAdicionalService);

        EnvioBuilder builder = new EnvioBuilder(origen, destino, dto.getPeso(), dto.getLargo(),dto.getAncho(), dto.getAlto(), usuario);

        if (!serviciosAdicionalesEntities.isEmpty()) {
            builder.withServiciosAdicionales(serviciosAdicionalesEntities);
        }

        return builder.build();
    }


    public static EnvioDTO toDTO(Envio envio) {
        List<String> serviciosAdicionalesNombres = new ArrayList<>();

        if (envio.getServiciosAdicionales() != null) {
            for (ServicioAdicional servicio : envio.getServiciosAdicionales()) {
                serviciosAdicionalesNombres.add(servicio.getNombre());
            }
        }

        return new EnvioDTO(
                envio.getUsuario().getIdUsuario(),
                DireccionMapper.toDTO(envio.getOrigen()),
                DireccionMapper.toDTO(envio.getDestino()),
                envio.getPeso(),
                envio.getLargo(),
                envio.getAncho(),
                envio.getAlto(),
                serviciosAdicionalesNombres
        );
    }

    //Este fragemeno de código se lleva aparte para no extender el código de toEntity.
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
