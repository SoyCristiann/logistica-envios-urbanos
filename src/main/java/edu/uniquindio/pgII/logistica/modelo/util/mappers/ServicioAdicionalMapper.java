package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.ServicioAdicionalDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;

public class ServicioAdicionalMapper {

    public static ServicioAdicionalDTO toDTO(ServicioAdicional servicio) {
        if (servicio == null) return null;

        ServicioAdicionalDTO dto = new ServicioAdicionalDTO();
        dto.setIdService(servicio.getIdService());
        dto.setNombre(servicio.getNombre());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setCosto(servicio.getCosto());
        return dto;
    }

    public static ServicioAdicional toEntity(ServicioAdicionalDTO dto) {
        if (dto == null) return null;

        return new ServicioAdicional(
                dto.getIdService(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getCosto()
        );
    }

    /**
     * Actualiza una entidad existente con los valores del DTO (si no son nulos o cero).
     */
    public static void updateEntityFromDTO(ServicioAdicional servicio, ServicioAdicionalDTO dto) {
        if (servicio == null || dto == null) return;

        if (dto.getNombre() != null) servicio.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) servicio.setDescripcion(dto.getDescripcion());
        if (dto.getCosto() != 0) servicio.setCosto(dto.getCosto());
        if (dto.getIdService() != null) servicio.setIdService(dto.getIdService());
    }
}
