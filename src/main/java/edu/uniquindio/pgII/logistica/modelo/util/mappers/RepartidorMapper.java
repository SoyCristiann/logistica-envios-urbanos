package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.RepartidorBuilder;

import java.util.ArrayList;
import java.util.List;


public class RepartidorMapper {

    public static RepartidorDTO toDTO(Repartidor repartidor){
        if(repartidor==null){
            return null;
        }
        RepartidorDTO dto=new RepartidorDTO(
                repartidor.getNombre(),
                repartidor.getDocumento(),
                repartidor.getTelefono(),
                repartidor.getZonaCobertura(),
                repartidor.getEstadoDisponibilidad()
        );

        List<EnvioDTO> enviosDTO = new ArrayList<>();

        if(repartidor.getEnviosAsignados()!=null){
            for (Envio envio : repartidor.getEnviosAsignados()){
                enviosDTO.add(EnvioMapper.toDTO(envio));
            }
        }
        dto.setEnviosAsignadosDTO(enviosDTO);
        return dto;
    }

    public static Repartidor toEntity(RepartidorDTO repartidorDTO){
        if(repartidorDTO==null){
            return null;
        }

        //Mapear las listas DTO a Entidades
        List<Envio> enviosAsignados = new ArrayList<>();
        if(repartidorDTO.getEnviosAsignadosDTO() != null){
            for (EnvioDTO envioDTO : repartidorDTO.getEnviosAsignadosDTO()){
                enviosAsignados.add(EnvioMapper.toEntity(envioDTO));
            }
        }

        // Iniciar el Builder con los campos obligatorios.
        RepartidorBuilder builder = new RepartidorBuilder(
                repartidorDTO.getNombre(),
                repartidorDTO.getDocumento(),
                repartidorDTO.getTelefono()
        );

        //Configurar los campos adicionales
        builder.withZonaCobertura(repartidorDTO.getZonaCobertura());

        if(repartidorDTO.getEstadoDisponibilidad() != null) {
            builder.withEstadoDisponibilidad(repartidorDTO.getEstadoDisponibilidad());
        }

        //Asignar las listas mapeadas al incio
        builder.withEnviosAsignados(enviosAsignados);

        //Construir la entidad.
        return builder.build();
    }

    public static void updateEntityFromDTO(RepartidorDTO repartidorDTO, Repartidor repartidor){
        if(repartidorDTO==null || repartidor==null){
            return;
        }
        repartidor.setIdRepartidor(repartidorDTO.getIdRepartidor());
        repartidor.setNombre(repartidorDTO.getNombre());
        repartidor.setDocumento(repartidorDTO.getDocumento());
        repartidor.setTelefono(repartidorDTO.getTelefono());
        repartidor.setZonaCobertura(repartidorDTO.getZonaCobertura());
        repartidor.setEstadoDisponibilidad(repartidorDTO.getEstadoDisponibilidad());

        //Direcciones, metodo de pago e historial de envios
        List<Envio> enviosAsignados= new ArrayList<>();

        if(repartidorDTO.getEnviosAsignadosDTO() != null){
            for (EnvioDTO envioDTO : repartidorDTO.getEnviosAsignadosDTO()){
                // Mapea cada DTO a Entidad antes de añadirlo a la nueva lista
                enviosAsignados.add(EnvioMapper.toEntity(envioDTO));
            }
        }
        repartidor.setEnviosAsignados(enviosAsignados);
    }
}
