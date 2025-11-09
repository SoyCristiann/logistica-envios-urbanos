package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import java.util.ArrayList;
import java.util.List;


public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario){
        if(usuario==null){
            return null;
        }
        UsuarioDTO dto=new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombreCompleto(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getPassword(),
                usuario.getRolUsuario()
        );

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

        Usuario usuario=new Usuario(
                usuarioDTO.getIdUsuario(),
                usuarioDTO.getNombreCompleto(),
                usuarioDTO.getCorreo(),
                usuarioDTO.getTelefono(),
                usuarioDTO.getPassword(),
                usuarioDTO.getRolUsuario()
        );

        List<Direccion> direcciones= new ArrayList<>();
        List<MetodoPago> metodosPago= new ArrayList<>();
        List<Envio> historialEnvios= new ArrayList<>();

        if(usuarioDTO.getDireccionesFrecuentesDTO()!=null){
            for (DireccionDTO direccionDTO : usuarioDTO.getDireccionesFrecuentesDTO()){
                direcciones.add(DireccionMapper.toEntity(direccionDTO));
            }
        }


        if(usuarioDTO.getMetodosPago()!=null){
            for (MetodoPago metodoPago : usuarioDTO.getMetodosPago()){
                metodosPago.add(metodoPago);
            }
        }

        if(usuarioDTO.getHistorialEnviosDTO()!=null){
            for (EnvioDTO envioDTO: usuarioDTO.getHistorialEnviosDTO()){
                historialEnvios.add(EnvioMapper.toEntity(envioDTO));
            }
        }

        usuario.setDireccionesFrecuentes(direcciones);
        usuario.setMetodosPago(metodosPago);
        usuario.setHistorialEnvios(historialEnvios);

        return usuario;

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
        usuario.setRolUsuario(usuarioDTO.getRolUsuario());

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
