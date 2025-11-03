package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRolUsuario());

        if (usuario.getDireccionesFrecuentes() != null) {
            dto.setDireccionesFrecuentes(
                    usuario.getDireccionesFrecuentes()
                            .stream()
                            .map(DireccionMapper::toDTO)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setDireccionesFrecuentes(Collections.emptyList());
        }
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setPassword(dto.getPassword());
        usuario.setRolUsuario(dto.getRol());

        if (dto.getDireccionesFrecuentes() != null) {
            List<Direccion> direcciones = dto.getDireccionesFrecuentes()
                    .stream()
                    .map(DireccionMapper::toEntity)
                    .collect(Collectors.toList());
            usuario.setDireccionesFrecuentes(direcciones);
        }
        return usuario;
    }


    public static void updateEntityFromDTO(Usuario usuario, UsuarioDTO dto) {
        if (usuario == null || dto == null) return;

        if (dto.getNombreCompleto() != null) usuario.setNombreCompleto(dto.getNombreCompleto());
        if (dto.getCorreo() != null) usuario.setCorreo(dto.getCorreo());
        if (dto.getTelefono() != null) usuario.setTelefono(dto.getTelefono());
        if (dto.getPassword() != null) usuario.setPassword(dto.getPassword());
        if (dto.getRol() != null) usuario.setRolUsuario(dto.getRol());


        if (dto.getDireccionesFrecuentes() != null) {
            List<Direccion> direcciones = dto.getDireccionesFrecuentes()
                    .stream()
                    .map(DireccionMapper::toEntity)
                    .collect(Collectors.toList());
            usuario.setDireccionesFrecuentes(direcciones);
        }
    }
}
