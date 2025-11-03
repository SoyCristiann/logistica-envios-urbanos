package edu.uniquindio.pgII.logistica.modelo.util.mappers;

import edu.uniquindio.pgII.logistica.modelo.dto.PagoDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Pago;

public class PagoMapper {


    public static PagoDTO toDTO(Pago pago) {
        if (pago == null) return null;

        PagoDTO dto = new PagoDTO();
        dto.setIdPago(pago.getIdPago());
        dto.setMontoPago(pago.getMontoPago());
        dto.setFecha(pago.getFecha());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setResultado(pago.getResultado());
        return dto;
    }


    public static void updateEntityFromDTO(Pago pago, PagoDTO dto) {
        if (pago == null || dto == null) return;

        if (dto.getIdPago() != null) pago.setIdPago(dto.getIdPago());
        if (dto.getMontoPago() != 0) pago.setMontoPago(dto.getMontoPago());
        if (dto.getFecha() != null) pago.setFecha(dto.getFecha());
        if (dto.getMetodoPago() != null) pago.setMetodoPago(dto.getMetodoPago());
        if (dto.getResultado() != null) pago.setResultado(dto.getResultado());
    }
}
