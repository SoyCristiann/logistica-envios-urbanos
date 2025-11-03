package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IDireccionService;

import java.util.ArrayList;
import java.util.List;

public class DireccionService implements IDireccionService {
    private List<Direccion> direcciones;

    public DireccionService(){
        this.direcciones = new ArrayList<Direccion>();
    }


    // METODOS

    public Direccion registrarDireccion(String id, String calle, String numero, String barrio, String ciudad, String codigoPostal, String descripcion, String zona){


        return null;
    }

    public Direccion actualizarDireccion(String id, String calle, String numero, String barrio, String ciudad, String codigoPostal, String descripcion, String zona){


        return null;
    }

    public void  eliminarDireccion(String idDireccion){

    }

    public Direccion buscarDireccionPorId(String idDireccion){


        return null;
    }

    public ArrayList<Direccion>  listarDirecciones(){


        return null;
    }
}