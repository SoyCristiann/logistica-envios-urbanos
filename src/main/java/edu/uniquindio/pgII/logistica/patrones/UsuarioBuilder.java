package edu.uniquindio.pgII.logistica.patrones;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;

import java.util.List;

public class UsuarioBuilder {
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String numerotelefono;

    //Listas de relaciones con otros clases

    private List<Direccion> direccionesFrecuentes;
    private List<Envio> historialEnvios;
    private List<String> metodosPago;
}
