package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.UsuarioBuilder;

import java.util.ArrayList;
import java.util.List;


public class UsuarioMapper {
    private UsuarioMapper(){}


    public static UsuarioDTO toDTO(Usuario usuario){
        if(usuario==null){
            return null;
        }
        UsuarioDTO dto=new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombreCompleto(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getPassword()
        );
        dto.setRolUsuario(usuario.getRolUsuario());

        List<DireccionDTO> direccionesDTO = new ArrayList<>();
        List<MetodoPago> metodosPago= new ArrayList<>();
        List<EnvioDTO> historialEnviosDTO= new ArrayList<>();

        if(usuario.getDireccionesFrecuentes()!=null){
            for (Direccion direccion : usuario.getDireccionesFrecuentes()){
                direccionesDTO.add(DireccionMapper.toDTO(direccion));
            }
        }


        if(usuario.getMetodosPago()!=null){
            for (MetodoPago metodoPago : usuario.getMetodosPago()){
                metodosPago.add(metodoPago);
            }
        }

        if(usuario.getHistorialEnvios()!=null){
            for (Envio envio: usuario.getHistorialEnvios()){
                historialEnviosDTO.add(EnvioMapper.toDTO(envio));
            }
        }

        dto.setDireccionesFrecuentesDTO(direccionesDTO);
        dto.setMetodosPago(metodosPago);
        dto.setHistorialEnviosDTO(historialEnviosDTO);

        return dto;
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO){
        if(usuarioDTO==null){
            return null;
        }

        //Mapear las listas DTO a Entidades
        List<Direccion> direcciones = new ArrayList<>();
        if(usuarioDTO.getDireccionesFrecuentesDTO() != null){
            for (DireccionDTO direccionDTO : usuarioDTO.getDireccionesFrecuentesDTO()){
                direcciones.add(DireccionMapper.toEntity(direccionDTO));
            }
        }

        List<Envio> historialEnvios = new ArrayList<>();
        if(usuarioDTO.getHistorialEnviosDTO() != null){
            for (EnvioDTO envioDTO: usuarioDTO.getHistorialEnviosDTO()){
                historialEnvios.add(EnvioMapper.toEntity(envioDTO));
            }
        }

        // La lista de MetodosPago es un Enum y puede pasarse directamente (o copiarse si es necesario)
        List<MetodoPago> metodosPago = new ArrayList<>();
        if(usuarioDTO.getMetodosPago() != null){
            metodosPago.addAll(usuarioDTO.getMetodosPago());
        }

        // Iniciar el Builder con los campos obligatorios.
        UsuarioBuilder builder = new UsuarioBuilder(
                usuarioDTO.getIdUsuario(),
                usuarioDTO.getNombreCompleto(),
                usuarioDTO.getCorreo(),
                usuarioDTO.getTelefono()
        );

        //Configurar los campos adicionales
        builder.withPassword(usuarioDTO.getPassword());

        if(usuarioDTO.getRolUsuario() != null) {
            builder.withRolUsuario(RolUsuario.valueOf(usuarioDTO.getRolUsuario()));
        }

        //Asignar las listas mapeadas al incio
        builder.withListaDirecciones(direcciones);
        builder.withHistorialEnvio(historialEnvios);
        builder.withListaMetodosPago(metodosPago);

        //Construir la entidad.
        return builder.build();
    }

    public static void updateEntityFromDTO(UsuarioDTO usuarioDTO, Usuario usuario){
        if(usuarioDTO==null || usuario==null){
            return;
        }
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRolUsuario(RolUsuario.valueOf(usuarioDTO.getRolUsuario()));

        //Direcciones, metodo de pago e historial de envios
        List<Direccion> direcciones= new ArrayList<>();
        List<MetodoPago> metodosPago= new ArrayList<>();
        List<Envio> historialEnvios= new ArrayList<>();

        if(usuarioDTO.getDireccionesFrecuentesDTO() != null){
            for (DireccionDTO direccionDTO : usuarioDTO.getDireccionesFrecuentesDTO()){
                // Mapea cada DTO a Entidad antes de añadirlo a la nueva lista
                direcciones.add(DireccionMapper.toEntity(direccionDTO));
            }
        }

        if(usuarioDTO.getHistorialEnviosDTO()!=null){
            for (EnvioDTO envioDTO: usuarioDTO.getHistorialEnviosDTO()){
                historialEnvios.add(EnvioMapper.toEntity(envioDTO));
            }
        }

        if(usuarioDTO.getMetodosPago()!=null){
            for (MetodoPago metodoPago : usuarioDTO.getMetodosPago()){
                metodosPago.add(metodoPago);
            }
        }

        usuario.setDireccionesFrecuentes(direcciones);
        usuario.setMetodosPago(metodosPago);
        usuario.setHistorialEnvios(historialEnvios);
    }
}
