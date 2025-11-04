package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.entidades.Repartidor;

public class EnvioMapper {

    public static EnvioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        EnvioDTO dto = new EnvioDTO(
                envio.getIdEnvio(),
                envio.getOrigen(),
                envio.getDestino(),
                envio.getPeso(),
                envio.getDimensiones(),
                envio.getCosto(),
                envio.getEstado(),
                envio.getFechaCreacion(),
                envio.getFechaEstimada(),
                envio.getUsuario() != null ? envio.getUsuario().getIdUsuario() : null,
                envio.getRepartidor() != null ? envio.getRepartidor().getIdRepartidor() : null
        );
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
                dto.getDimensiones(),
                dto.getEstado(),
                dto.getFechaCreacion(),
                dto.getFechaEstimada(),
                usuario,
                repartidor
        );

        return envio;
    }


    public static void updateEntityFromDTO(Envio envio, EnvioDTO dto) {
        if (envio == null || dto == null) return;

        if (dto.getOrigen() != null) envio.setOrigen(dto.getOrigen());
        if (dto.getDestino() != null) envio.setDestino(dto.getDestino());

        if (dto.getPeso() > 0) envio.setPeso(dto.getPeso());
        if (dto.getCosto() > 0) envio.setCosto(dto.getCosto());
        if (dto.getDimensiones() != null) envio.setDimensiones(dto.getDimensiones());
        if (dto.getEstado() != null) envio.setEstado(dto.getEstado());
        if (dto.getFechaCreacion() != null) envio.setFechaCreacion(dto.getFechaCreacion());
        if (dto.getFechaEstimada() != null) envio.setFechaEstimada(dto.getFechaEstimada());
    }
}
