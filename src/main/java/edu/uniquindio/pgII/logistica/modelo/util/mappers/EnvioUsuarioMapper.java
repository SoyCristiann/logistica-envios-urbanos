package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioUsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IServicioAdicionalService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.EnvioBuilder;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

import java.util.ArrayList;
import java.util.List;

public class EnvioUsuarioMapper {

    public static EnvioUsuarioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        return new EnvioUsuarioDTO(
                envio.getUsuario().getIdUsuario(),
                DireccionMapper.toDTO(envio.getOrigen()),
                DireccionMapper.toDTO(envio.getDestino()),
                envio.getPeso(),
                envio.getLargo(),
                envio.getAncho(),
                envio.getAlto(),
                envio.getFechaCreacion(),
                envio.getServiciosAdicionales(),
                envio.getCosto(),
                UsuarioMapper.toDTO(envio.getUsuario())
        );
    }

    public static Envio toEntity(EnvioUsuarioDTO dto) {
        if (dto == null) throw new IllegalArgumentException("El DTO no puede ser null");

        IUsuarioService usuarioService = AdministradorSingleton.getInstance().getUsuarioService();
        IServicioAdicionalService servicioService = AdministradorSingleton.getInstance().getServiciosAdicionalesService();

        // --- Obtener usuario real ---
        if (dto.getUsuario() == null || dto.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("Usuario inválido en el DTO");
        }
        Usuario usuario = UsuarioMapper.toEntity(usuarioService.buscarUsuarioPorId(dto.getUsuario().getIdUsuario()));
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con id: " + dto.getUsuario().getIdUsuario());
        }

        // --- Direcciones ---
        Direccion origen = DireccionMapper.toEntity(dto.getOrigen());
        Direccion destino = DireccionMapper.toEntity(dto.getDestino());
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Dirección origen o destino inválida");
        }

        // --- Servicios adicionales ---
        List<ServicioAdicional> servicios = new ArrayList<>();
        if (dto.getServiciosAdicionales() != null) {
            for (ServicioAdicional s : dto.getServiciosAdicionales()) {
                ServicioAdicional serv = servicioService.buscarServicioPorId(s.getIdService());
                if (serv != null) servicios.add(serv);
            }
        }

        // --- Construir Envío ---
        EnvioBuilder builder = new EnvioBuilder(
                origen,
                destino,
                dto.getPeso(),
                dto.getLargo(),
                dto.getAncho(),
                dto.getAlto(),
                usuario
        );

        if (!servicios.isEmpty()) builder.withServiciosAdicionales(servicios);

        Envio envio = builder.build();

        if (dto.getFechaCreacion() != null) envio.setFechaCreacion(dto.getFechaCreacion());

        return envio;
    }
}
