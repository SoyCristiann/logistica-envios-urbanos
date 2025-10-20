package edu.uniquindio.pgII.logistica.modelo.entidades;

import java.time.LocalDateTime;

public class PagoPse extends Pago {

    private String banco;
    private String referencia;

    public PagoPse(String idPago, double montoPago, LocalDateTime fecha, String metodoPago, String resultado, String banco, String referencia) {
        super(idPago, montoPago, fecha, metodoPago, resultado);
        this.banco = banco;
        this.referencia = referencia;

    }

    @Override
    public boolean procesarPago() {
        return false;
    }

    public String getBanco() {
        return banco;
    }
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;

    }

}
