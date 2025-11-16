package edu.uniquindio.pgII.logistica.patrones.builder.usuario;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;

import java.util.List;

public class UsuarioBuilder implements IUsuarioBuilder{

    protected String idUsuario;
    protected String nombreCompleto;
    protected String correoElectronico;
    protected String numerotelefono;
    protected String password;
    protected RolUsuario rolUsuario;

    protected List<Direccion> direccionesFrecuentes;
    protected List<Envio> historialEnvios;
    protected List<MetodoPago> metodosPago;

    public UsuarioBuilder(String idUsuario, String nombreCompleto, String correoElectronico, String numerotelefono) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numerotelefono = numerotelefono;
        this.password = "12345678";
    }

    @Override
    public IUsuarioBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public IUsuarioBuilder withRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
        return this;
    }

    @Override
    public IUsuarioBuilder withListaDirecciones(List<Direccion> direccionesFrecuentes) {
        this.direccionesFrecuentes = direccionesFrecuentes;
        return this;
    }

    @Override
    public IUsuarioBuilder withHistorialEnvio(List<Envio> historialEnvios) {
        this.historialEnvios = historialEnvios;
        return this;
    }

    @Override
    public IUsuarioBuilder withListaMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
        return this;
    }

    public Usuario build(){
        return new Usuario(this);
    }
}
