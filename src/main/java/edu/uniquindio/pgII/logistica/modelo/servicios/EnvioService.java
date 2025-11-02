package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.util.EstadoEnvio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnvioService {

    private List<Envio> envios;
    private TarifaService tarifaService;
    private int contador;


    // CONSTRUCTOR

    public EnvioService() {
        envios = new ArrayList<Envio>();
        tarifaService = new TarifaService();
        contador = 0;
    }


    // METODOS

    public int generarId(){


        return 0;
    }

    public Envio crearEnvio(Usuario usuario, String origen, String destino, double peso, String dimensiones, String prioridad){


        return null;
    }

    public void modificarEnvio(String idEnvio, String nuevoDestino, double nuevoPeso, String nuevasDimensiones) {

    }

    public void cancelarEnvio(String idEnvio){

    }

    public void asignarRepartidor(String idEnvio, Repartidor repartidor){

    }

    public void actualizarEstado(String idEnvio, String nuevoEstado){

    }

    public Envio consultarDetalle( String idEnvio){

        return null;
    }

    public ArrayList<Envio> consultarHistorial(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, EstadoEnvio estado){

        return null;
    }

    public void agregarServicioAdicional(String idEnvio, ServicioAdicional servicio){

    }

    public void eliminarServicioAdicional(String idEnvio, String idServicio){

    }

    public double calcularCostoTotal(String idEnvio){


        return 0;
    }





}
