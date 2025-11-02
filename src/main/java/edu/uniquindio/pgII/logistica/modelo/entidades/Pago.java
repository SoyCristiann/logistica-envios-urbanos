package edu.uniquindio.pgII.logistica.modelo.entidades;

import edu.uniquindio.pgII.logistica.modelo.util.Interface.IPago;

import java.time.LocalDateTime;

public abstract class Pago implements IPago {

    private String idPago;
    private double montoPago;
    private LocalDateTime fecha;
    private String metodoPago;
    private String resultado;


    // CONSTRUCTOR


    public Pago(String idPago, double montoPago, LocalDateTime fecha, String metodoPago, String resultado) {
        this.idPago = idPago;
        this.montoPago = montoPago;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.resultado = resultado;
    }

    // GETTERS AND SETTERS

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
