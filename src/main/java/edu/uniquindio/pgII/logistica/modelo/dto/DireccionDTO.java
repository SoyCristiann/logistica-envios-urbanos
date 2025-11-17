package edu.uniquindio.pgII.logistica.modelo.dto;

public class DireccionDTO {

    private String idDireccion;
    private String calle;
    private String numero;
    private String barrio;
    private String ciudad;
    private String codigoPostal;
    private String descripcion;
    private String alias;


    public DireccionDTO() {
    }

    public DireccionDTO(String idDireccion, String calle, String numero, String barrio,String ciudad,String codigoPostal,String descripcion, String alias) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.descripcion = descripcion;
        this.alias = alias;


    }

    // GETTERS AND SETTERS

    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


    @Override
    public String toString() {
        return getCiudad();
    }

}
