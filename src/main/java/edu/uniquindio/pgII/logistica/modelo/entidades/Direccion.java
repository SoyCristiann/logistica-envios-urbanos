package edu.uniquindio.pgII.logistica.modelo.entidades;

public class Direccion {

    private String idDireccion;
    private String calle;
    private String numero;
    private String barrio;
    private String ciudad;
    private String codigoPostal;
    private String descripcion;
    private int zona;

    public Direccion(String idDireccion, String calle, String numero, String barrio,String ciudad,String codigoPostal,String descripcion,int zona) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.descripcion = descripcion;
        this.zona = zona;

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

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }


}
