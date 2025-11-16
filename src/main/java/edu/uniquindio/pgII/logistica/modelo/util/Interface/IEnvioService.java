package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;

public interface IEnvioService {
    public abstract boolean crearEnvio(EnvioDTO envioDTO);
    public abstract boolean registrarServicio(ServicioAdicional servicioAdicionalNuevo);
}
