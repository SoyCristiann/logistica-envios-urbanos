package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IRepartidorService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.RepartidorMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;

import java.util.ArrayList;
import java.util.List;

public class RepartidorService implements IRepartidorService {
    private List<Repartidor> repartidores;

    public RepartidorService() {
        this.repartidores = new ArrayList<>();
    }


    @Override
    public RepartidorDTO registrarRepartidor(RepartidorDTO nuevoRepartidor) {
        System.out.println("Nuevo Repartidor a registrar: " + nuevoRepartidor);
        repartidores.add(RepartidorMapper.toEntity(nuevoRepartidor));
        for(Repartidor repartidor : repartidores){
            if(repartidor.getDocumento().equals(nuevoRepartidor.getDocumento())){
                return RepartidorMapper.toDTO(repartidor);
            }
        }
        return null;
    }

    @Override
    public RepartidorDTO actualizarRepartidor(RepartidorDTO repartidorDTO, String idRepartidorAnterior) {
        //System.out.println("Repartidor DTO: \n"+repartidorDTO);
        if(repartidorDTO!=null){
            for(Repartidor r: repartidores){
                if(r.getIdRepartidor().equals(idRepartidorAnterior)){
                    r.setIdRepartidor(repartidorDTO.getIdRepartidor());
                    r.setNombre(repartidorDTO.getNombre());
                    r.setDocumento(repartidorDTO.getDocumento());
                    r.setTelefono(repartidorDTO.getTelefono());
                    r.setEstadoDisponibilidad(repartidorDTO.getEstadoDisponibilidad());
                    r.setZonaCobertura(repartidorDTO.getZonaCobertura());
                    //System.out.println("Repartidor actualizado: \n"+r);
                    return RepartidorMapper.toDTO(r);
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean eliminarRepartidor(RepartidorDTO repartidorDTO) {
        if(repartidorDTO!=null){
            for (Repartidor r : repartidores){
                if(r.getIdRepartidor().equals(repartidorDTO.getIdRepartidor())){
                    repartidores.remove(r);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void asignarEnvio(EnvioDTO envioDTO) {

    }

    @Override
    public List<RepartidorDTO> getRepartidores() {
        List<RepartidorDTO> repartidoresDTO = new ArrayList<>();
        for(Repartidor r: repartidores){
            repartidoresDTO.add(RepartidorMapper.toDTO(r));
        }
        return repartidoresDTO;
    }

    @Override
    public RepartidorDTO buscarRepartidor(String idRepartidor) {
        for(Repartidor r: repartidores){
            if(r.getIdRepartidor().equals(idRepartidor)){
                return RepartidorMapper.toDTO(r);
            }
        }
        return null;
    }
}
