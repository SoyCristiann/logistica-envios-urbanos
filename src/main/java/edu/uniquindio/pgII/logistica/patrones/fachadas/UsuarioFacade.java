package edu.uniquindio.pgII.logistica.patrones.fachadas;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.ServicioAdicionalDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.DireccionMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.servicios.EnvioService;
import edu.uniquindio.pgII.logistica.modelo.servicios.ServicioAdicionalService;
import edu.uniquindio.pgII.logistica.modelo.servicios.UsuarioService;

import edu.uniquindio.pgII.logistica.modelo.util.mappers.EnvioMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.ServicioAdicionalMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.UsuarioMapper;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFacade {

    private final AdministradorSingleton admin = AdministradorSingleton.getInstance();

    private final edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService envioService =
            admin.getEnvioService();

    private final edu.uniquindio.pgII.logistica.modelo.util.Interface.IServicioAdicionalService servicioService =
            admin.getServiciosAdicionalesService();


    // GESTIÓN USUARIOS
    public boolean actualizarPerfil(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null) {
            Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
            admin.getUsuarioService().actualizarPerfil(usuario);
            return true;
        }
        return false;
    }

    // COTIZAR
    public double cotizarEnvio(EnvioDTO dto) {
        if (dto == null) return 0;
        Envio envio = EnvioMapper.toEntity(dto);
        return envioService.calcularCostoCotizacion(envio);
    }


    public boolean crearEnvio(EnvioDTO envioDTO) {
        return envioService.crearEnvio(envioDTO);

    }

    public boolean modificarEnvio(EnvioDTO dto) {
        if (dto == null) return false;
        Envio e = EnvioMapper.toEntity(dto);
        return envioService.modificarEnvio(e);
    }

    public boolean cancelarEnvio(EnvioDTO dto) {
        if (dto == null) return false;
        Envio e = EnvioMapper.toEntity(dto);
        return envioService.cancelarEnvio(e);
    }

    // RASTREAR
    public EnvioDTO rastrearEnvio(String id) {
        if (id == null || id.isEmpty()) return null;

        for (Envio e : envioService.getEnvios()) {
            if (e.getIdEnvio().equalsIgnoreCase(id)) {
                return EnvioMapper.toDTO(e);
            }
        }
        return null;
    }

    // HISTORIAL
    public List<EnvioDTO> obtenerHistorial(
            UsuarioDTO usuario,
            LocalDate inicio,
            LocalDate fin,
            String estadoTexto) {

        EstadoEnvio estado = null;

        if (estadoTexto != null && !estadoTexto.equalsIgnoreCase("Todos")) {
            try {
                estado = EstadoEnvio.valueOf(estadoTexto.toUpperCase());
            } catch (Exception ignored) {}
        }

        List<Envio> lista = envioService.listarEnvios();
        List<EnvioDTO> resultado = new ArrayList<>();

        for (Envio e : lista) {

            boolean filtrarFecha = true;
            boolean filtrarEstado = true;

            if (inicio != null)
                filtrarFecha &= !e.getFechaCreacion().isBefore(inicio);

            if (fin != null)
                filtrarFecha &= !e.getFechaCreacion().isAfter(fin);

            if (estado != null)
                filtrarEstado = e.getEstado() == estado;

            if (filtrarFecha && filtrarEstado) {
                resultado.add(EnvioMapper.toDTO(e));
            }
        }

        return resultado;
    }

    //  SERVICIOS ADICIONALES
    public boolean registrarServicio(ServicioAdicionalDTO dto) {
        ServicioAdicional s = ServicioAdicionalMapper.toEntity(dto);
        return servicioService.registrarServicioAdicional(s);
    }


    public boolean registrarDireccion(UsuarioDTO usuario, DireccionDTO dto) {
        Usuario usuarioActual = UsuarioMapper.toEntity(usuario);
        return admin.getDireccionService().registrarDireccion(usuarioActual, DireccionMapper.toEntity(dto));
    }

    public boolean eliminarDireccion(UsuarioDTO usuario, DireccionDTO dto) {
        Usuario usuarioActual = UsuarioMapper.toEntity(usuario);
        return admin.getDireccionService().eliminarDireccion(usuarioActual, DireccionMapper.toEntity(dto));
    }

    public List<Direccion> obtenerDirecciones(){
        ArrayList<Direccion> listaDirecciones = admin.getDireccionService().listarDirecciones();
        return listaDirecciones;
    }

}
