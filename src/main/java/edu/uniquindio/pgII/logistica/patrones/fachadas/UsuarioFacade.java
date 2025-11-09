package edu.uniquindio.pgII.logistica.patrones.fachadas;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.servicios.EnvioService;
import edu.uniquindio.pgII.logistica.modelo.servicios.ServicioAdicionalService;
import edu.uniquindio.pgII.logistica.modelo.servicios.UsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.EnvioMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.UsuarioMapper;

public class UsuarioFacade {

    private final UsuarioService usuarioService;
    private final EnvioService envioService;
    private final ServicioAdicionalService servicioAdicionalService;


    public UsuarioFacade() {
        this.usuarioService = new UsuarioService();
        this.envioService = new EnvioService();
        this.servicioAdicionalService = new ServicioAdicionalService();
    }


    // GESTIÓN USUARIOS
    public boolean actualizarPerfil(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null) {
            Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);

            return true;
        }
        return false;
    }



    // GESTIÓN ENVÍOS
    public double cotizarEnvio(EnvioDTO envioDTO) {
        Envio envio = EnvioMapper.toEntity(envioDTO);
        return envioService.calcularCostoDecorado(envio);
    }

    public boolean crearEnvio(EnvioDTO envioDTO) {
        Envio envio = EnvioMapper.toEntity(envioDTO);
        return envioService.crearEnvio(envio);
    }

    public boolean modificarEnvio(EnvioDTO envioDTO) {
        Envio envio = EnvioMapper.toEntity(envioDTO);
        return envioService.modificarEnvio(envio);
    }


}
