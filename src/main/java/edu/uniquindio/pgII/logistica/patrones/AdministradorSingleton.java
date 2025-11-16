package edu.uniquindio.pgII.logistica.patrones;

import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.servicios.DireccionService;
import edu.uniquindio.pgII.logistica.modelo.servicios.EnvioService;
import edu.uniquindio.pgII.logistica.modelo.servicios.UsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.*;

import java.util.ArrayList;
import java.util.List;

public class AdministradorSingleton {
    private static AdministradorSingleton instance;
    private final ServiceFactory serviceFactory;

    private final IUsuarioService usuarioService;
    private final IDireccionService direccionService;
    private final IEnvioService envioService;
    //Pendiente revisar la interfaz de pago
    private final IReporteService reporteService;
    private final IServicioAdicionalService servicioAdicionalService;

    private AdministradorSingleton() {
        this.serviceFactory = new ServiceFactory();
        this.usuarioService= serviceFactory.crearUsuarioService();
        this.direccionService= serviceFactory.crearDireccionService();
        this.envioService=  serviceFactory.crearEnvioService();
        this.reporteService= serviceFactory.crearReporteService();
        this.servicioAdicionalService = serviceFactory.crearServicioAdicionalService();
    }

    public static AdministradorSingleton getInstance() {
        if(instance==null){
            return instance= new AdministradorSingleton();
        }
        return instance;
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }
    public IDireccionService getDireccionService() {
        return direccionService;
    }
    public IEnvioService getEnvioService() {
        return envioService;
    }
    public IReporteService getReporteService() {
        return reporteService;
    }
    public IServicioAdicionalService getServicioAdicionalService() {
        return servicioAdicionalService;
    }



}
