package edu.uniquindio.pgII.logistica.patrones.fachadas;

import edu.uniquindio.pgII.logistica.modelo.dto.*;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.*;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.servicios.EnvioService;
import edu.uniquindio.pgII.logistica.modelo.servicios.ServicioAdicionalService;
import edu.uniquindio.pgII.logistica.modelo.servicios.UsuarioService;

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
    public double cotizarEnvio(EnvioUsuarioDTO dto) {
        if (dto == null) return 0;
        Envio envio = EnvioUsuarioMapper.toEntity(dto);
        return envioService.calcularCosto(envio);
    }


    public boolean crearEnvio(EnvioDTO envioDTO) {
        return envioService.crearEnvio(envioDTO);

    }

    // Para Usuario
    public boolean crearEnvioUsuario(EnvioUsuarioDTO dto) {
        Envio envio = EnvioUsuarioMapper.toEntity(dto);
        return envioService.crearEnvio(envio);
    }

    // Modificar Envio (Para Usuario)
    public boolean modificarEnvio(EnvioUsuarioDTO dto) {
        if (dto == null) return false;
        Envio e = EnvioUsuarioMapper.toEntity(dto);
        return envioService.modificarEnvio(e);
    }

    public boolean cancelarEnvio(EnvioUsuarioDTO dto) {
        if (dto == null) return false;
        Envio e = EnvioUsuarioMapper.toEntity(dto);
        return envioService.cancelarEnvio(e);
    }

    // RASTREAR
    public EnvioUsuarioDTO rastrearEnvio(String id) {
        if (id == null || id.isEmpty()) return null;

        for (Envio e : envioService.getEnvios()) {
            if (e.getIdEnvio().equalsIgnoreCase(id)) {
                return EnvioUsuarioMapper.toDTO(e);
            }
        }
        return null;
    }

    // HISTORIAL
    public List<EnvioUsuarioDTO> obtenerHistorial(
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
        if (lista == null) {
            lista = new ArrayList<>();
        }

        List<EnvioUsuarioDTO> resultado = new ArrayList<>();

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
                resultado.add(EnvioUsuarioMapper.toDTO(e));
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
        String idDireccion = dto.getIdDireccion();
        return admin.getDireccionService().eliminarDireccion(usuarioActual, DireccionMapper.toEntity(dto));
    }

    public List<Direccion> obtenerDirecciones(){
        ArrayList<Direccion> listaDirecciones = admin.getDireccionService().listarDirecciones();
        return listaDirecciones;
    }

}
