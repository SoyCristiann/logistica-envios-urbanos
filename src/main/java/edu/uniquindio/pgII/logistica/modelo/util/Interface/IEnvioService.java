package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;

public interface IEnvioService {
    public abstract boolean registrarServicio(ServicioAdicional servicioAdicionalNuevo);
}
