package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;

import java.util.ArrayList;
import java.util.List;

public class ServicioAdicionalService {

    public List<ServicioAdicional> servicios;

    // CONSTRUCTOR

    public ServicioAdicionalService (){
        this.servicios = new ArrayList<ServicioAdicional>();
    }

    // METODOS

    public ServicioAdicional registrarServicio(int idServicio, String nombre, String descripcion, double costo) {
        return null;
    }

    public ServicioAdicional actualizarServicio(int idServicio, String nombre, String descripcion, double costo) {
        return null;
    }

    public void eliminarServicio(int idServicio) {
    }

    public ServicioAdicional buscarPorId(int idServicio) {
        return null;
    }

    public ArrayList<ServicioAdicional> listarServicios() {
        return null;
    }



}
