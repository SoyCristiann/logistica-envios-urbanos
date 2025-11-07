package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.patrones.Decorator.EnvioBase;
import edu.uniquindio.pgII.logistica.patrones.Decorator.IEnvio;

import java.util.ArrayList;
import java.util.List;

public class ServicioAdicionalService {

    private List<ServicioAdicional> servicios;

    public ServicioAdicionalService() {
        this.servicios = new ArrayList<>();
    }


    public boolean registrarServicio(ServicioAdicional servicioAdicionalNuevo) {
        if (servicioAdicionalNuevo == null || servicioAdicionalNuevo.getIdService() == null) {
            return false;
        }

        ServicioAdicional existente = buscarPorId(servicioAdicionalNuevo.getIdService());
        if (existente != null) {
            return false;
        }

        servicios.add(servicioAdicionalNuevo);
        return true;
    }


    public boolean actualizarServicio(ServicioAdicional servicioAdicionalActualizado) {
        if (servicioAdicionalActualizado == null || servicioAdicionalActualizado.getIdService() == null) {
            return false;
        }

        ServicioAdicional existente = buscarPorId(servicioAdicionalActualizado.getIdService());
        if (existente != null) {
            existente.setNombre(servicioAdicionalActualizado.getNombre());
            existente.setDescripcion(servicioAdicionalActualizado.getDescripcion());
            existente.setCosto(servicioAdicionalActualizado.getCosto());
            return true;
        }

        return false;
    }


    public boolean eliminarServicio(ServicioAdicional servicioAdicional) {
        if (servicioAdicional == null || servicioAdicional.getIdService() == null) {
            return false;
        }

        ServicioAdicional existente = buscarPorId(servicioAdicional.getIdService());
        if (existente != null) {
            servicios.remove(existente);
            return true;
        }

        return false;
    }


    public ServicioAdicional buscarPorId(String idService) {
        for (ServicioAdicional s : servicios) {
            if (s.getIdService().equals(idService)) {
                return s;
            }
        }
        return null;
    }


    public ArrayList<ServicioAdicional> listarServicios() {
        return new ArrayList<>(servicios);
    }



    //
//    public double calcularCostoConExtras(EnvioDTO envioDTO) {
//        IEnvio envio = new EnvioBase(envioDTO.getCostoBase(), "Envío base");
//
//        if (envioDTO.isSeguro()) envio = new EnvioConSeguro(envio);
//        if (envioDTO.isFragil()) envio = new EnvioFragil(envio);
//        if (envioDTO.isFirmaRequerida()) envio = new EnvioConFirma(envio);
//        if (envioDTO.isPrioritario()) envio = new EnvioPrioritario(envio);
//
//        return envio.getCosto();
//    }

}
