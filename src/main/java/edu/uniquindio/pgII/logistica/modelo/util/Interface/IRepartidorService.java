package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.patrones.builder.usuario.Usuario;

import java.util.List;

public interface IRepartidorService {

    // Se crean los métodos (contrato) que deberán desarrollar las clases que implementen esta interfaz

    public abstract RepartidorDTO registrarRepartidor(RepartidorDTO nuevoRepartidor);
    public abstract RepartidorDTO actualizarRepartidor(RepartidorDTO repartidorDTO, String  idRepartidorAnterior);
    public abstract boolean eliminarRepartidor(RepartidorDTO repartidorDTO);
    public abstract void asignarEnvio(EnvioDTO envioDTO);
    public abstract List<RepartidorDTO> getRepartidores();
    public abstract RepartidorDTO buscarRepartidor(String idRepartidor);
}
