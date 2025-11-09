package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.entidades.Repartidor;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnvioMapper {

    // 🔹 Convierte un DTO a Entidad
    public static Envio toEntity(EnvioDTO dto) {
        if (dto == null) {
            return null;
        }

        Envio envio = new Envio(
                dto.getOrigen(),
                dto.getDestino(),
                dto.getPeso(),
                dto.getDimensiones(),
                dto.getCosto(),
                dto.getEstado()
        );

        envio.setIdEnvio(dto.getIdEnvio());
        envio.setFechaCreacion(dto.getFechaCreacion());
        envio.setFechaEstimada(dto.getFechaEstimada());

        // Convertir flags booleanos a servicios adicionales
        List<ServicioAdicional> servicios = new ArrayList<>();
        if (dto.isSeguro()) servicios.add(new ServicioAdicional("1", "Seguro", "Cobertura contra pérdida", 2000));
        if (dto.isFragil()) servicios.add(new ServicioAdicional("2", "Frágil", "Manejo delicado", 1500));
        if (dto.isFirmaRequerida()) servicios.add(new ServicioAdicional("3", "Firma Requerida", "Entrega con firma", 1000));
        if (dto.isPrioritario()) servicios.add(new ServicioAdicional("4", "Prioritario", "Entrega express", 3000));

        envio.setServiciosAdicionales(servicios);

        return envio;
    }


    public static EnvioDTO toDTO(Envio envio) {
        if (envio == null) {
            return null;
        }

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


        if (envio.getServiciosAdicionales() != null) {
            for (ServicioAdicional servicio : envio.getServiciosAdicionales()) {
                String nombre = servicio.getNombre().toLowerCase();
                switch (nombre) {
                    case "seguro":
                        dto.setSeguro(true);
                        break;
                    case "frágil":
                    case "fragil":
                        dto.setFragil(true);
                        break;
                    case "firma requerida":
                        dto.setFirmaRequerida(true);
                        break;
                    case "prioritario":
                        dto.setPrioritario(true);
                        break;
                }
            }
        }

        return dto;
    }


    public static void updateEntityFromDTO(EnvioDTO dto, Envio envio) {
        if (dto == null || envio == null) {
            return;
        }

        envio.setOrigen(dto.getOrigen());
        envio.setDestino(dto.getDestino());
        envio.setPeso(dto.getPeso());
        envio.setDimensiones(dto.getDimensiones());
        envio.setCosto(dto.getCosto());
        envio.setEstado(dto.getEstado());
        envio.setFechaEstimada(dto.getFechaEstimada());

        // Actualizar lista de servicios adicionales
        List<ServicioAdicional> servicios = new ArrayList<>();
        if (dto.isSeguro()) servicios.add(new ServicioAdicional("1", "Seguro", "Cobertura contra pérdida", 2000));
        if (dto.isFragil()) servicios.add(new ServicioAdicional("2", "Frágil", "Manejo delicado", 1500));
        if (dto.isFirmaRequerida()) servicios.add(new ServicioAdicional("3", "Firma Requerida", "Entrega con firma", 1000));
        if (dto.isPrioritario()) servicios.add(new ServicioAdicional("4", "Prioritario", "Entrega express", 3000));

        envio.setServiciosAdicionales(servicios);
    }
}
