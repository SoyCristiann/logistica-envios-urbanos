package edu.uniquindio.pgII.logistica.patrones.builder.usuario;

import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;

import java.util.List;

public interface IUsuarioBuilder {
    public abstract IUsuarioBuilder withPassword(String password);
    public abstract IUsuarioBuilder withRolUsuario(RolUsuario rolUsuario);

    public abstract IUsuarioBuilder withListaDirecciones(List<Direccion> direccionesFrecuentes);
    public abstract IUsuarioBuilder withHistorialEnvio(List<Envio> historialEnvios);
    public abstract IUsuarioBuilder withListaMetodosPago(List<MetodoPago> metodosPago);
}
