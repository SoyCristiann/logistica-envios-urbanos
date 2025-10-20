package edu.uniquindio.pgII.logistica.modelo.entidades;

import java.time.LocalDateTime;

public class PagoTarjeta extends Pago{

    private String numeroTarjeta;
    private String titular;
    private String tipoTarjeta;

    public PagoTarjeta(String idPago, double montoPago, LocalDateTime fecha, String metodoPago, String resultado, String numeroTarjeta, String titular, String tipoTarjeta) {
        super(idPago, montoPago, fecha, metodoPago, resultado);
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.tipoTarjeta = tipoTarjeta;
    }


    @Override
    public boolean procesarPago() {
        return false;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;

    }
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;

    }
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
}
