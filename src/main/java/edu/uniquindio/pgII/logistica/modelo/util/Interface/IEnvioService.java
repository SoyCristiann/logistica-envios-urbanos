package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioAdminDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;

import java.util.List;

public interface IEnvioService {
    public abstract boolean crearEnvio(EnvioDTO envioDTO);
    public abstract boolean registrarServicio(ServicioAdicional servicioAdicionalNuevo);
    public abstract List<Envio> getEnvios();
    public abstract Envio actualizarEnvioAdmin(EnvioAdminDTO envio);
    public abstract Envio buscarEnvioPorId(String Id);
}
