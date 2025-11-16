package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;

public class DireccionMapper {
    private DireccionMapper(){}

    // 1. Mapeo de Entidad a DTO
    public static DireccionDTO toDTO(Direccion direccion) {
        if (direccion == null) return null;

        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCalle(direccion.getCalle());
        dto.setNumero(direccion.getNumero());
        dto.setBarrio(direccion.getBarrio());
        dto.setCiudad(direccion.getCiudad());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        dto.setDescripcion(direccion.getDescripcion());
        dto.setAlias(direccion.getAlias());

        return dto;
    }

    // 2. Mapeo de DTO a Entidad
    public static Direccion toEntity(DireccionDTO dto) {
        if (dto == null) return null;

        Direccion direccion = new Direccion();
        direccion.setIdDireccion(dto.getIdDireccion());
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setBarrio(dto.getBarrio());
        direccion.setCiudad(dto.getCiudad());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setDescripcion(dto.getDescripcion());
        direccion.setAlias(dto.getAlias());

        return direccion;
    }


    // 3. Actualización de Entidad desde DTO
    public static void updateEntityFromDTO(Direccion direccion, DireccionDTO dto) {
        if (direccion == null || dto == null) return;

        if (dto.getCalle() != null) direccion.setCalle(dto.getCalle());
        if (dto.getNumero() != null) direccion.setNumero(dto.getNumero());
        if (dto.getBarrio() != null) direccion.setBarrio(dto.getBarrio());
        if (dto.getCiudad() != null) direccion.setCiudad(dto.getCiudad());
        if (dto.getCodigoPostal() != null) direccion.setCodigoPostal(dto.getCodigoPostal());
        if (dto.getDescripcion() != null) direccion.setDescripcion(dto.getDescripcion());
        if (dto.getIdDireccion() != null) direccion.setIdDireccion(dto.getIdDireccion());
        if (dto.getAlias() != null) direccion.setAlias(dto.getAlias());
    }
}