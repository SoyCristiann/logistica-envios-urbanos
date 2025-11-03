package edu.uniquindio.pgII.logistica.patrones;

import edu.uniquindio.pgII.logistica.modelo.servicios.DireccionService;
import edu.uniquindio.pgII.logistica.modelo.servicios.EnvioService;
import edu.uniquindio.pgII.logistica.modelo.servicios.ReporteService;
import edu.uniquindio.pgII.logistica.modelo.servicios.UsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IDireccionService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IReporteService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;

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
}
