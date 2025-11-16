package edu.uniquindio.pgII.logistica.patrones.singleton;

import edu.uniquindio.pgII.logistica.modelo.util.Interface.*;
import edu.uniquindio.pgII.logistica.patrones.factory.ServiceFactory;

public class AdministradorSingleton {
    private static AdministradorSingleton instance;
    private final ServiceFactory serviceFactory;

    private final IUsuarioService usuarioService;
    private final IRepartidorService repartidorService;
    private final IDireccionService direccionService;
    private final IEnvioService envioService;
    //Pendiente revisar la interfaz de pago
    private final IReporteService reporteService;

    private final IServicioAdicionalService serviciosAdicionalesService;

    private AdministradorSingleton() {
        this.serviceFactory = new ServiceFactory();
        this.usuarioService= serviceFactory.crearUsuarioService();
        this.repartidorService= serviceFactory.crearRepartidorService();
        this.direccionService= serviceFactory.crearDireccionService();
        this.envioService=  serviceFactory.crearEnvioService();
        this.reporteService= serviceFactory.crearReporteService();

        this.serviciosAdicionalesService= serviceFactory.crearServiciosAdicionalesService();

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
    public IRepartidorService getRepartidorService() {return repartidorService;}
    public IDireccionService getDireccionService() {
        return direccionService;
    }
    public IEnvioService getEnvioService() {
        return envioService;
    }
    public IReporteService getReporteService() {
        return reporteService;
    }

    public IServicioAdicionalService getServiciosAdicionalesService() {
        return serviciosAdicionalesService;
    }


}
