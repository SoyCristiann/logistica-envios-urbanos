package edu.uniquindio.pgII.logistica.patrones.fachadas;

import edu.uniquindio.pgII.logistica.modelo.dto.*;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.*;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFacade {

    private final AdministradorSingleton admin = AdministradorSingleton.getInstance();
    private final edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService envioService = admin.getEnvioService();
    private final edu.uniquindio.pgII.logistica.modelo.util.Interface.IServicioAdicionalService servicioService = admin.getServiciosAdicionalesService();

    // ======================= PERFIL =======================
    public boolean actualizarPerfil(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) return false;

        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        boolean ok = admin.getUsuarioService().actualizarPerfil(usuario);
        if (!ok) return false;

        UsuarioDTO dtoActualizado = UsuarioMapper.toDTO(usuario);

        // Sincronizar sesión
        UsuarioDTO activo = SesionManagerSingleton.getInstance().getUsuarioActivo();
        if (activo != null && activo.getIdUsuario().equals(dtoActualizado.getIdUsuario())) {
            SesionManagerSingleton.getInstance().setUsuarioActivo(dtoActualizado);
        }

        return true;
    }

    // ======================= ENVIOS =======================
    public double cotizarEnvio(EnvioUsuarioDTO dto) {
        if (dto == null) return 0;
        Envio envio = EnvioUsuarioMapper.toEntity(dto);
        return envioService.calcularCosto(envio);
    }

    public boolean crearEnvio(EnvioUsuarioDTO dto) {
        if (dto == null) return false;
        Envio envio = EnvioUsuarioMapper.toEntity(dto);
        return envioService.crearEnvio(envio);
    }

    public boolean modificarEnvio(EnvioUsuarioDTO dto) {
        if (dto == null) return false;
        Envio envio = EnvioUsuarioMapper.toEntity(dto);
        return envioService.modificarEnvio(envio);
    }

    public boolean cancelarEnvio(EnvioUsuarioDTO dto) {
        if (dto == null || dto.getIdEnvio() == null || dto.getIdEnvio().isBlank()) return false;

        Envio envio = envioService.buscarEnvioPorId(dto.getIdEnvio());
        if (envio == null) return false;

        return envioService.cancelarEnvio(envio);
    }

    public EnvioUsuarioDTO rastrearEnvio(String id) {
        if (id == null || id.isBlank()) return null;

        Envio envio = envioService.buscarEnvioPorId(id);
        if (envio == null) return null;

        return EnvioUsuarioMapper.toDTO(envio);
    }

    public List<EnvioUsuarioDTO> obtenerHistorial(UsuarioDTO usuario, LocalDate inicio, LocalDate fin, String estadoTexto) {
        EstadoEnvio estado = null;

        if (estadoTexto != null && !estadoTexto.equalsIgnoreCase("Todos")) {
            try { estado = EstadoEnvio.valueOf(estadoTexto.toUpperCase()); }
            catch (Exception ignored) {}
        }

        List<Envio> lista = envioService.listarEnvios();
        if (lista == null) lista = new ArrayList<>();

        List<EnvioUsuarioDTO> resultado = new ArrayList<>();

        for (Envio e : lista) {
            boolean fechaOk = true;
            boolean estadoOk = true;

            if (inicio != null) fechaOk &= !e.getFechaCreacion().isBefore(inicio);
            if (fin != null) fechaOk &= !e.getFechaCreacion().isAfter(fin);

            if (estado != null) estadoOk = e.getEstado() == estado;

            if (fechaOk && estadoOk) resultado.add(EnvioUsuarioMapper.toDTO(e));
        }

        return resultado;
    }

    // ======================= SERVICIOS ADICIONALES =======================
    public boolean registrarServicio(ServicioAdicionalDTO dto) {
        if (dto == null) return false;

        ServicioAdicional s = ServicioAdicionalMapper.toEntity(dto);
        return servicioService.registrarServicioAdicional(s);
    }

    // ======================= DIRECCIONES =======================

    public boolean registrarDireccion(UsuarioDTO usuarioDTO, DireccionDTO direccionDTO) {
        if (usuarioDTO == null || direccionDTO == null) return false;

        UsuarioDTO usuarioDTOReal = admin.getUsuarioService().buscarUsuarioPorId(usuarioDTO.getIdUsuario());
        if (usuarioDTOReal == null) return false;

        Usuario usuarioReal = UsuarioMapper.toEntity(usuarioDTOReal);
        Direccion direccion = DireccionMapper.toEntity(direccionDTO);

        boolean ok = admin.getDireccionService().registrarDireccion(usuarioReal, direccion);
        if (!ok) return false;

        // Sincronizar sesión
        UsuarioDTO activo = SesionManagerSingleton.getInstance().getUsuarioActivo();
        if (activo != null && activo.getIdUsuario().equals(usuarioDTO.getIdUsuario())) {
            SesionManagerSingleton.getInstance().setUsuarioActivo(UsuarioMapper.toDTO(usuarioReal));
        }

        return true;
    }

    public boolean eliminarDireccion(UsuarioDTO usuarioDTO, DireccionDTO direccionDTO) {
        if (usuarioDTO == null || direccionDTO == null) return false;

        UsuarioDTO usuarioDTOReal = admin.getUsuarioService().buscarUsuarioPorId(usuarioDTO.getIdUsuario());
        if (usuarioDTOReal == null) return false;

        Usuario usuarioReal = UsuarioMapper.toEntity(usuarioDTOReal);
        Direccion direccion = DireccionMapper.toEntity(direccionDTO);

        boolean ok = admin.getDireccionService().eliminarDireccion(usuarioReal, direccion);
        if (!ok) return false;

        // Sincronizar sesión
        UsuarioDTO activo = SesionManagerSingleton.getInstance().getUsuarioActivo();
        if (activo != null && activo.getIdUsuario().equals(usuarioDTO.getIdUsuario())) {
            SesionManagerSingleton.getInstance().setUsuarioActivo(UsuarioMapper.toDTO(usuarioReal));
        }

        return true;
    }

    public UsuarioDTO obtenerUsuarioPorId(String idUsuario) {
        if (idUsuario == null || idUsuario.isBlank()) return null;
        return admin.getUsuarioService().buscarUsuarioPorId(idUsuario);
    }
}
