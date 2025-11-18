package edu.uniquindio.pgII.logistica.patrones;

import edu.uniquindio.pgII.logistica.modelo.servicios.*;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.*;

public class ServiceFactory {

    public IUsuarioService crearUsuarioService(){
        return new UsuarioService();
    }

    public IDireccionService crearDireccionService(){
        return new DireccionService();
    }

    public IReporteService crearReporteService(){
        return new ReporteService();
    }

    public IEnvioService crearEnvioService(){
        return new EnvioService();
    }

    public IServicioAdicionalService crearServicioAdicionalService(){
        return new ServicioAdicionalService();
    }
}
