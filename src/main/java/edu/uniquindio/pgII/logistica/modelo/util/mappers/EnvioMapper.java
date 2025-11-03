package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.entidades.Repartidor;

public class EnvioMapper {

    public static EnvioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(envio.getIdEnvio());
        dto.setOrigen(envio.getOrigen());
        dto.setDestino(envio.getDestino());
        dto.setPeso(envio.getPeso());
        dto.setDimensiones(envio.getDimensiones());
        dto.setCosto(envio.getCosto());
        dto.setEstado(envio.getEstado());
        dto.setFechaCreacion(envio.getFechaCreacion());
        dto.setFechaEstimada(envio.getFechaEstimada());

        if (envio.getUsuario() != null) dto.setIdUsuario(envio.getUsuario().getIdUsuario());
        if (envio.getRepartidor() != null) dto.setIdRepartidor(envio.getRepartidor().getIdRepartidor());

        return dto;
    }

    public static Envio toEntity(EnvioDTO dto, Usuario usuario, Repartidor repartidor) {
        if (dto == null) return null;

        Envio envio = new Envio(
                dto.getIdEnvio(),
                dto.getOrigen(),
                dto.getDestino(),
                dto.getPeso(),
                dto.getCosto(),
                dto.getEstado() != null ? dto.getEstado().name() : null,
                dto.getFechaCreacion(),
                dto.getFechaEstimada(),
                usuario,
                repartidor
        );
        envio.setDimensiones(dto.getDimensiones());
        return envio;
    }


    public static void updateEntityFromDTO(Envio envio, EnvioDTO dto) {
        if (envio == null || dto == null) return;

        if (dto.getOrigen() != null) envio.setOrigen(dto.getOrigen());
        if (dto.getDestino() != null) envio.setDestino(dto.getDestino());
        if (dto.getPeso() != 0) envio.setPeso(dto.getPeso());
        if (dto.getDimensiones() != null) envio.setDimensiones(dto.getDimensiones());
        if (dto.getCosto() != 0) envio.setCosto(dto.getCosto());
        if (dto.getEstado() != null) envio.setEstado(dto.getEstado());
        if (dto.getFechaCreacion() != null) envio.setFechaCreacion(dto.getFechaCreacion());
        if (dto.getFechaEstimada() != null) envio.setFechaEstimada(dto.getFechaEstimada());
    }
}
